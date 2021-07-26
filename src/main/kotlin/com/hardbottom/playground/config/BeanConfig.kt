package com.hardbottom.playground.config

//import com.beust.klaxon.Parser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hardbottom.playground.JachiAPI.JachiDataObject
import com.hardbottom.playground.account.Account
import com.hardbottom.playground.account.AccountRole
import com.hardbottom.playground.account.AccountService
import com.hardbottom.playground.population.LocationRepository
import com.hardbottom.playground.population.Population
import com.hardbottom.playground.population.PopulationService
import com.hardbottom.playground.population.ReadLocationDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object LocationProperty {
    private var locationSet: HashMap<String, Pair<String, String>> = hashMapOf()
    fun setDistrictFromLocations(locations: List<ReadLocationDTO>) {
        for (location in locations) {
            locationSet[location.code] = Pair(location.district, location.city)
        }
    }

    fun getDistrictAndCityFromCode(revCode: String): Pair<String, String>? {
        return locationSet[revCode];
    }
}

@Configuration
class BeanConfig {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun tokenStore() : TokenStore {
        return InMemoryTokenStore()
    }

    fun getDayOfWeekString(day: Int) : String {
        var ret: String = ""
        when (day) {
            Calendar.SUNDAY -> { ret = "SUNDAY" }
            Calendar.MONDAY -> { ret = "MONDAY" }
            Calendar.TUESDAY -> { ret = "TUESDAY" }
            Calendar.WEDNESDAY -> { ret = "WEDNESDAY" }
            Calendar.THURSDAY -> { ret = "THURSDAY" }
            Calendar.FRIDAY -> { ret = "FRIDAY" }
            Calendar.SATURDAY -> { ret = "SATURDAY" }
        }
        return ret
    }

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return object : ApplicationRunner {

            @Autowired
            private lateinit var accountService: AccountService
            @Autowired
            private lateinit var populationService: PopulationService
            @Autowired
            lateinit var restTemplateBuilder: RestTemplateBuilder
            @Value("\${openapi.key}")
            val datakey: String? = null

            override fun run(args: ApplicationArguments) {

                // Account
                val account = accountService.getAccount("playground");

                if(account == null) {
                    val admin = Account(
                        null,
                        "playground",
                        "playground",
                        mutableSetOf(AccountRole.ADMIN, AccountRole.USER)
                    )
                    accountService.saveAccount(admin)
                }

                // Population
                val locationList = populationService.getLocations()
                LocationProperty.setDistrictFromLocations((locationList))

                val curDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val yy = Integer.parseInt(curDate.split("-")[0])
                val mm = Integer.parseInt(curDate.split("-")[1])
                val dd = Integer.parseInt(curDate.split("-")[2])
                //val parser: Parser = Parser.default()
                val cal = Calendar.getInstance()
                cal.set(yy, mm - 1, dd)
                cal.add(Calendar.DATE, -14)
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                val format = SimpleDateFormat("yyyyMMdd")
                val restTemplate = restTemplateBuilder.build()
                val mapper = jacksonObjectMapper()
                var idOfPopulation: Long = 0
                for (i in 1..7) {
                    val date = format.format(cal.time)
                    val day = getDayOfWeekString(cal.get(Calendar.DAY_OF_WEEK))
                    val cnt: Long = 24 * 25
                    try {
                        var response = restTemplate.getForObject(
                            "http://openapi.seoul.go.kr:8088/{datakey}/json/SPOP_LOCAL_RESD_JACHI/1/{cnt}/{date}",
                            String::class.java, //JachiDataObject::class.java,
                            datakey,
                            cnt,
                            date
                        );
                        response = response?.toLowerCase()
                        val jsonData: JachiDataObject = mapper.readValue(response, JachiDataObject::class.java)
                        val populationData = jsonData.spop_local_resd_jachi.row.mapIndexed { idx, it ->
                            val code = it.adstrd_code_se
                            val time = it.tmzon_pd_se
                            var count: Float = 0.0f
                            count += it.male_f15t19_lvpop_co.toFloat()
                            count += it.male_f20t24_lvpop_co.toFloat()
                            count += it.male_f25t29_lvpop_co.toFloat()
                            count += it.male_f30t34_lvpop_co.toFloat()
                            count += it.female_f15t19_lvpop_co.toFloat()
                            count += it.female_f20t24_lvpop_co.toFloat()
                            count += it.female_f25t29_lvpop_co.toFloat()
                            count += it.female_f30t34_lvpop_co.toFloat()
                            Population(idOfPopulation + idx, code, day, time, count.toInt())
                        }
                        populationService.insertPopulations(populationData)
                    } catch(e: Exception) {
                        println("서울 유동인구 데이터를 가져오는데 실패하였습니다 Error: $e")
                    } finally {
                        cal.add(Calendar.DATE, 1)
                        idOfPopulation += cnt
                    }
                }
            }
        }
    }
}
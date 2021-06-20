package com.hardbottom.playground.config

import com.hardbottom.playground.account.Account
import com.hardbottom.playground.account.AccountRepository
import com.hardbottom.playground.account.AccountRole
import com.hardbottom.playground.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

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

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return object : ApplicationRunner {

            @Autowired
            private lateinit var accountService: AccountService

            override fun run(args: ApplicationArguments) {

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
            }

        }
    }
}
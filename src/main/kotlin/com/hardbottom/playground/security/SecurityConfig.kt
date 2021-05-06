package com.hardbottom.playground.security

import com.hardbottom.playground.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    companion object {
        const val LOGIN_SUCCESS_URL : String = "/populations"
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(accountService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.anonymous()
                .and()
            .formLogin()
                .successForwardUrl(LOGIN_SUCCESS_URL)
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
    }
}
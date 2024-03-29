package com.hardbottom.playground.config

import com.hardbottom.playground.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import java.lang.Exception

@Configuration
@EnableAuthorizationServer
class AuthServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var passwordEncoder : PasswordEncoder

    @Autowired
    lateinit var authenticationManager : AuthenticationManager

    @Autowired
    lateinit var accountService : AccountService

    @Autowired
    lateinit var tokenStore : TokenStore

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.passwordEncoder(passwordEncoder)
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("myApp")
            .authorizedGrantTypes("password","refresh_token")
            .scopes("read","write")
            .secret(this.passwordEncoder.encode("playground"))
            .accessTokenValiditySeconds(10*60)
            .refreshTokenValiditySeconds(6*10*60)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
            .userDetailsService(accountService)
            .tokenStore(tokenStore)
    }
}
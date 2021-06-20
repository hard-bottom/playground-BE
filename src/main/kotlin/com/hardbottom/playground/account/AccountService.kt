package com.hardbottom.playground.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService : UserDetailsService{

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var passwordEncoder : PasswordEncoder

    fun saveAccount(account: Account):Account {
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }

    fun getAccount(email: String): Account? {
        return accountRepository.findByEmail(email)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return accountRepository.findByEmail(username)?.getAuthorities()
            ?: throw UsernameNotFoundException("$username cannot found")
    }
}
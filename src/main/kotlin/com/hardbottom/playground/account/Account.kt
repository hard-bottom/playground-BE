package com.hardbottom.playground.account

import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue
    var id : Long? = null,
    var email : String? = null,
    var password : String? = null,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles : MutableSet<AccountRole> = mutableSetOf(),

    @CreationTimestamp
    var createDt : LocalDateTime = LocalDateTime.now()
) {
    fun getAuthorities(): User {
        return User(
            this.email,
            this.password,
            this.roles.stream().map{ role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
}

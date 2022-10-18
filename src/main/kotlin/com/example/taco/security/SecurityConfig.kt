package com.example.taco.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*


@Configuration
@EnableGlobalAuthentication
class SecurityConfig: WebSecurityConfigurerAdapter() {
    @Autowired
    private var userDetailsService: UserDetailsService? = null

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

//    @Bean
//    fun userDetailsService(encoder: PasswordEncoder): UserDetailsService {
//        var userList: MutableList<UserDetails> = ArrayList()
//        userList.add(User("buzz", encoder.encode("password"), Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))))
//        userList.add(User("woody", encoder.encode("password"), Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))))
//
//        return InMemoryUserDetailsManager(userList)
//    }

//    @Bean
//    fun userDetailsService(userRepo: UserRepository): UserDetailsService? {
//        return UserDetailsService { username: String ->
//            val user: com.example.taco.User = userRepo.findByUsername(username)
//            if (user != null) {
//                return@UserDetailsService user
//            }
//            throw UsernameNotFoundException(
//                "User '$username' not found"
//            )
//        }
//    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/design", "/orders").access("hasRole('USER')")
            .antMatchers("/", "/**").access("permitAll")
            .and()
            .formLogin()
            .loginPage("/login")
            .and()
            .logout()
            .logoutSuccessUrl("/") // Make H2-Console non-secured; for debug purposes
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**") // Allow pages to be loaded in frames from the same origin; needed for H2-Console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()
    }
}
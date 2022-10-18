package com.example.taco.security

import com.example.taco.User
import com.example.taco.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserRepositoryUserDetailService: UserDetailsService {
    private val userRepo:UserRepository

    @Autowired
    constructor(userRepo:UserRepository)
    {
        this.userRepo = userRepo
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepo.findByUsername(username)
        if (user != null) {
            return user
        }
        throw UsernameNotFoundException(
            "User '$username' not found"
        )
    }
}
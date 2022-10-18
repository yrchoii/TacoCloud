package com.example.taco.security

import com.example.taco.data.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
class RegistrationController {
    private var userRepo : UserRepository
    private var passwordEncoder: PasswordEncoder
    constructor(userRepo:UserRepository, passwordEncoder: PasswordEncoder)
    {
        this.userRepo = userRepo
        this.passwordEncoder = passwordEncoder
    }

    @GetMapping
    fun resiterForm():String
    {
       return "registration"
    }

    @PostMapping
    fun processRegistration(form:RegistrationForm): String
    {
        userRepo.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }
}
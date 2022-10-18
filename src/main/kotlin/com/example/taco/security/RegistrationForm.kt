package com.example.taco.security

import com.example.taco.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm(val username:String,val password:String, val fullname:String, val street:String, val city:String, val state:String, val zip:String, val phone:String)
{
    fun toUser(passwordEncoder: PasswordEncoder):User
    {
        return User(username, passwordEncoder.encode(password),
            fullname, street, city, state, zip, phone)
    }
}

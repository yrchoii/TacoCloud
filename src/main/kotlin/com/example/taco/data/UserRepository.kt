package com.example.taco.data

import com.example.taco.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User,Long> {
     fun findByUsername(username:String): User
}
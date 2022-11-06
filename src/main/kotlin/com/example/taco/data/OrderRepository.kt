package com.example.taco.data

import com.example.taco.TacoOrder
import com.example.taco.User
import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<TacoOrder, Long> {
//    fun save(order: TacoOrder)
    fun findByUserOrderByPlacedAtDesc(user:User, pageable: org.springframework.data.domain.Pageable): List<TacoOrder>
}
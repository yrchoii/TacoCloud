package com.example.taco.data

import com.example.taco.TacoOrder
import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<TacoOrder, Long> {
//    fun save(order: TacoOrder)
}
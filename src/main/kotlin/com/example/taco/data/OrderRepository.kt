package com.example.taco.data

import com.example.taco.TacoOrder

interface OrderRepository {
    fun save(order: TacoOrder):TacoOrder
}
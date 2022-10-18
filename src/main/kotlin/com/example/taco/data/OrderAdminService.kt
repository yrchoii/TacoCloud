package com.example.taco.data

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class OrderAdminService {

    private val orderRepository:OrderRepository
    constructor(orderRepository: OrderRepository)
    {
        this.orderRepository = orderRepository
    }

    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAllOrders()
    {
        orderRepository.deleteAll()
    }
}
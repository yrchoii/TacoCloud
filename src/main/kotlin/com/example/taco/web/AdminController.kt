package com.example.taco.web

import com.example.taco.data.OrderAdminService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/admin")
class AdminController {
    private val adminService:OrderAdminService
    constructor(adminService: OrderAdminService)
    {
        this.adminService = adminService
    }

    @GetMapping
    fun showAdminPage():String
    {
        return "admin"
    }

    @PostMapping("/deleteOrders")
    fun deleteAllOrders():String
    {
        adminService.deleteAllOrders()
        return "redirect:/admin"
    }
}
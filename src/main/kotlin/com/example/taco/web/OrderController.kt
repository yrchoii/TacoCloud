package com.example.taco.web

import com.example.taco.TacoOrder
import com.example.taco.User
import com.example.taco.data.OrderRepository
import com.example.taco.data.UserRepository
import lombok.extern.java.Log
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import java.security.Principal


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {
    val logger: Logger = LoggerFactory.getLogger(OrderController::class.java)
    private var orderRepo : OrderRepository

    constructor(orderRepo:OrderRepository)
    {
        this.orderRepo = orderRepo
    }

    @GetMapping("/current")
    fun orderForm():String
    {

        return "orderForm"
    }

    @PostMapping
    fun processOrder(order:TacoOrder, errors: Errors, sessionStatus:SessionStatus, @AuthenticationPrincipal user:User):String
    {
        logger.info("Order Submitted: {}",order)

        if(errors.hasErrors())
        {
            return "orderForm"
        }

        order.user = user
        orderRepo.save(order)

        sessionStatus.setComplete()
        return "redirect:/"
    }
}
package com.example.taco.web

import com.example.taco.TacoOrder
import com.example.taco.data.OrderRepository
import lombok.extern.java.Log
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus


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
    fun processOrder(order:TacoOrder,errors: Errors ,sessionStatus:SessionStatus):String
    {
        logger.info("Order Submitted: {}",order)

        if(errors.hasErrors())
        {
            return "orderForm"
        }
        sessionStatus.setComplete()
        return "redirect:/"
    }
}
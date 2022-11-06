package com.example.taco.web

import com.example.taco.TacoOrder
import com.example.taco.User
import com.example.taco.data.OrderRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController {
    val logger: Logger = LoggerFactory.getLogger(OrderController::class.java)
    private var orderRepo : OrderRepository
    private var props: OrderProps

    constructor(orderRepo:OrderRepository, props:OrderProps)
    {
        this.orderRepo = orderRepo
        this.props= props
    }

    @GetMapping("/current")
    fun orderForm(
        @AuthenticationPrincipal user: User,
        @ModelAttribute order: TacoOrder
    ): String? {
        if (order.deliveryName == null) {
            order.deliveryName= user.fullname
        }
        if (order.deliveryStreet == null) {
            order.deliveryStreet = user.street
        }
        if (order.deliveryCity == null) {
            order.deliveryCity = user.city
        }
        if (order.deliveryState == null) {
            order.deliveryState = user.state
        }
        if (order.deliveryZip == null) {
            order.deliveryZip = user.zip
        }
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        order: @Valid TacoOrder, errors: Errors,
        sessionStatus: SessionStatus,
        @AuthenticationPrincipal user: User?
    ): String? {
        if (errors.hasErrors()) {
            return "orderForm"
        }
        order!!.user = user!!
        orderRepo.save(order)
        sessionStatus.setComplete()
        return "redirect:/"
    }

    @GetMapping
    fun ordersForUser(
        @AuthenticationPrincipal user: User?, model: Model
    ): String? {
        val pageable: Pageable = PageRequest.of(0, props.pageSize)
        model.addAttribute(
            "orders",
            orderRepo.findByUserOrderByPlacedAtDesc(user!!, pageable)
        )
        return "orderList"
    }
}
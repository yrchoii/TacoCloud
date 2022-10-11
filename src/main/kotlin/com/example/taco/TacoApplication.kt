package com.example.taco

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TacoApplication

fun main(args: Array<String>) {
	runApplication<TacoApplication>(*args)
}

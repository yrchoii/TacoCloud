package com.example.taco.data

import com.example.taco.Taco
import org.springframework.data.repository.CrudRepository

interface TacoRepository:CrudRepository<Taco,Long> {
}
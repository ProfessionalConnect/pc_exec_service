package com.pro.exec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ExecApplication

fun main(args: Array<String>) {
    runApplication<ExecApplication>(*args)
}

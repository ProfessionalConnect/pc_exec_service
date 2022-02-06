package com.pro.exec.controller

import com.pro.exec.dto.ExecRequest
import com.pro.exec.dto.ExecResponse
import com.pro.exec.service.ExecService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Minky on 2022-02-06
 */

@RestController
@RequestMapping("/api/v1/exec")
class ExecController {
    @Autowired
    private lateinit var execService: ExecService

    @PostMapping
    fun matchTestCases(@RequestBody execRequest: ExecRequest): ResponseEntity<ExecResponse> =
        ResponseEntity.ok(execService.runAndMatchTestCases(execRequest))
}
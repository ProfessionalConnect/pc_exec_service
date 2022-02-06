package com.pro.exec.service

import com.pro.exec.dto.ExecRequest
import com.pro.exec.dto.ExecResponse

/**
 * Created by Minky on 2022-02-06
 */
interface ExecService {
    fun runAndMatchTestCases(execRequest: ExecRequest): ExecResponse
}
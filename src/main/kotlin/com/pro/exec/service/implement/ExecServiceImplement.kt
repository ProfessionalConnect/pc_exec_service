package com.pro.exec.service.implement

import com.pro.exec.dto.ExecRequest
import com.pro.exec.service.ExecService
import com.pro.exec.dto.CodeType
import com.pro.exec.dto.ExecResponse
import com.pro.exec.dto.ResultType
import com.pro.exec.util.Compiler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Minky on 2022-02-06
 */

@Service
class ExecServiceImplement: ExecService {
    @Autowired
    private lateinit var compiler: Compiler

    override fun runAndMatchTestCases(execRequest: ExecRequest): ExecResponse {
        if (execRequest.getEnumCodeType() == CodeType.C) {
            return runAndMatchCodeC(execRequest)
        }
        if (execRequest.getEnumCodeType() == CodeType.CPP) {
            return runAndMatchCodeCpp(execRequest)
        }

         return ExecResponse(ResultType.MISSMATCH, ResultType.MISSMATCH.message)
    }

    private fun runAndMatchCodeC(execRequest: ExecRequest): ExecResponse {
        val fileStatusSender = compiler.compileCodeC(execRequest.uuid, execRequest.subjectId, execRequest.code)

        if (!fileStatusSender.status) {
             return ExecResponse(ResultType.FILE_CRASH, ResultType.FILE_CRASH.message)
        }

        val result = execRequest.testArguments.all { testArgumentRequest ->
            compiler.matchResult(
                compiler.executeFileC(fileStatusSender, testArgumentRequest.testArgument),
                testArgumentRequest.matchResult
            )
        }

        if (result) {
            return ExecResponse(ResultType.SUCCESS, ResultType.SUCCESS.message)
        }

        return ExecResponse(ResultType.FAIL, ResultType.FAIL.message)
    }

    private fun runAndMatchCodeCpp(execRequest: ExecRequest): ExecResponse {
        val fileStatusSender = compiler.compileCodeCpp(execRequest.uuid, execRequest.subjectId, execRequest.code)

        if (!fileStatusSender.status) {
             return ExecResponse(ResultType.FILE_CRASH, ResultType.FILE_CRASH.message)
        }

        val result = execRequest.testArguments.all { testArgumentRequest ->
            compiler.matchResult(
                compiler.executeFileCpp(fileStatusSender, testArgumentRequest.testArgument),
                testArgumentRequest.matchResult
            )
        }

        if (result) {
            return ExecResponse(ResultType.SUCCESS, ResultType.SUCCESS.message)
        }

        return ExecResponse(ResultType.FAIL, ResultType.FAIL.message)
    }
}
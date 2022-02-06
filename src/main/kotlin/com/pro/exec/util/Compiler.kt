package com.pro.exec.util

import com.pro.exec.util.c.CFileBuilder
import com.pro.exec.util.c.CFileExecutor
import com.pro.exec.util.c.CFileMaker
import com.pro.exec.util.cpp.CppFileBuilder
import com.pro.exec.util.cpp.CppFileExecutor
import com.pro.exec.util.cpp.CppFileMaker
import org.springframework.stereotype.Component

/**
 * Created by Minky on 2022-02-06
 */

@Component
class Compiler {
    fun matchResult(execResult: ExecResult, matchResult: String) =
        execResult.errorLog == "" && matchResult == execResult.inputLog

    fun compileCodeC(uuid: String, subjectId: Long, code: String): FileStatusSender {
        val fileStatusSender = CFileMaker.saveCode(uuid, subjectId, code)
        return CFileBuilder.makeExecuteCode(fileStatusSender)
    }

    fun executeFileC(fileStatusSender: FileStatusSender, testArgument: String): ExecResult =
        CFileExecutor.run(fileStatusSender, testArgument)

    fun compileCodeCpp(uuid: String, subjectId: Long, code: String): FileStatusSender {
        val fileStatusSender = CppFileMaker.saveCode(uuid, subjectId, code)
        return CppFileBuilder.makeExecuteCode(fileStatusSender)
    }

    fun executeFileCpp(fileStatusSender: FileStatusSender, testArgument: String): ExecResult =
        CppFileExecutor.run(fileStatusSender, testArgument)
}
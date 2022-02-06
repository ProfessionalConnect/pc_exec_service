package com.pro.exec.dto

/**
 * Created by Minky on 2022-02-06
 */
data class ExecRequest(
    val uuid: String,
    val subjectId: Long,
    val code: String,
    val testArguments: List<TestArgumentRequest>,
    val codeType: String,
) {
    fun getEnumCodeType() = CodeType.valueOf(codeType)
}
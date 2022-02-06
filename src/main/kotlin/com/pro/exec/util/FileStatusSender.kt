package com.pro.exec.util

/**
 * Created by Minky on 2022-02-06
 */
data class FileStatusSender(
    val status: Boolean,
    val originalFileName: String,
    val fileName: String,
    val directory: String,
) {
}
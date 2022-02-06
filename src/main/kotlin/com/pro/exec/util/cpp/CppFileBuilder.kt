package com.pro.exec.util.cpp

import com.pro.exec.util.FileStatusSender
import com.pro.exec.util.file.ExecutionFileExtension
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Minky on 2022-02-06
 */
object CppFileBuilder {
    @Throws(Exception::class)
    fun makeExecuteCode(fileStatusSender: FileStatusSender): FileStatusSender {
        var status = true
        val processBuilder = ProcessBuilder("g++", fileStatusSender.fileName, "-o", fileStatusSender.originalFileName)
        processBuilder.directory(File(fileStatusSender.directory))

        val process = processBuilder.start()
        process.waitFor(3, TimeUnit.SECONDS)
        process.destroy()

        val exitValue = process.exitValue()
        if (exitValue != 0) {
            status = false
        }

        val newFileName = "${fileStatusSender.originalFileName}${ExecutionFileExtension.WINDOW}"
        if (!checkFile("${fileStatusSender.directory}/$newFileName")) {
            status = false
        }

        return FileStatusSender(
            status,
            fileStatusSender.originalFileName,
            newFileName,
            fileStatusSender.directory
        )
    }

    private fun checkFile(path: String): Boolean {
        val file = File(path)
        return file.exists()
    }

    private fun checkFileAndWait(fileName: String, directory: String): Boolean {
        val path = "$directory/$fileName"
        if (!checkFile(path)) {
            Thread.sleep(3000)
        }
        return checkFile(path)
    }
}
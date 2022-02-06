package com.pro.exec.util.c

import com.pro.exec.util.ExecResult
import com.pro.exec.util.FileStatusSender
import java.io.*
import java.util.concurrent.TimeUnit


/**
 * Created by Minky on 2022-02-06
 */

object CFileExecutor {
    @Throws(Exception::class)
    fun run(fileStatusSender: FileStatusSender, testArgument: String): ExecResult {
        val processBuilder = ProcessBuilder("${fileStatusSender.directory}/${fileStatusSender.fileName}")

        /** Current Not Working **/
        // processBuilder.directory(File(fileStatusSender.directory))

        val process = processBuilder.start()

        /** Input 삽입 **/
        val outputStream = process.outputStream
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))
        bufferedWriter.write(testArgument)
        bufferedWriter.close()
        outputStream.close()

        process.waitFor(10, TimeUnit.SECONDS)
        process.destroy()

        val inputLog = readInputStream(process.inputStream).trim()
        val errorLog = readInputStream(process.errorStream).trim()

        return ExecResult(inputLog, errorLog)
    }

    fun readInputStream(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val builder = StringBuilder()
        var line: String? = null
        while (reader.readLine().also { line = it } != null) {
            builder.append(line)
            builder.append(System.getProperty("line.separator"))
        }
        return builder.toString()
    }
}
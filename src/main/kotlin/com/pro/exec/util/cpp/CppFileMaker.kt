package com.pro.exec.util.cpp

import com.pro.exec.util.FileStatusSender
import com.pro.exec.util.file.CodeFileExtension.BASE
import com.pro.exec.util.file.CodeFileExtension.CPP
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * Created by Minky on 2022-02-06
 */
object CppFileMaker {
    fun saveCode(uuid: String, subjectId: Long, code: String): FileStatusSender {

        val path = System.getProperty("user.dir")

        val originalFileName = System.currentTimeMillis().toString()
        val fileName = "$originalFileName${CPP}"
        val directory = "$path/${BASE}/$uuid/$subjectId"
        val finalPath = "$directory/$fileName"
        val file = File(directory)

        if (!file.exists()) {
            file.mkdirs()
        }

        try {
            Files.write(Paths.get(finalPath), code.toByteArray(), StandardOpenOption.CREATE)
        } catch(e: FileNotFoundException) {
            return FileStatusSender(false, originalFileName, fileName, directory)
        }

        return FileStatusSender(true, originalFileName, fileName, directory)
    }
}
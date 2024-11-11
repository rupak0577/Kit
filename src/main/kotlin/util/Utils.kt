package org.example.util

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun getConfigFile(path: Path, filename: String): File {
    val configFile = path.resolve(filename)
    return if (configFile.toFile().exists())
        configFile.toFile()
    else {
        Files.createFile(configFile).toFile()
    }
}
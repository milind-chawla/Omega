package com.omega.io

import java.io.File
import org.apache.commons.io.FileUtils
import java.util.Base64

object IO {
    def uploadDir = """C:\Users\milind\eclipse-ide\git\Omega\WebContent"""
    
    implicit class IOHelper(file: File) {        
        def readBase64: String = {
            val encoder = Base64.getEncoder();
            val bytes = FileUtils.readFileToByteArray(file)
            encoder.encodeToString(bytes)
        }
    }
}

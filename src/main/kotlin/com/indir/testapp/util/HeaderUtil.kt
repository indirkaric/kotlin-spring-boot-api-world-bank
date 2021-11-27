package com.indir.testapp.util

import com.indir.testapp.common.FileConstants
import org.springframework.http.HttpHeaders

class HeaderUtil {
    companion object {
        fun getFileHeader(fileName: String, extension: String): HttpHeaders {
            val headers = HttpHeaders()
            val attachment = "attachment; filename=$fileName$extension"
            headers.add(HttpHeaders.CONTENT_DISPOSITION, attachment)
            headers.add(FileConstants.FILENAME, fileName)
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
            headers.add(HttpHeaders.PRAGMA, "no-cache")
            headers.add(HttpHeaders.EXPIRES, "0")
            return headers
        }
    }
}
package com.indir.testapp.exception.error

class BadRequestException(val error: RestApiError) : RuntimeException()
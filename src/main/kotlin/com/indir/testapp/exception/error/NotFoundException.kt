package com.indir.testapp.exception.error;

class NotFoundException(val error: RestApiError) : RuntimeException()

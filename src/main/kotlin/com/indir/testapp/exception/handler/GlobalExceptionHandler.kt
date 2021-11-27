package com.indir.testapp.exception.handler;

import com.indir.testapp.exception.error.BadRequestException;
import com.indir.testapp.exception.error.NotFoundException;
import com.indir.testapp.exception.error.RestApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handle(ex: MethodArgumentNotValidException): ResponseEntity<RestApiError> {
        val result = ex.bindingResult
        val error = Objects.requireNonNull(result.fieldError)?.defaultMessage?.let { RestApiError("field_error", it) }
        return ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = [BadRequestException::class])
    fun handle(ex: BadRequestException) : ResponseEntity<RestApiError> = ResponseEntity(ex.error, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [NotFoundException::class])
    fun handle(ex: NotFoundException) : ResponseEntity<RestApiError> = ResponseEntity(ex.error, HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = [Exception::class])
    fun handle(ex:Exception) :ResponseEntity<RestApiError> =
        ResponseEntity(RestApiError("Internal server error", ex.message.toString()), HttpStatus.INTERNAL_SERVER_ERROR)


}

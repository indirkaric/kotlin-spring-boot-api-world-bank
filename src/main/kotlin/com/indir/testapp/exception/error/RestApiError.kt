package com.indir.testapp.exception.error;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
class RestApiError (@JsonProperty("ERROR")val error:String,
                    @JsonProperty("ERROR_DESCRIPTION")val description:String):
                    Serializable {
    companion object {
        val LANGUAGE_DOES_NOT_EXISTS = RestApiError("validation_error", "Language not found")
        val USER_NOT_FOUND = RestApiError("validation_error", "User not found")
        val USER_ALREADY_EXISTS = RestApiError("validation_error", "User already exists")
        val COUNTRY_DOES_NOT_EXISTS = RestApiError("validation_error", "Country does not exists")
    }
}
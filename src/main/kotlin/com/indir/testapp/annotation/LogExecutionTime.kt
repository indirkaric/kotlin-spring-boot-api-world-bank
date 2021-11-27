package com.indir.testapp.annotation

@kotlin.annotation.Target(AnnotationTarget.FUNCTION,
                          AnnotationTarget.PROPERTY_GETTER,
                          AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime
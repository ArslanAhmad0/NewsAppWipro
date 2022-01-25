package com.arslan.newsapp_wipro.util

sealed class Output<out T : Any> {
    data class Success<out T : Any>(val output: T) : Output<T>()
    data class Error(val exception: Exception) : Output<Nothing>()
}
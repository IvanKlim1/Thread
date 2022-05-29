package ru.netology.nmedia.model

import java.io.IOException


sealed class AppError(var code: String): RuntimeException()
class ApiError(val status: Int, code: String): AppError(code)
object NetworkError : AppError("error_network")
object UnknownError: AppError("error_unknown")
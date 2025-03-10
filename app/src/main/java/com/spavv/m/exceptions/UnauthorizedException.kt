package com.spavv.m.exceptions

class UnauthorizedException(message: String) : Exception(message)

//* How to catch exception thrown
/*
val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    if (throwable is UnauthorizedException) {
        Navigate to login screen
    }
}

viewModelScope.launch(exceptionHandler) {
    repository.getPromotions()
}

* */
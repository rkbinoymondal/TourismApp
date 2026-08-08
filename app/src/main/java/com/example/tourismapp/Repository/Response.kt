package com.example.tourismapp.Repository

sealed class Response<T>(val data : T? = null, val errorMessage : String? = null) {
    class Loading<T>() : Response<T>();
    class Success<T>(dataSuccess : T? = null) : Response<T>(data = dataSuccess);
    class Failure<T>(errorMess : String? = null) : Response<T>(errorMessage = errorMess);
}
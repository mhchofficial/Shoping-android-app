package com.akatsuki.myproject.api

import retrofit2.Response

open class Respistry {

    suspend fun <T: Any> Customresponse(work: () ->  Response<T>): T {

        val response = work.invoke()

        if (response.isSuccessful)
            return response.body()!!
            throw Exception(response.message())



    }
}
package com.example.test1.apiConfig

import com.akatsuki.myproject.api.Respistry
import retrofit2.Response

object Handle_requests : Respistry(){
    suspend fun <T: Any> Request(response: Response<T>) = Customresponse { response }
}
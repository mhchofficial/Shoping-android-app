package com.example.test1.apiConfig

import com.example.test1.apiConfig.models.comments.add.add_comment
import com.example.test1.apiConfig.models.comments.comments
import com.example.test1.apiConfig.models.login.login
import com.example.test1.apiConfig.models.pro.pru
import com.example.test1.apiConfig.models.recent.recent
import com.example.test1.apiConfig.models.topPager.top
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface api {


    @GET("test/top.json")
    suspend fun top_call(
    ): Response<top>



    @GET("test/recent.json")
    suspend fun recent_call(
        //@Header("Content-Type") content: String,
    ):Response<recent>

    @GET("test/pr.json")
    suspend fun pru_call(
        //@Header("Content-Type") content: String,
    ):Response<pru>


    @GET("test/login.json")
    suspend fun login_call(
        //@Header("Content-Type") content: String,
    ):Response<login>

    @GET("test/comment.php")
    suspend fun comment_call(
        //@Header("Content-Type") content: String,
    ):Response<comments>

    @GET("test/add_comment.php")
    suspend fun add_comment_call(
        @Query("username") username: String,
        @Query("com") comment: String,
    ):Response<add_comment>






    companion object{

        operator fun invoke():api{
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            //setting for capture http data
            val log = OkHttpClient.Builder()
            log.addInterceptor(logger)
            //setting for time out
            log.connectTimeout(20, TimeUnit.SECONDS)
            log.writeTimeout(20, TimeUnit.SECONDS)
            log.readTimeout(30, TimeUnit.SECONDS)
            //domain must be seted
            return Retrofit.Builder().baseUrl("https://domain.com/")
                .client(log.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(api::class.java)

        }
    }
}
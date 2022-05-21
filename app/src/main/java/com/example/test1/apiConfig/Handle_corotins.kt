package com.example.test1.apiConfig

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

object Handle_corotins {
    fun <T: Any> ThreadMain(context: Context, work:suspend ( () -> T), callback :((T) -> Unit))=
    CoroutineScope(Dispatchers.Main).launch {
        try {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()
            callback(data)


        }catch (e: IOException){
            Log.e("error", e.toString())
        }
        catch (e: HttpException){
        }
        catch (e: Exception){
            Log.e("error", e.toString())
        }

    }
}
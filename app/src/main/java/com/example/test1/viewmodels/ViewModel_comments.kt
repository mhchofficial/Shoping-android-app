package com.example.test1.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test1.apiConfig.Handle_corotins
import com.example.test1.apiConfig.Handle_requests
import com.example.test1.apiConfig.api
import kotlinx.coroutines.Job
import com.example.test1.apiConfig.models.comments.Result
class ViewModel_comments: ViewModel() {
    val response = MutableLiveData<List<Result>>()
    lateinit var job: Job

    //for post call and with json object we use this header
    //val agent = "application/json"

    fun call(context: Context){
        
        job = Handle_corotins.ThreadMain(context,
            {
                Handle_requests.Request(api.invoke().comment_call())
            }
        ) {
            response.value = it.result
        }

    }

    override fun onCleared() {
        if (::job.isInitialized)job.cancel()

        super.onCleared()
    }
}
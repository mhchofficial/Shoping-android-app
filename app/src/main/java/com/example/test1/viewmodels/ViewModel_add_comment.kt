package com.example.test1.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test1.apiConfig.Handle_corotins
import com.example.test1.apiConfig.Handle_requests
import com.example.test1.apiConfig.api
import com.example.test1.apiConfig.models.comments.add.add_comment
import com.example.test1.apiConfig.models.login.Result
import kotlinx.coroutines.Job

class ViewModel_add_comment: ViewModel() {
    val response = MutableLiveData<add_comment>()
    lateinit var job: Job

    fun call(context: Context, username: String, comment: String){
        
        job = Handle_corotins.ThreadMain(context,
            {
                Handle_requests.Request(api.invoke().add_comment_call(username, comment))
            }
        ) {
            response.value = it
        }

    }

    override fun onCleared() {
        if (::job.isInitialized)job.cancel()

        super.onCleared()
    }
}
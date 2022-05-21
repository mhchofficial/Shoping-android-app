package com.example.test1.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test1.apiConfig.models.login.Result
import com.example.test1.db.RoomModel_top
import com.example.test1.db.RoomModel_users
import com.example.test1.db.roomsetup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class roomViewModel_save_search(application: Application, title: String): AndroidViewModel(application) {

    private val db: roomsetup = roomsetup.getInstance(application)

    //internal val save : LiveData<List<RoomModel_save>> = db.myDao().getsave_sps(title)

    fun search(title: String):LiveData<List<RoomModel_save>>{
        return db.myDao().getsave_sps(title)
    }
}

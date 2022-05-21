package com.example.test1.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.test1.db.RoomModel_users
import com.example.test1.db.roomsetup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class roomViewModel_users(application: Application): AndroidViewModel(application) {

    private val db: roomsetup = roomsetup.getInstance(application)

    internal val user : LiveData<RoomModel_users> = db.myDao().getProfile()

    //internal val check : List<Userp> = db.myDao().checkProfile()


        fun insert(users: RoomModel_users){
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    db.myDao().insertProfile(users)
                }
            }catch (e: IOException){
                Log.e("my db ex", e.toString())
            }

        }

        fun cleardata(){
            viewModelScope.launch(Dispatchers.IO) {
                db.myDao().deleteProfile()
            }
        }

}

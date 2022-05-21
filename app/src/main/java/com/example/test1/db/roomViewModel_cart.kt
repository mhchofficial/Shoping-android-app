package com.example.test1.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.test1.db.RoomModel_top
import com.example.test1.db.RoomModel_users
import com.example.test1.db.roomsetup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class roomViewModel_cart(application: Application): AndroidViewModel(application) {

    private val db: roomsetup = roomsetup.getInstance(application)

    var cart : LiveData<List<RoomModel_cart>> = db.myDao().getcart(false)


        fun insert(cart: RoomModel_cart){
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    db.myDao().insertCarts(cart)
                }
            }catch (e: IOException){
                Log.e("my db ex", e.toString())
            }

        }

        fun cleardata(){
            viewModelScope.launch(Dispatchers.IO) {
                db.myDao().deleteCarts()
            }
        }

}

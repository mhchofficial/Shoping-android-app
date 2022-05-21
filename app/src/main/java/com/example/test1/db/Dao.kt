package com.example.test1.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.test1.db.RoomModel_cart
import com.example.test1.db.RoomModel_top
import com.example.test1.db.RoomModel_users
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @androidx.room.Query("SELECT * FROM users")
    fun getProfile() :LiveData<RoomModel_users>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: RoomModel_users)



    @androidx.room.Query("DELETE FROM users")
    suspend fun deleteProfile()




    //cart
    @androidx.room.Query("SELECT * FROM cart WHERE status LIKE '%' || :status || '%' ")
    fun getcart(status: Boolean): LiveData<List<RoomModel_cart>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarts(data: RoomModel_cart)


    @androidx.room.Query("DELETE FROM cart")
    suspend fun deleteCarts()


    //top
    @androidx.room.Query("SELECT * FROM top")
    fun gettop(): LiveData<List<RoomModel_top>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttop(data: RoomModel_top)


    @androidx.room.Query("DELETE FROM top")
    suspend fun deletetop()


    //save
    @androidx.room.Query("SELECT * FROM save")
    fun getsave(): LiveData<List<RoomModel_save>>

    @androidx.room.Query("SELECT * FROM save Where title LIKE '%' || :title || '%' ")
    fun getsave_sps(title: String): LiveData<List<RoomModel_save>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsave(data: RoomModel_save)


    @androidx.room.Query("DELETE FROM save")
    suspend fun deletesave()

    @androidx.room.Query("DELETE FROM save Where title LIKE '%' || :title || '%' ")
    suspend fun deletesave_sps(title: String)


}
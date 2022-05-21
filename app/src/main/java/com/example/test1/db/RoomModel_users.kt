package com.example.test1.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "users")
data class RoomModel_users(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "img")
    var img: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,



)




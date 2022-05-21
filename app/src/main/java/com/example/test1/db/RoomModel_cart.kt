package com.example.test1.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "cart")
data class RoomModel_cart(

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "price")
    var price: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "status")
    var status: Boolean? = null,



)




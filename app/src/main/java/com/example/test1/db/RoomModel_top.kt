package com.example.test1.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "top")
data class RoomModel_top(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "body")
    var body: String? = null,

    @ColumnInfo(name = "img")
    var img: String? = null,



)




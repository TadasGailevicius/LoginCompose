package com.tedm.logincompose.feature_auth.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val username: String,
    val password: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int
)

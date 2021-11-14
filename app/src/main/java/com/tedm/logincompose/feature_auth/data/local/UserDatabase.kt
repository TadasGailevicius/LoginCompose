package com.tedm.logincompose.feature_auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tedm.logincompose.feature_auth.data.local.entities.User

@Database(
    entities = [User::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
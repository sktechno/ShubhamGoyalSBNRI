package com.sk.shubhamgoyalsbnri.db

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sk.shubhamgoyalsbnri.db.MyDataBase.Companion.DATABASE_VERSION
import com.sk.shubhamgoyalsbnri.model.License
import com.sk.shubhamgoyalsbnri.model.Owner
import com.sk.shubhamgoyalsbnri.model.Permissions
import com.sk.shubhamgoyalsbnri.model.User
import java.lang.reflect.Type


//
// Created by SK(Sk) on 05/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

@Embedded(prefix = "license")
private lateinit var license: License

@Embedded(prefix = "permissions")
private lateinit var permissions: Permissions

@Embedded(prefix = "owner")
private lateinit var owner: Owner

@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "UserDb"

        @Volatile
        private var INSTANCE: MyDataBase? = null

        fun getDB(context: Context): MyDataBase {

            val temp = INSTANCE
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }


    object Converters {
        @TypeConverter
        fun fromString(value: String?): ArrayList<String> {
            val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayLisr(list: ArrayList<String?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }


    object TypeConvertorClass {
        @TypeConverter
        fun getPermissions(): Permissions {
            return Permissions()
        }

        @TypeConverter
        fun getLicense(): License {
            return License()
        }

        @TypeConverter
        fun getOwner(): Owner {
            return Owner()
        }
    }

}
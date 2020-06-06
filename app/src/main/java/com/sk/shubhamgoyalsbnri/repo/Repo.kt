package com.sk.shubhamgoyalsbnri.repo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.sk.shubhamgoyalsbnri.ApiResponse
import com.sk.shubhamgoyalsbnri.db.MyDataBase
import com.sk.shubhamgoyalsbnri.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//
// Created by SK(Sk) on 04/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class Repo(private val context: Context) {

    companion object {
        const val API_URL = "octokit/repos"
        const val PerPage = 10
    }


    fun getUser(page: Long, apiResponse: ApiResponse<List<User>>) {

        if (!isNetworkAvailable(context)!!) {
            val db = MyDataBase.getDB(context)
            val list = db.userDao().getUsers()
            val sublist = if (list.size >= page * PerPage) list.subList(
                ((page - 1) * PerPage).toInt(),
                (page * PerPage).toInt()
            ) else
                ArrayList<User>()

            apiResponse.onResponse(sublist)
        }
        val service = ApiClient.getRetrofitInstance()?.create(ApiService::class.java)

        val data = service?.getUsers(page, Repo.PerPage)
        data?.enqueue(object : Callback<List<User>?> {
            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                apiResponse.onFailure(t)
            }

            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                val list = response.body()
                list?.let {
                    apiResponse.onResponse(it)
                    saveData(it)
                }

            }
        })

    }

    private fun saveData(user: List<User>) {

        Thread(Runnable {
            MyDataBase.getDB(context).userDao().insert(*user.toTypedArray())
        }).start()


    }

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            } else {
                @Suppress("DEPRECATION")
                activeNetworkInfo?.run {
                    return when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
}
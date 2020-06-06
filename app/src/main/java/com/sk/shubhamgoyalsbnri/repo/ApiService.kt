package com.sk.shubhamgoyalsbnri.repo

import com.sk.shubhamgoyalsbnri.model.User
import com.sk.shubhamgoyalsbnri.repo.Repo.Companion.API_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//
// Created by SK(Sk) on 04/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

interface ApiService {
    @GET(API_URL)
    fun getUsers(@Query("page") page: Long , @Query("per_page") perPage:Int): Call<List<User>?>?
}
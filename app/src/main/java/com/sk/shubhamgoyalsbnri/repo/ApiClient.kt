package com.sk.shubhamgoyalsbnri.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//
// Created by SK(Sk) on 04/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class ApiClient {
    companion object {

        const val BASE_URL = "https://api.github.com/orgs/"

        private var retrofit: Retrofit? = null

        @JvmStatic
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}
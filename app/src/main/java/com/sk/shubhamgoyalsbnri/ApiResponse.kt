package com.sk.shubhamgoyalsbnri

import com.sk.shubhamgoyalsbnri.model.User
import retrofit2.Response


//
// Created by SK(Sk) on 05/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

interface ApiResponse<T> {

    fun onFailure(t: Throwable)
    fun onResponse(response: T)
}
package com.sk.shubhamgoyalsbnri.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName





//
// Created by SK(Sk) on 04/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class Permissions {
    @SerializedName("admin")
    @Expose
    var admin: Boolean? = null

    @SerializedName("push")
    @Expose
    var push: Boolean? = null

    @SerializedName("pull")
    @Expose
    var pull: Boolean? = null
}
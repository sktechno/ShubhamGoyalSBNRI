package com.sk.shubhamgoyalsbnri.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sk.shubhamgoyalsbnri.model.User
import com.sk.shubhamgoyalsbnri.model.UserDataSource
import com.sk.shubhamgoyalsbnri.repo.Repo


//
// Created by SK(Sk) on 05/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class MainViewModelFactory(private val repo: Repo) : DataSource.Factory<Long, User>() {

    lateinit var dataSource: UserDataSource
    var mutableLiveData: MutableLiveData<UserDataSource> = MutableLiveData<UserDataSource>()



    override fun create(): DataSource<Long, User> {
        dataSource = UserDataSource(repo)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }


}
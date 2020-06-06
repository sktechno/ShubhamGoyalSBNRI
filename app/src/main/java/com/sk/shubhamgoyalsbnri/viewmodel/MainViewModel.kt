package com.sk.shubhamgoyalsbnri.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sk.shubhamgoyalsbnri.model.User
import com.sk.shubhamgoyalsbnri.model.UserDataSource
import com.sk.shubhamgoyalsbnri.repo.Repo
import com.sk.shubhamgoyalsbnri.repo.Repo.Companion.PerPage
import java.util.concurrent.Executor
import java.util.concurrent.Executors


//
// Created by SK(Sk) on 04/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class MainViewModel : AndroidViewModel {

    private val mApplication: Application

    private val repo: Repo

    lateinit var dataSourceFactory: MainViewModelFactory
    lateinit var dataSourceMutableLiveData: MutableLiveData<UserDataSource>
    lateinit var executor: Executor
    lateinit var pagedListLiveData: LiveData<PagedList<User>>


    constructor(application: Application) : super(application) {
        this.mApplication = application
        repo = Repo(mApplication.applicationContext)

        getData()
    }

    fun getData() {

        dataSourceFactory = MainViewModelFactory(repo)
        dataSourceMutableLiveData = dataSourceFactory.mutableLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(PerPage)
            .setPageSize(PerPage)
            .setPrefetchDistance(4)
            .build()

        executor = Executors.newFixedThreadPool(5)
        pagedListLiveData = LivePagedListBuilder<Long, User>(dataSourceFactory, config)
            .setFetchExecutor(executor)
            .build()

    }
}
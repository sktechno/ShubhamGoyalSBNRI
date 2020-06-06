package com.sk.shubhamgoyalsbnri.model

import androidx.paging.PageKeyedDataSource
import com.sk.shubhamgoyalsbnri.ApiResponse
import com.sk.shubhamgoyalsbnri.repo.ApiClient
import com.sk.shubhamgoyalsbnri.repo.ApiService
import com.sk.shubhamgoyalsbnri.repo.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//
// Created by SK(Sk) on 05/06/20.
// Copyright (c) 2020 Sktech. All rights reserved.

class UserDataSource(private val repo: Repo) : PageKeyedDataSource<Long, User>() {


    /**
     * Load initial data.
     *
     *
     * This method is called first to initialize a PagedList with data. If it's possible to count
     * the items that can be loaded by the DataSource, it's recommended to pass the loaded data to
     * the callback via the three-parameter
     * [LoadInitialCallback.onResult]. This enables PagedLists
     * presenting data from this source to display placeholders to represent unloaded items.
     *
     *
     * [LoadInitialParams.requestedLoadSize] is a hint, not a requirement, so it may be may be
     * altered or ignored.
     *
     * @param params Parameters for initial load, including requested load size.
     * @param callback Callback that receives initial load data.
     */
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, User>
    ) {
        repo.getUser(1 , object : ApiResponse<List<User>> {
            override fun onFailure(t: Throwable) {

            }

            override fun onResponse(response: List<User>) {
                callback.onResult(response, null, 2.toLong())
            }
        })
    }

    /**
     * Append page with the key specified by [LoadParams.key].
     *
     *
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     *
     *
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     *
     *
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call [.invalidate] to invalidate the data source,
     * and prevent further loading.
     *
     * @param params Parameters for the load, including the key for the new page, and requested load
     * size.
     * @param callback Callback that receives loaded data.
     */
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, User>) {
        repo.getUser(params.key, object : ApiResponse<List<User>> {
            override fun onFailure(t: Throwable) {

            }

            override fun onResponse(response: List<User>) {
                callback.onResult(response, params.key + 1)
            }
        })
    }

    /**
     * Prepend page with the key specified by [LoadParams.key].
     *
     *
     * It's valid to return a different list size than the page size if it's easier, e.g. if your
     * backend defines page sizes. It is generally safer to increase the number loaded than reduce.
     *
     *
     * Data may be passed synchronously during the load method, or deferred and called at a
     * later time. Further loads going down will be blocked until the callback is called.
     *
     *
     * If data cannot be loaded (for example, if the request is invalid, or the data would be stale
     * and inconsistent, it is valid to call [.invalidate] to invalidate the data source,
     * and prevent further loading.
     *
     * @param params Parameters for the load, including the key for the new page, and requested load
     * size.
     * @param callback Callback that receives loaded data.
     */
    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, User>) {

        repo.getUser(params.key, object : ApiResponse<List<User>> {
            override fun onFailure(t: Throwable) {

            }

            override fun onResponse(response: List<User>) {
                callback.onResult(response, params.key + 1)
            }
        })
    }
}
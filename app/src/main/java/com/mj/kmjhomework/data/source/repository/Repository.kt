package com.mj.kmjhomework.data.source.repository

import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.data.model.BeersRequest

interface Repository {

    fun getBeers(
        request: BeersRequest,
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    )

    fun getSingleBeer(
        id: Int,
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    )

    fun getRandomBeer(
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    )
}
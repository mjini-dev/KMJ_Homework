package com.mj.kmjhomework.data.source.repository

import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.data.model.BeersRequest
import com.mj.kmjhomework.data.source.remote.RemoteDataSource

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getBeers(
        request: BeersRequest,
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {
        remoteDataSource.getBeers(request, onSuccess, notSuccessStatus, onFailure)
    }

    override fun getSingleBeer(id: Int, onSuccess: (response: List<Beer>) -> Unit, notSuccessStatus: (errorCode: Int) -> Unit, onFailure: (e: Throwable) -> Unit) {
        remoteDataSource.getSingleBeer(id, onSuccess, notSuccessStatus, onFailure)
    }

    override fun getRandomBeer(
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {
        remoteDataSource.getRandomBeer(onSuccess, notSuccessStatus, onFailure)
    }
}
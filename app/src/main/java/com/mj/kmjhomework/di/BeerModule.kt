package com.mj.kmjhomework.di

import com.mj.kmjhomework.data.source.remote.RemoteDataSource
import com.mj.kmjhomework.data.source.remote.RemoteDataSourceImpl
import com.mj.kmjhomework.data.source.repository.Repository
import com.mj.kmjhomework.data.source.repository.RepositoryImpl
import org.koin.dsl.module

val beerModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<Repository> { RepositoryImpl(get())}
}
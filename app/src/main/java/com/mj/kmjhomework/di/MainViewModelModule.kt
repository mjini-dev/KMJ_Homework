package com.mj.kmjhomework.di

import com.mj.kmjhomework.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}
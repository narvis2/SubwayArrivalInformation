package com.example.subwaymvvm.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel() : ViewModel() {

    abstract fun fetchData() : Job

    // xml 에서 android:visibility="@{vm.isLoading() ? View.VISIBLE : View.GONE}" 이렇게 사용 <import type="android.view.View" /> 추가
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    // viewModel or Activity, Fragment 에서 사용
    fun showProgress() {
        _isLoading.value = true
    }

    // viewModel or Activity, Fragment 에서 사용
    fun hideProgress() {
        _isLoading.value = false
    }


    override fun onCleared() {
        super.onCleared()
        fetchData().cancel()
    }

}
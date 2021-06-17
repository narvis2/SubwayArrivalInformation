package com.example.subwaymvvm.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.subwaymvvm.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {

    }

    private val _toolbar = MutableLiveData<Boolean>(false)
    val toolbar : LiveData<Boolean>
        get() = _toolbar


    fun showToolbar() {
        _toolbar.value = true
    }

    fun hideToolbar() {
        _toolbar.value = false
    }
}
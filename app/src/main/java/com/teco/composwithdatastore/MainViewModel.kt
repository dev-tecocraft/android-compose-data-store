package com.teco.composwithdatastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSharedPref: DataSharedPref
): ViewModel() {

    var isUserLogin : StateFlow<Boolean> =
        dataSharedPref.isUserLogin().stateIn(viewModelScope, SharingStarted.Lazily,false)

    var currentCount : StateFlow<Int> =
        dataSharedPref.currentCount().stateIn(viewModelScope, SharingStarted.Lazily,0)


    fun setIsUserLogin(isUserLogin: Boolean){
        viewModelScope.launch {
            dataSharedPref.updateUserLogin(isUserLogin)
        }
    }

    fun increaseCount(counter: Int){
        viewModelScope.launch {
            dataSharedPref.updateCounter(counter)
        }
    }

}
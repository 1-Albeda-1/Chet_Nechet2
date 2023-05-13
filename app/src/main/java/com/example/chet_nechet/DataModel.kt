package com.example.chet_nechet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val messageForComputerFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForUserFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForMainActivityFromUserFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForMainActivityFromComputerFragmentTrueCounter: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForMainActivityFromUserFragmentTrueCounter: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
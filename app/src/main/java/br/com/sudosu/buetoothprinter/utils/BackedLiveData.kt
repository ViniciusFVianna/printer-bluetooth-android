package br.com.sudosu.buetoothprinter.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@MainThread
class BackedLiveData<T>: MediatorLiveData<T>() {

    private var mBackingLiveData: LiveData<T>? = null

    fun setSource(source: LiveData<T>?) {

        if(source == mBackingLiveData) {
            return
        }

        if(mBackingLiveData != null) {
            removeSource(mBackingLiveData!!)
        }

        mBackingLiveData = source

        if(mBackingLiveData != null) {
            addSource(mBackingLiveData!!, this::setValue)
        }
    }
}
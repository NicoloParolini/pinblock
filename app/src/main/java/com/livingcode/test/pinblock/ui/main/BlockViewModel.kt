package com.livingcode.test.pinblock.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livingcode.test.pinblock.calculator.BlockCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlockViewModel : ViewModel() {
    private val pan = "1111222233334444"
    val pin = ObservableField<String>()
    private val calculator = BlockCalculator()

    private val _block = MutableLiveData<String>().apply {
        value = ""
    }

    val block: LiveData<String> = _block

    private val _error = MutableLiveData<Boolean>().apply {
        value = false
    }

    val error: LiveData<Boolean> = _error

    fun calculate() {
        pin.get()?.let { enteredPin ->
            viewModelScope.launch(Dispatchers.IO) {
                calculator.calculate(enteredPin, pan)?.let { result ->
                    _error.postValue(false)
                    _block.postValue(result)
                } ?: run { _error.postValue(true) }
            }
        } ?: run { _error.postValue(true) }
    }
}
package com.rvp.stringoperations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StringViewModel : ViewModel() {


    // For storing string operation output
    private val _stringOutput = MutableLiveData<String>()
    val stringOutput : LiveData<String> = _stringOutput

    //For storing string error messages
    private val _stringErrorMessage = MutableLiveData<String?>()
    val stringErrorMessage : LiveData<String?> = _stringErrorMessage

    // For limit to enter string value into edit text
    private val _inputValue =MutableLiveData<String>()
    val inputValue : LiveData<String> = _inputValue

    fun setInputValue(value : String){
        _inputValue.value = value
    }

    private fun validateInputValue(value : String) : Boolean{

        return when{

            value.length > 100 -> {
                _stringErrorMessage.value = "Only 100 characters are allowed"
                false
            }

            !value.matches(Regex("^[a-zA-Z0-9]*$")) -> {
                _stringErrorMessage.value = "Only alphanumeric characters allowed"
                false
            }
            else -> {
                _stringErrorMessage.value = null
                true
            }

        }
    }

    fun getLength(value: String){
        if (validateInputValue(value)){
            _stringOutput.value = value.length.toString()
        }
    }

    fun getReversedString(value: String){
        if (validateInputValue(value)){
            _stringOutput.value = value.reversed()
        }
    }

    fun appendValue(value: String){
        if (validateInputValue(value)){
            _stringOutput.value = value + "123"
        }
    }

    fun isStringNumeric(value: String){
        if (validateInputValue(value)){
            _stringOutput.value = if (value.matches(Regex("\\d+"))) "yes" else "no"
        }
    }

    fun clearData(){
        _inputValue.value = ""
        _stringOutput.value = ""
        _stringErrorMessage.value = null
    }

}
package com.martinszuc.templateapp.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Project: database application
 *
 * Author: Bc. Martin Szuc (matoszuc@gmail.com)
 * GitHub: https://github.com/martinszuc
 *
 *
 * License:
 * This code is licensed under MIT License. You may not use this file except
 * in compliance with the License.
 */

abstract class BaseViewModel : ViewModel() {

    protected val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _isFinished = MutableLiveData(false)
    val isFinished: LiveData<Boolean> = _isFinished

    protected val _operationFailed = MutableLiveData(false)
    val operationFailed: LiveData<Boolean> = _operationFailed

    protected val _hasStarted = MutableLiveData(false)
    val hasStarted: LiveData<Boolean> = _hasStarted

    protected val _progress = MutableLiveData(0)
    val progress: LiveData<Int> = _progress

    protected val _totalCount = MutableLiveData(0)
    val totalCount: LiveData<Int> = _totalCount

    protected fun <T> launchDataLoad(
        execution: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    ) {
        _hasStarted.postValue(true)
        _isLoading.postValue(true)
        _progress.postValue(0)
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.Default) {
                    execution()
                }
                onSuccess(result)
                _isFinished.postValue(true)
            } catch (e: Exception) {
                Log.e(logTag, "Error in launchDataLoad: ${e.message}")
                onFailure(e)
                _operationFailed.postValue(true)
            } finally {
                _isLoading.postValue(false)
                _hasStarted.postValue(false)
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        onEach: (T) -> Unit
    ) {
        _hasStarted.postValue(true)
        _isLoading.postValue(true)
        viewModelScope.launch(dispatcher) {
            try {
                flow.collectLatest { data ->
                    onEach(data)
                }
                _isFinished.postValue(true)
            } catch (e: Exception) {
                Log.e(logTag, "Error collecting flow: ${e.message}")
                _operationFailed.postValue(true)
            } finally {
                _isLoading.postValue(false)
                _hasStarted.postValue(false)
            }
        }
    }

    fun clearStates() {
        _isFinished.postValue(false)
        _operationFailed.postValue(false)
        _hasStarted.postValue(false)
        _isLoading.postValue(false)
    }

    fun clearIsFinished() {
        _isFinished.postValue(false)
    }

    fun setIsFinished(boolean: Boolean) {
        _isFinished.postValue(boolean)
    }

    fun clearOperationFailed() {
        _operationFailed.postValue(false)
    }

    fun clearHasStarted() {
        _hasStarted.postValue(false)
    }

    companion object {
        private const val logTag = "AbstractViewModel"
    }
}

package com.adkhambek.telepat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adkhambek.auth.api.User
import com.adkhambek.auth.api.UsersProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val usersProvider: UsersProvider
) : ViewModel() {

    private val _usersFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersFlow: StateFlow<List<User>> get() = _usersFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val users = usersProvider()
            _usersFlow.emit(users)
        }
    }
}
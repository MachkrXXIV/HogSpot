package com.team.hogspot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.team.hogspot.model.user.User
import com.team.hogspot.model.user.UserRepository
import kotlinx.coroutines.launch

/**
 * ViewModel we can import to other activities
 * when we need to modify the current user
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _currentUser = MutableLiveData<User>().apply { value = null }
    val currentUser: LiveData<User>
        get() = _currentUser

    private fun setCurrentUser(user: User) {
        _currentUser.value = user
        Log.d("UserViewModel", "Current User: ${currentUser.value}")
    }

    fun signUp(email: String, userName: String) = viewModelScope.launch {
        val newUser = User(null, userName, email, emptyList())
        val userId = repository.insert(newUser)
        repository.getUserById(userId).collect {
            setCurrentUser(it[userId]!!)
        }
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.team.hogspot.PlayActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.geospot.GeoSpotRepository
import com.team.hogspot.model.user.User
import com.team.hogspot.model.user.UserRepository
import kotlinx.coroutines.launch

class PlayViewModel(private val userRepository: UserRepository, private val geoSpotRepository: GeoSpotRepository) : ViewModel() {
    private val _currentUser = MutableLiveData<User>().apply { value = null }
    private val _geoSpot = MutableLiveData<GeoSpot>().apply { value = null }
    private val _savedGeoSpots = MutableLiveData<List<GeoSpot>>().apply { value = emptyList() }
    val currentUser: LiveData<User>
        get() = _currentUser
    val currentGeoSpot: LiveData<GeoSpot>
        get() = _geoSpot

    fun start(id:Long) {
        viewModelScope.launch {
            geoSpotRepository.allGeoSpots.collect(){
                _geoSpot.value = it[id]!!
            }
            userRepository.getUserById(id).collect {
                _currentUser.value = it[id]!!
            }
        }
    }
}

class PlayViewModelFactory(private val userRepository: UserRepository, private val geoSpotRepository: GeoSpotRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.team.hogspot.PlayActivity.PlayViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayViewModel(userRepository = userRepository, geoSpotRepository = geoSpotRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
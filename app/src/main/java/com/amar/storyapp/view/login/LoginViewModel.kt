package com.amar.storyapp.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.storyapp.data.LoginPreferences
import com.amar.storyapp.data.remote.repository.StoryRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val storyRepository: StoryRepository,
    private val preferences: LoginPreferences
) : ViewModel() {
    fun login(emailInput: String, passwordInput: String) =
        storyRepository.login(emailInput, passwordInput)

    fun saveState(token: String) {
        viewModelScope.launch {
            preferences.saveToken(token)
            preferences.login()
        }
    }
}
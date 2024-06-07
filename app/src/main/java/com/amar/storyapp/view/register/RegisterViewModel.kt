package com.amar.storyapp.view.register

import androidx.lifecycle.ViewModel
import com.amar.storyapp.data.remote.repository.StoryRepository

class RegisterViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun register(nameInput: String, emailInput: String, passwordInput: String) =
        storyRepository.register(nameInput, emailInput, passwordInput)
}
package com.amar.storyapp.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amar.storyapp.data.LoginPreferences
import com.amar.storyapp.data.remote.repository.StoryRepository
import com.amar.storyapp.di.Injection
import com.amar.storyapp.view.addstory.AddStoryViewModel
import com.amar.storyapp.view.login.LoginViewModel
import com.amar.storyapp.view.main.MainViewModel
import com.amar.storyapp.view.map.MapsViewModel
import com.amar.storyapp.view.register.RegisterViewModel

class ViewModelFactory(
    private val storyRepository: StoryRepository,
    private val preferences: LoginPreferences
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(storyRepository, preferences) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(storyRepository, preferences) as T
        }
        if (modelClass.isAssignableFrom(AddStoryViewModel::class.java)) {
            return AddStoryViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(storyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context, preferences: LoginPreferences): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context), preferences)
            }.also { INSTANCE = it }
    }
}
package com.amar.storyapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.amar.storyapp.data.LoginPreferences
import com.amar.storyapp.data.remote.repository.StoryRepository
import com.amar.storyapp.data.remote.response.ListStoryItem
import com.amar.storyapp.data.Result
import kotlinx.coroutines.launch

class MainViewModel(
    storyRepository: StoryRepository,
    private val preferences: LoginPreferences
) : ViewModel() {

    val getStories: LiveData <Result<PagingData<ListStoryItem>>> by lazy {
        storyRepository.getStoriesWithPaging(viewModelScope)
    }

    fun logout() {
        viewModelScope.launch {
            preferences.logout()
        }
    }
}
package com.amar.storyapp.view.map

import androidx.lifecycle.ViewModel
import com.amar.storyapp.data.remote.repository.StoryRepository

class MapsViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getAllStoriesWithLocation() = storyRepository.getStoriesWithLocation()

}
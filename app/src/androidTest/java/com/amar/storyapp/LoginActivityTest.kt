package com.amar.storyapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amar.storyapp.util.EspressoIdlingResource
import androidx.test.filters.LargeTest
import com.amar.storyapp.view.login.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testIfLoginSuccess() {
        Espresso.onView(withId(R.id.ed_login_email))
            .perform(ViewActions.typeText("meilyn@gmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.ed_login_password))
            .perform(ViewActions.typeText("12345678"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btn_login))
            .perform(ViewActions.click())
        Espresso.onView(withText(R.string.dialog_continue))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.action_logout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
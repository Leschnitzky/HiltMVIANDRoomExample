package com.example.roomhiltexample

import androidx.fragment.app.FragmentFactory
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.roomhiltexample.ui.fragments.LoginFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.not


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.runners.JUnit4

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@HiltAndroidTest
class MainTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
        val scenario = launchFragmentInHiltContainer<LoginFragment>(factory = FragmentFactory())

    }

    @Test
    fun checkButtonIsDisplayed() {
        onView(withId(R.id.login)).check(matches((isDisplayed())))
        onView(withId(R.id.login)).check(matches((isEnabled())))
    }

    @Test
    fun checkUsernameAndPasswordFieldsAreEmpty(){
        onView(withId(R.id.username)).check(matches(withText("")))
        onView(withId(R.id.password)).check(matches(withText("")))

    }
}
package com.example.roomhiltexample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
class MainTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun someTest() {
    }
}
package com.example.myworkout

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
} 
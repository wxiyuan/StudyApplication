package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickMainActivityButtons() {
        onView(withId(R.id.btn_insert)).perform(click())
        onView(withId(R.id.btn_load)).perform(click())
        onView(withId(R.id.btn_notification)).perform(click())
        pressBack()
        onView(withId(R.id.btn_animator)).perform(click())
        pressBack()
    }
}
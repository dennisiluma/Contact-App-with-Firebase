package com.decagon.android.sq007
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class MainActivityTest {
    @Rule
    @JvmField
    val activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_navigation_to_phoneContactActivity_returns_true() {

        Espresso.onView(withId(R.id.btPhoneContact)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.lyActivityPhoneContact))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_navigation_to_FirebaseContactActivity_returns_true() {

        Espresso.onView(withId(R.id.btCustomContact)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.lyActivityFirebaseContact))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

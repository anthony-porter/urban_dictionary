package com.apsoftware.myapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    private lateinit var searchTerm: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        searchTerm = "test"
    }

    @Test
    fun testHappyPath() {
        // Type text and then press the button.
        onView(withId(R.id.search_term_edit_text)).perform(typeText(searchTerm))
        onView(withId(R.id.submit_button)).perform(click())
        Assert.assertEquals(10, getDefinitionCount())
    }

    private fun getDefinitionCount(): Int {
        val recyclerView = activityRule.activity.findViewById(R.id.definition_recycler_view) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}
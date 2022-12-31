package com.example

import android.os.Build
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@Config(sdk = [Build.VERSION_CODES.S])
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class TestClassTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun reproducible() {
        var onLinkClickedClicked = false
        composeTestRule.setContent {
            TestComposable(onLinkClicked = { onLinkClickedClicked = true })
        }

        composeTestRule.onAllNodesWithText("License agreement", substring = true)
            .assertCountEquals(1)
        composeTestRule.onNodeWithText("License agreement", substring = true)
            .performClick()

        assertTrue(onLinkClickedClicked)
    }

}

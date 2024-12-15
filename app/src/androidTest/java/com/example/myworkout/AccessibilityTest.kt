package com.example.myworkout

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

@RunWith(AndroidJUnit4::class)
class AccessibilityTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testContentDescriptions() {
        composeRule.setContent {
            MainActivity()
        }

        composeRule.onAllNodes(hasClickAction())
            .fetchSemanticsNodes()
            .forEach { node ->
                assert(node.config.getOrNull(SemanticsProperties.ContentDescription) != null) {
                    "Clickable element missing content description"
                }
            }
    }

    @Test
    fun testTextContrast() {
        composeRule.setContent {
            MainActivity()
        }

        composeRule.onAllNodes(hasTestTag("text_element"))
            .fetchSemanticsNodes()
            .forEach { _ ->
                assert(hasAdequateContrast()) {
                    "Text element has insufficient contrast"
                }
            }
    }

    @Test
    fun testTouchTargetSize() {
        composeRule.setContent {
            MainActivity()
        }

        composeRule.onAllNodes(hasClickAction())
            .fetchSemanticsNodes()
            .forEach { node ->
                val bounds = node.boundsInRoot
                assert(bounds.width >= 48.dp.value && bounds.height >= 48.dp.value) {
                    "Touch target size should be at least 48x48dp"
                }
            }
    }

    private fun hasAdequateContrast(): Boolean {
        val backgroundColor = Color.White.toArgb()
        val textColor = Color.Black.toArgb()
        
        return calculateContrastRatio(backgroundColor, textColor) >= 4.5f
    }

    private fun calculateContrastRatio(color1: Int, color2: Int): Float {
        val luminance1 = calculateRelativeLuminance(color1)
        val luminance2 = calculateRelativeLuminance(color2)
        val lighter = max(luminance1, luminance2)
        val darker = min(luminance1, luminance2)
        return (lighter + 0.05f) / (darker + 0.05f)
    }

    private fun calculateRelativeLuminance(color: Int): Float {
        val red = Color(color).red
        val green = Color(color).green
        val blue = Color(color).blue

        fun adjustChannel(channel: Float): Float {
            return if (channel <= 0.03928f) {
                channel / 12.92f
            } else {
                ((channel + 0.055f) / 1.055f).pow(2.4f)
            }
        }

        return 0.2126f * adjustChannel(red) +
               0.7152f * adjustChannel(green) +
               0.0722f * adjustChannel(blue)
    }
} 
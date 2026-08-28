package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.PackageMode
import com.example.model.PackageNameValidator
import com.example.model.PromptGenerator
import com.example.model.WizardState
import com.example.viewmodel.AppBuilderViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("App Builder", appName)
    }

    @Test
    fun `package name validator tests`() {
        // Valid packages
        assertTrue(PackageNameValidator.validate("com.example.myapp").isValid)
        assertTrue(PackageNameValidator.validate("com.mystudio.tracker").isValid)
        assertTrue(PackageNameValidator.validate("org.education.flashcards").isValid)

        // Invalid packages
        assertFalse(PackageNameValidator.validate("").isValid)
        assertFalse(PackageNameValidator.validate("com.Example.MyApp").isValid) // uppercase
        assertFalse(PackageNameValidator.validate("com example myapp").isValid) // spaces
        assertFalse(PackageNameValidator.validate("com.my-studio.app").isValid) // hyphens
        assertFalse(PackageNameValidator.validate("singleword").isValid) // no dot
        assertFalse(PackageNameValidator.validate("com.123app.test").isValid) // segment starts with number
    }

    @Test
    fun `package generator sanitization`() {
        val pkg1 = WizardState.generateSanitizedPackageName("N Educate", "Daily Expense Tracker")
        assertEquals("com.neducate.dailyexpensetracker", pkg1)

        val pkg2 = WizardState.generateSanitizedPackageName("123 Studio!", "My Cool App #1")
        assertTrue(pkg2.startsWith("com.dev123studio.mycoolapp1"))
    }

    @Test
    fun `prompt generator builds complete prompt`() {
        val state = WizardState(
            appName = "FitTimer",
            ideaMode = "choose",
            selectedIdea = "Workout Timer",
            packageMode = PackageMode.ENTER_MY_OWN,
            manualPackageName = "com.fitstudio.timer",
            selectedFeatures = setOf("Start timer", "Pause timer", "Interval countdown")
        )
        val prompt = PromptGenerator.generatePrompt(state)
        assertTrue(prompt.contains("Build a native Android app called \"FitTimer\"."))
        assertTrue(prompt.contains("Package name: com.fitstudio.timer"))
        assertTrue(prompt.contains("App purpose:"))
        assertTrue(prompt.contains("Core features:"))
        assertTrue(prompt.contains("Data and connectivity requirements:"))
        assertTrue(prompt.contains("Completion requirement:"))
    }
}

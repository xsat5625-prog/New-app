package com.example.model

object PromptGenerator {

    fun generatePrompt(state: WizardState): String {
        val appName = state.appName.ifBlank { "My Android App" }
        val packageName = state.effectivePackageName.ifBlank { "com.example.myapp" }
        val appPurpose = state.effectiveIdea.ifBlank { "A helpful, modern Android application." }
        
        val allFeatures = (state.selectedFeatures + state.customFeatures).distinct()
        val featureList = if (allFeatures.isNotEmpty()) {
            allFeatures.mapIndexed { index, feat -> "${index + 1}. $feat" }.joinToString("\n")
        } else {
            "1. Main core dashboard with intuitive controls\n2. Add, view, edit, and manage entries\n3. Local data persistence and instant search"
        }

        val dataConnectivitySection = when (state.appType) {
            AppType.OFFLINE -> {
                buildString {
                    appendLine("Data and connectivity requirements:")
                    appendLine("- Architecture: Offline-first native Android application.")
                    appendLine("- Local Persistence: Use modern on-device storage (such as Room Database or Jetpack DataStore) so all user entries, settings, and progress survive app restarts and process recreation.")
                    appendLine("- Offline Reliability: All features, calculations, and interactions must work 100% reliably with zero internet connection required.")
                }
            }
            AppType.ONLINE -> {
                buildString {
                    appendLine("Data and connectivity requirements:")
                    appendLine("- Architecture: Online / Cloud database-powered Android application.")
                    val onlineReqs = mutableListOf<String>()
                    if (state.onlineFeatures.isNotEmpty()) {
                        state.onlineFeatures.forEach { feat ->
                            onlineReqs.add("- Online capability: $feat")
                        }
                    }
                    if (state.otherOnlineFeature.isNotBlank()) {
                        onlineReqs.add("- Custom online requirement: ${state.otherOnlineFeature.trim()}")
                    }
                    if (onlineReqs.isEmpty()) {
                        onlineReqs.add("- Cloud data synchronization and multi-device persistence.")
                    }
                    onlineReqs.forEach { appendLine(it) }
                    appendLine("- Security & Configuration: Do not hardcode API keys or secret tokens. Implement robust offline fallbacks, loading spinners during network requests, and clear error notifications if connectivity fails.")
                }
            }
            AppType.AI_API -> {
                buildString {
                    appendLine("Data and connectivity requirements:")
                    appendLine("- Architecture: AI / API-Powered Android application integrating ${state.apiType}.")
                    if (state.apiType.contains("Gemini", ignoreCase = true)) {
                        appendLine("- AI Integration: Integrate the Gemini API securely.")
                        if (state.geminiCapabilities.isNotEmpty()) {
                            appendLine("- Gemini capabilities:")
                            state.geminiCapabilities.forEach { cap ->
                                appendLine("  * $cap")
                            }
                        }
                        if (state.customApiDescription.isNotBlank()) {
                            appendLine("- AI behavior details: ${state.customApiDescription.trim()}")
                        }
                    } else {
                        appendLine("- API Integration: Connect to ${state.apiType}.")
                        if (state.customApiDescription.isNotBlank()) {
                            appendLine("- API requirements: ${state.customApiDescription.trim()}")
                        }
                    }
                    appendLine("- Safe Credentials: Never include hardcoded API keys or placeholder secrets. Use appropriate secure configuration (such as BuildConfig or environment variables via the AI Studio Secrets panel).")
                    appendLine("- Network UX: Provide smooth loading states, timeout handling, retry options, and clear error feedback during API calls.")
                }
            }
        }

        // Domain-specific smart enhancements
        val domainEnhancements = getDomainEnhancement(appPurpose, state.appType)

        return buildString {
            appendLine("Build a native Android app called \"$appName\".")
            appendLine("Package name: $packageName")
            appendLine()
            appendLine("App purpose:")
            appendLine(appPurpose)
            appendLine()
            appendLine("Core features:")
            appendLine(featureList)
            appendLine()
            append(dataConnectivitySection)
            appendLine()
            if (domainEnhancements.isNotBlank()) {
                appendLine("Specialized implementation requirements:")
                appendLine(domainEnhancements)
                appendLine()
            }
            appendLine("User interface:")
            appendLine("- Request a modern, clean, intuitive Android interface built with Jetpack Compose and Material Design 3.")
            appendLine("- Use clear visual hierarchy, readable typography, proper 8dp-grid spacing, touch-friendly controls (min 48dp touch targets), elevation cards where appropriate, and consistent visual styling.")
            appendLine("- The app should feel like a real Android application rather than a webpage placed inside a phone screen.")
            appendLine("- Include full support for Edge-to-Edge display and dynamic theme styling.")
            appendLine()
            appendLine("Navigation:")
            appendLine("- Define sensible navigation according to the selected features (e.g., single-activity with clean Compose navigation or tabbed bottom navigation if multiple distinct modules exist).")
            appendLine("- Avoid unnecessary screens or confusing deep navigation.")
            appendLine()
            appendLine("User experience:")
            appendLine("- Include useful empty states with friendly illustrations/prompts when no items exist yet.")
            appendLine("- Provide input validation, confirmation dialogs for destructive actions (like clearing data), responsive loading states where needed, and clear beginner-friendly error messages.")
            appendLine()
            appendLine("Android implementation:")
            appendLine("- Build the project as a proper Android application in Kotlin using Jetpack Compose.")
            appendLine("- Preserve the exact package name ($packageName) and app name ($appName) supplied above.")
            appendLine("- Do not silently change the package name or app name.")
            appendLine("- Keep the implementation focused on the requested features.")
            appendLine("- Do not add unrelated features.")
            appendLine()
            appendLine("Completion requirement:")
            appendLine("When finished, confirm the app name ($appName), package name ($packageName), major features implemented, and whether the project is offline, online/database-powered, or AI/API-powered.")
        }.trim()
    }

    private fun getDomainEnhancement(idea: String, appType: AppType): String {
        val lower = idea.lowercase()
        return when {
            lower.contains("timer") || lower.contains("pomodoro") || lower.contains("stopwatch") -> {
                "- Audio & Haptics: Trigger clear vibration and audio chime when timers finish.\n- State Preservation: Timer state and elapsed seconds must continue accurately when the app is backgrounded or rotated."
            }
            lower.contains("expense") || lower.contains("budget") || lower.contains("finance") -> {
                "- Financial Calculations: Ensure accurate decimal math for currency amounts and category breakdowns.\n- Visual Insights: Render clear, responsive visual progress bars or summary cards for monthly spending vs budget limits."
            }
            lower.contains("quiz") || lower.contains("flashcard") || lower.contains("study") -> {
                "- Study Logic: Include smooth interactive card flipping, scoring calculations, randomized question shuffling, and a completion result summary with mastery score."
            }
            lower.contains("habit") || lower.contains("routine") -> {
                "- Streak Tracking: Compute consecutive daily streaks based on calendar dates without timezone bugs.\n- Quick Interaction: Enable 1-tap completion toggles right from the home list."
            }
            lower.contains("task") || lower.contains("todo") || lower.contains("planner") -> {
                "- Task Ergonomics: Support quick task creation, checkbox completion with visual feedback, and category filtering (All, Today, Completed)."
            }
            lower.contains("water") || lower.contains("hydration") -> {
                "- Hydration Ergonomics: Quick-tap buttons for standard glass sizes and an interactive visual gauge representing progress toward the daily goal."
            }
            else -> ""
        }
    }
}

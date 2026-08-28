package com.example.model

enum class AppType(val title: String, val subtitle: String) {
    OFFLINE(
        title = "Offline App",
        subtitle = "Works completely on the phone without requiring internet connection."
    ),
    ONLINE(
        title = "Online / Database App",
        subtitle = "Needs user accounts, cloud database, remote sync, or shared data."
    ),
    AI_API(
        title = "AI / API-Powered App",
        subtitle = "Uses an AI model (like Gemini) or an external web API."
    )
}

enum class PackageMode {
    GENERATE_FOR_ME,
    ENTER_MY_OWN
}

data class CategoryIdeas(
    val categoryId: String,
    val categoryName: String,
    val iconName: String,
    val initialIdeas: List<String>,
    val moreIdeas: List<String>,
    val defaultFeatures: List<String>
)

data class WizardState(
    // Step navigation
    val currentStep: Int = 0, // 0 = Home, 1..6 = Wizard, 7 = Final Prompt
    
    // Step 1: Idea
    val ideaMode: String = "choose", // "type" or "choose"
    val typedIdea: String = "",
    val selectedCategory: String = "Productivity",
    val selectedIdea: String = "",
    val showMoreIdeas: Boolean = false,
    
    // Step 2: Name
    val appName: String = "",
    val suggestedNames: List<String> = emptyList(),
    
    // Step 3: Architecture / Type
    val appType: AppType = AppType.OFFLINE,
    val onlineFeatures: Set<String> = emptySet(),
    val otherOnlineFeature: String = "",
    val apiType: String = "Gemini API",
    val geminiCapabilities: Set<String> = emptySet(),
    val customApiDescription: String = "",
    
    // Step 4: Features
    val availableFeatures: List<String> = emptyList(),
    val selectedFeatures: Set<String> = emptySet(),
    val customFeatureInput: String = "",
    val customFeatures: List<String> = emptyList(),
    
    // Step 5: Package Name
    val packageMode: PackageMode = PackageMode.GENERATE_FOR_ME,
    val creatorName: String = "My Studio",
    val manualPackageName: String = "com.mystudio.myapp",
    val packageValidationError: String? = null,
    
    // UI state
    val copiedToClipboard: Boolean = false
) {
    val effectiveIdea: String
        get() = if (ideaMode == "type") typedIdea.trim() else selectedIdea.trim()

    val effectivePackageName: String
        get() = if (packageMode == PackageMode.GENERATE_FOR_ME) {
            generateSanitizedPackageName(creatorName, appName.ifBlank { effectiveIdea })
        } else {
            manualPackageName.trim().lowercase()
        }

    companion object {
        fun generateSanitizedPackageName(creator: String, app: String): String {
            val sanitizedCreator = creator.lowercase()
                .replace(Regex("[^a-z0-9]"), "")
                .let { if (it.isEmpty() || it.first().isDigit()) "dev$it" else it }
            
            val sanitizedApp = app.lowercase()
                .replace(Regex("[^a-z0-9]"), "")
                .let { if (it.isEmpty() || it.first().isDigit()) "app$it" else it }
                .take(20)

            val safeCreator = if (sanitizedCreator.isBlank()) "app" else sanitizedCreator.take(16)
            val safeApp = if (sanitizedApp.isBlank()) "project" else sanitizedApp

            return "com.$safeCreator.$safeApp"
        }
    }
}

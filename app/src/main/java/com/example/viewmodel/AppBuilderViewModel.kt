package com.example.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.AppCatalogData
import com.example.model.AppType
import com.example.model.PackageMode
import com.example.model.PackageNameValidator
import com.example.model.PromptGenerator
import com.example.model.WizardState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppBuilderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WizardState())
    val uiState: StateFlow<WizardState> = _uiState.asStateFlow()

    init {
        // Initialize default category and suggestions
        val defaultCat = AppCatalogData.categories.first()
        val defaultIdea = defaultCat.initialIdeas.first()
        val defaultFeatures = AppCatalogData.getSuggestedFeaturesForIdea(defaultIdea, defaultCat.categoryName)
        val defaultNames = AppCatalogData.generateNameSuggestions(defaultIdea, defaultCat.categoryName)

        _uiState.update { state ->
            state.copy(
                selectedCategory = defaultCat.categoryName,
                selectedIdea = defaultIdea,
                appName = defaultNames.firstOrNull() ?: "Daily Task Planner",
                suggestedNames = defaultNames,
                availableFeatures = defaultFeatures,
                selectedFeatures = defaultFeatures.take(6).toSet(),
                manualPackageName = WizardState.generateSanitizedPackageName("mystudio", defaultNames.firstOrNull() ?: "planner")
            )
        }
    }

    // Step navigation
    fun startBuilding() {
        _uiState.update { it.copy(currentStep = 1) }
    }

    fun nextStep() {
        _uiState.update { state ->
            val next = (state.currentStep + 1).coerceAtMost(7)
            state.copy(currentStep = next)
        }
    }

    fun previousStep() {
        _uiState.update { state ->
            val prev = (state.currentStep - 1).coerceAtLeast(0)
            state.copy(currentStep = prev)
        }
    }

    fun goToStep(step: Int) {
        _uiState.update { it.copy(currentStep = step.coerceIn(0, 7)) }
    }

    fun resetWizard() {
        val defaultCat = AppCatalogData.categories.first()
        val defaultIdea = defaultCat.initialIdeas.first()
        val defaultFeatures = AppCatalogData.getSuggestedFeaturesForIdea(defaultIdea, defaultCat.categoryName)
        val defaultNames = AppCatalogData.generateNameSuggestions(defaultIdea, defaultCat.categoryName)

        _uiState.value = WizardState(
            currentStep = 0,
            selectedCategory = defaultCat.categoryName,
            selectedIdea = defaultIdea,
            appName = defaultNames.firstOrNull() ?: "Daily Task Planner",
            suggestedNames = defaultNames,
            availableFeatures = defaultFeatures,
            selectedFeatures = defaultFeatures.take(6).toSet(),
            creatorName = "My Studio",
            manualPackageName = WizardState.generateSanitizedPackageName("mystudio", defaultNames.firstOrNull() ?: "planner")
        )
    }

    // STEP 1: Idea
    fun setIdeaMode(mode: String) {
        _uiState.update { state ->
            val updated = state.copy(ideaMode = mode)
            refreshSuggestions(updated)
        }
    }

    fun updateTypedIdea(idea: String) {
        _uiState.update { state ->
            val updated = state.copy(typedIdea = idea)
            refreshSuggestions(updated)
        }
    }

    fun selectCategory(categoryName: String) {
        val cat = AppCatalogData.getCategory(categoryName)
        val idea = cat.initialIdeas.firstOrNull() ?: ""
        _uiState.update { state ->
            val updated = state.copy(
                selectedCategory = categoryName,
                selectedIdea = idea,
                showMoreIdeas = false
            )
            refreshSuggestions(updated)
        }
    }

    fun selectIdea(idea: String) {
        _uiState.update { state ->
            val updated = state.copy(selectedIdea = idea)
            refreshSuggestions(updated)
        }
    }

    fun updateSelectedIdea(edited: String) {
        _uiState.update { state ->
            val updated = state.copy(selectedIdea = edited)
            refreshSuggestions(updated)
        }
    }

    fun toggleShowMoreIdeas() {
        _uiState.update { it.copy(showMoreIdeas = !it.showMoreIdeas) }
    }

    private fun refreshSuggestions(state: WizardState): WizardState {
        val idea = state.effectiveIdea
        val cat = state.selectedCategory
        val names = AppCatalogData.generateNameSuggestions(idea, cat)
        val features = AppCatalogData.getSuggestedFeaturesForIdea(idea, cat)
        
        // Preserve current selection if present, else pick top suggested name
        val currentName = if (state.appName.isNotBlank() && state.appName in state.suggestedNames) {
            state.appName
        } else {
            names.firstOrNull() ?: state.appName
        }

        val updatedPkg = if (state.packageMode == PackageMode.GENERATE_FOR_ME) {
            WizardState.generateSanitizedPackageName(state.creatorName, currentName)
        } else {
            state.manualPackageName
        }

        return state.copy(
            suggestedNames = names,
            availableFeatures = features,
            selectedFeatures = if (state.selectedFeatures.isEmpty()) features.take(6).toSet() else state.selectedFeatures,
            appName = currentName,
            manualPackageName = updatedPkg
        )
    }

    // STEP 2: Name
    fun updateAppName(name: String) {
        _uiState.update { state ->
            val updatedPkg = if (state.packageMode == PackageMode.GENERATE_FOR_ME) {
                WizardState.generateSanitizedPackageName(state.creatorName, name)
            } else {
                state.manualPackageName
            }
            state.copy(appName = name, manualPackageName = updatedPkg)
        }
    }

    fun selectSuggestedName(name: String) {
        updateAppName(name)
    }

    // STEP 3: How should the app work
    fun setAppType(type: AppType) {
        _uiState.update { it.copy(appType = type) }
    }

    fun toggleOnlineFeature(feature: String) {
        _uiState.update { state ->
            val current = state.onlineFeatures.toMutableSet()
            if (current.contains(feature)) current.remove(feature) else current.add(feature)
            state.copy(onlineFeatures = current)
        }
    }

    fun updateOtherOnlineFeature(text: String) {
        _uiState.update { it.copy(otherOnlineFeature = text) }
    }

    fun setApiType(api: String) {
        _uiState.update { it.copy(apiType = api) }
    }

    fun toggleGeminiCapability(cap: String) {
        _uiState.update { state ->
            val current = state.geminiCapabilities.toMutableSet()
            if (current.contains(cap)) current.remove(cap) else current.add(cap)
            state.copy(geminiCapabilities = current)
        }
    }

    fun updateCustomApiDescription(text: String) {
        _uiState.update { it.copy(customApiDescription = text) }
    }

    // STEP 4: Features
    fun toggleFeature(feature: String) {
        _uiState.update { state ->
            val current = state.selectedFeatures.toMutableSet()
            if (current.contains(feature)) current.remove(feature) else current.add(feature)
            state.copy(selectedFeatures = current)
        }
    }

    fun updateCustomFeatureInput(text: String) {
        _uiState.update { it.copy(customFeatureInput = text) }
    }

    fun addCustomFeature() {
        val input = _uiState.value.customFeatureInput.trim()
        if (input.isNotBlank()) {
            _uiState.update { state ->
                val current = state.customFeatures.toMutableList()
                if (!current.contains(input)) {
                    current.add(input)
                }
                val selected = state.selectedFeatures.toMutableSet()
                selected.add(input)
                state.copy(
                    customFeatures = current,
                    selectedFeatures = selected,
                    customFeatureInput = ""
                )
            }
        }
    }

    fun removeCustomFeature(feature: String) {
        _uiState.update { state ->
            val current = state.customFeatures.toMutableList()
            current.remove(feature)
            val selected = state.selectedFeatures.toMutableSet()
            selected.remove(feature)
            state.copy(customFeatures = current, selectedFeatures = selected)
        }
    }

    // STEP 5: Package Name
    fun setPackageMode(mode: PackageMode) {
        _uiState.update { state ->
            val updated = state.copy(packageMode = mode)
            if (mode == PackageMode.ENTER_MY_OWN) {
                val validation = PackageNameValidator.validate(state.manualPackageName)
                updated.copy(packageValidationError = validation.errorMessage)
            } else {
                updated.copy(packageValidationError = null)
            }
        }
    }

    fun updateCreatorName(creator: String) {
        _uiState.update { state ->
            val sanitizedPkg = WizardState.generateSanitizedPackageName(creator, state.appName.ifBlank { state.effectiveIdea })
            state.copy(creatorName = creator, manualPackageName = sanitizedPkg)
        }
    }

    fun updateManualPackageName(pkg: String) {
        val validation = PackageNameValidator.validate(pkg)
        _uiState.update { state ->
            state.copy(
                manualPackageName = pkg,
                packageValidationError = validation.errorMessage
            )
        }
    }

    // Prompt & Clipboard
    fun generateFinalPrompt(): String {
        return PromptGenerator.generatePrompt(_uiState.value)
    }

    fun copyPromptToClipboard(context: Context, prompt: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("AI Studio Prompt", prompt)
        clipboard.setPrimaryClip(clip)

        _uiState.update { it.copy(copiedToClipboard = true) }
        viewModelScope.launch {
            delay(2500)
            _uiState.update { it.copy(copiedToClipboard = false) }
        }
    }
}

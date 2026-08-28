package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.FinalPromptScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.Step1IdeaScreen
import com.example.ui.screens.Step2NameScreen
import com.example.ui.screens.Step3TypeScreen
import com.example.ui.screens.Step4FeaturesScreen
import com.example.ui.screens.Step5PackageScreen
import com.example.ui.screens.Step6ReviewScreen
import com.example.viewmodel.AppBuilderViewModel

@Composable
fun AppBuilderApp(
    viewModel: AppBuilderViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Handle system back navigation
    BackHandler(enabled = state.currentStep > 0) {
        viewModel.previousStep()
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AnimatedContent(
            targetState = state.currentStep,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                        slideOutHorizontally { width -> -width } + fadeOut()
                    )
                } else {
                    (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                        slideOutHorizontally { width -> width } + fadeOut()
                    )
                }
            },
            label = "wizardStepAnimation"
        ) { step ->
            when (step) {
                0 -> HomeScreen(
                    onStartBuilding = { viewModel.startBuilding() }
                )
                1 -> Step1IdeaScreen(
                    state = state,
                    onIdeaModeChanged = { viewModel.setIdeaMode(it) },
                    onTypedIdeaChanged = { viewModel.updateTypedIdea(it) },
                    onCategorySelected = { viewModel.selectCategory(it) },
                    onIdeaSelected = { viewModel.selectIdea(it) },
                    onIdeaEdited = { viewModel.updateSelectedIdea(it) },
                    onToggleShowMore = { viewModel.toggleShowMoreIdeas() },
                    onBackClick = { viewModel.previousStep() },
                    onContinueClick = { viewModel.nextStep() }
                )
                2 -> Step2NameScreen(
                    state = state,
                    onNameChanged = { viewModel.updateAppName(it) },
                    onSelectSuggestedName = { viewModel.selectSuggestedName(it) },
                    onBackClick = { viewModel.previousStep() },
                    onContinueClick = { viewModel.nextStep() }
                )
                3 -> Step3TypeScreen(
                    state = state,
                    onAppTypeChanged = { viewModel.setAppType(it) },
                    onToggleOnlineFeature = { viewModel.toggleOnlineFeature(it) },
                    onOtherOnlineFeatureChanged = { viewModel.updateOtherOnlineFeature(it) },
                    onApiTypeChanged = { viewModel.setApiType(it) },
                    onToggleGeminiCapability = { viewModel.toggleGeminiCapability(it) },
                    onCustomApiDescriptionChanged = { viewModel.updateCustomApiDescription(it) },
                    onBackClick = { viewModel.previousStep() },
                    onContinueClick = { viewModel.nextStep() }
                )
                4 -> Step4FeaturesScreen(
                    state = state,
                    onToggleFeature = { viewModel.toggleFeature(it) },
                    onCustomFeatureInputChanged = { viewModel.updateCustomFeatureInput(it) },
                    onAddCustomFeature = { viewModel.addCustomFeature() },
                    onRemoveCustomFeature = { viewModel.removeCustomFeature(it) },
                    onBackClick = { viewModel.previousStep() },
                    onContinueClick = { viewModel.nextStep() }
                )
                5 -> Step5PackageScreen(
                    state = state,
                    onPackageModeChanged = { viewModel.setPackageMode(it) },
                    onCreatorNameChanged = { viewModel.updateCreatorName(it) },
                    onManualPackageNameChanged = { viewModel.updateManualPackageName(it) },
                    onBackClick = { viewModel.previousStep() },
                    onContinueClick = { viewModel.nextStep() }
                )
                6 -> Step6ReviewScreen(
                    state = state,
                    onJumpToStep = { viewModel.goToStep(it) },
                    onBackClick = { viewModel.previousStep() },
                    onGeneratePromptClick = { viewModel.nextStep() }
                )
                7 -> FinalPromptScreen(
                    state = state,
                    generatedPrompt = viewModel.generateFinalPrompt(),
                    onCopyPrompt = {
                        viewModel.copyPromptToClipboard(context, viewModel.generateFinalPrompt())
                    },
                    onStartOver = { viewModel.resetWizard() },
                    onBackToReview = { viewModel.goToStep(6) }
                )
                else -> HomeScreen(
                    onStartBuilding = { viewModel.startBuilding() }
                )
            }
        }
    }
}

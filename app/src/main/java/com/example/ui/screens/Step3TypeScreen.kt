package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SignalCellularConnectedNoInternet0Bar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppType
import com.example.model.WizardState
import com.example.ui.components.InfoBanner
import com.example.ui.components.SelectionCard
import com.example.ui.components.StepProgressHeader
import com.example.ui.components.WizardNavButtons

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Step3TypeScreen(
    state: WizardState,
    onAppTypeChanged: (AppType) -> Unit,
    onToggleOnlineFeature: (String) -> Unit,
    onOtherOnlineFeatureChanged: (String) -> Unit,
    onApiTypeChanged: (String) -> Unit,
    onToggleGeminiCapability: (String) -> Unit,
    onCustomApiDescriptionChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val onlineFeatureOptions = listOf(
        "User signup and login",
        "Cloud database",
        "Sync between devices",
        "User profiles",
        "Upload images/files",
        "Online content",
        "Other"
    )

    val apiTypeOptions = listOf(
        "Gemini API",
        "Other AI API",
        "Weather API",
        "Maps/location API",
        "Custom REST API",
        "Other API"
    )

    val geminiCapabilities = listOf(
        "Generate text",
        "Answer questions",
        "Summarize information",
        "Generate ideas",
        "Analyze user input",
        "Create recommendations"
    )

    Scaffold(
        topBar = {
            StepProgressHeader(
                currentStep = 3,
                totalSteps = 6,
                stepTitle = "How Should the App Work?",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            WizardNavButtons(
                onBack = onBackClick,
                onContinue = onContinueClick,
                continueEnabled = true,
                continueText = "Continue"
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Heading
            Text(
                text = "How should your Android app store and use information?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Select how your app handles data, accounts, or external services.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3 Large Selectable Cards
            SelectionCard(
                title = "Option 1 — Offline App",
                subtitle = "Choose this if you want the app to work mainly on the phone without requiring an internet connection.",
                isSelected = state.appType == AppType.OFFLINE,
                onClick = { onAppTypeChanged(AppType.OFFLINE) },
                icon = Icons.Default.PhoneAndroid,
                badgeText = "Beginner Recommended",
                examples = listOf("Notes", "Timers", "Calculators", "Habit trackers", "Expense trackers", "Personal tools")
            )

            Spacer(modifier = Modifier.height(14.dp))

            SelectionCard(
                title = "Option 2 — Online / Database App",
                subtitle = "Choose this if your app needs online accounts, cloud data, syncing, or a remote database.",
                isSelected = state.appType == AppType.ONLINE,
                onClick = { onAppTypeChanged(AppType.ONLINE) },
                icon = Icons.Default.Cloud,
                badgeText = "Cloud Powered",
                examples = listOf("User accounts", "Shared data", "Cloud syncing", "Online dashboards", "Multi-device data")
            )

            // Online sub-options
            AnimatedVisibility(visible = state.appType == AppType.ONLINE) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "What online features do you need?",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Select all that apply:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        onlineFeatureOptions.forEach { opt ->
                            val isChecked = state.onlineFeatures.contains(opt)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onToggleOnlineFeature(opt) }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { onToggleOnlineFeature(opt) },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = opt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = state.otherOnlineFeature,
                            onValueChange = onOtherOnlineFeatureChanged,
                            label = { Text("Describe any other online feature (optional)") },
                            placeholder = { Text("e.g., Push notifications, multi-user real-time chat") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("other_online_feature_input"),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SelectionCard(
                title = "Option 3 — AI / API-Powered App",
                subtitle = "Choose this if your app will use an AI model or another online API.",
                isSelected = state.appType == AppType.AI_API,
                onClick = { onAppTypeChanged(AppType.AI_API) },
                icon = Icons.Default.AutoAwesome,
                badgeText = "AI Enhanced",
                examples = listOf("Gemini AI Assistant", "Weather Forecasts", "Location Maps", "Smart Summaries")
            )

            // AI/API sub-options
            AnimatedVisibility(visible = state.appType == AppType.AI_API) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "What kind of API or AI feature will your app use?",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            apiTypeOptions.forEach { apiOpt ->
                                val isSelected = state.apiType == apiOpt
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onApiTypeChanged(apiOpt) },
                                    label = { Text(apiOpt, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                    leadingIcon = {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                )
                            }
                        }

                        if (state.apiType == "Gemini API") {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "What should Gemini do inside the app?",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            geminiCapabilities.forEach { cap ->
                                val isChecked = state.geminiCapabilities.contains(cap)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onToggleGeminiCapability(cap) }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { onToggleGeminiCapability(cap) },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = cap,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = state.customApiDescription,
                            onValueChange = onCustomApiDescriptionChanged,
                            label = { Text("Custom AI or API behavior description (optional)") },
                            placeholder = { Text("e.g. Generate personalized daily meal recommendations based on user calories") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("custom_api_description_input"),
                            shape = RoundedCornerShape(10.dp),
                            minLines = 2
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            InfoBanner(
                title = "Security & Secrets Protection",
                text = "The generated Android prompt will instruct Google AI Studio to handle all API credentials securely via runtime secrets without hardcoding keys in code."
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

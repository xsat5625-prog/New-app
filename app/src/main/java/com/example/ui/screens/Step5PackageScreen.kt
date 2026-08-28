package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PackageMode
import com.example.model.WizardState
import com.example.ui.components.InfoBanner
import com.example.ui.components.SelectionCard
import com.example.ui.components.StepProgressHeader
import com.example.ui.components.WizardNavButtons

@Composable
fun Step5PackageScreen(
    state: WizardState,
    onPackageModeChanged: (PackageMode) -> Unit,
    onCreatorNameChanged: (String) -> Unit,
    onManualPackageNameChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    
    val isContinueEnabled = if (state.packageMode == PackageMode.GENERATE_FOR_ME) {
        state.effectivePackageName.isNotBlank()
    } else {
        state.packageValidationError == null && state.manualPackageName.isNotBlank()
    }

    Scaffold(
        topBar = {
            StepProgressHeader(
                currentStep = 5,
                totalSteps = 6,
                stepTitle = "Choose Package Name",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            WizardNavButtons(
                onBack = onBackClick,
                onContinue = onContinueClick,
                continueEnabled = isContinueEnabled,
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
                text = "Choose your Android package name",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "The package name is the unique ID used by Android for your app. It looks like reverse web domains (e.g. com.example.myapp).",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Two main choices
            SelectionCard(
                title = "Generate One for Me",
                subtitle = "Enter your creator/channel name and we'll automatically generate a clean, valid Android package ID.",
                isSelected = state.packageMode == PackageMode.GENERATE_FOR_ME,
                onClick = { onPackageModeChanged(PackageMode.GENERATE_FOR_ME) },
                icon = Icons.Default.AutoAwesome,
                badgeText = "Recommended for Beginners"
            )

            AnimatedVisibility(visible = state.packageMode == PackageMode.GENERATE_FOR_ME) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Creator, Brand, or Developer Name:",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = state.creatorName,
                            onValueChange = onCreatorNameChanged,
                            placeholder = { Text("e.g. N Educate, John Doe, SparkStudio") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("creator_name_input"),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Automatically sanitized to reverse-domain: ${state.effectivePackageName}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SelectionCard(
                title = "Enter My Own Package Name",
                subtitle = "Type a custom reverse-domain identifier (e.g. com.company.appname).",
                isSelected = state.packageMode == PackageMode.ENTER_MY_OWN,
                onClick = { onPackageModeChanged(PackageMode.ENTER_MY_OWN) },
                icon = Icons.Default.Edit
            )

            AnimatedVisibility(visible = state.packageMode == PackageMode.ENTER_MY_OWN) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Custom Package Name:",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = state.manualPackageName,
                            onValueChange = onManualPackageNameChanged,
                            placeholder = { Text("com.yourdomain.appname") },
                            isError = state.packageValidationError != null,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("manual_package_input"),
                            shape = RoundedCornerShape(10.dp)
                        )

                        if (state.packageValidationError != null) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.ErrorOutline,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = state.packageValidationError,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Selected Package Name Card
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selected Package Name",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = state.effectivePackageName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            InfoBanner(
                title = "Android Package Rules",
                text = "Package names must be all lowercase, contain no spaces or special symbols, use dot separators (like com.domain.app), and start each segment with a letter."
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

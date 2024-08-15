package com.easyhz.noffice.feature.organization.component.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.textField.MainTextField
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationState.Companion.ORGANIZATION_NAME_MAX
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationViewModel
import com.easyhz.noffice.feature.organization.util.creation.CreationStep

@Composable
internal fun OrganizationNameView(
    modifier: Modifier = Modifier,
    viewModel: OrganizationCreationViewModel = hiltViewModel(),
    creationStep: CreationStep,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        CommonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            creationStep = creationStep
        )
        MainTextField(
            modifier = Modifier.weight(0.5f),
            value = uiState.organizationName,
            onValueChange = { viewModel.postIntent(CreationIntent.ChangeOrganizationNameTextValue(it)) },
            title = null,
            placeholder = stringResource(id = R.string.organization_creation_name_placeholder),
            isFilled = false,
            singleLine = true,
            maxCount = ORGANIZATION_NAME_MAX,
            onClickIcon =  { viewModel.postIntent(CreationIntent.ClearOrganizationName) }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Transparent)
            .noRippleClickable { viewModel.postIntent(CreationIntent.ClearFocus) }
        )
        MediumButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.next_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(CreationIntent.ClickNextButton)
        }
    }
}
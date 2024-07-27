package com.easyhz.noffice.feature.announcement.screen.creation

import android.view.View
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.CreationState
import com.easyhz.noffice.feature.announcement.contract.creation.Options
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.SelectionDateTimeState
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactState
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor(

) : BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    override fun handleIntent(intent: CreationIntent) {
        when(intent) {
            is CreationIntent.ClickBackButton -> { onClickBackButton() }
            is CreationIntent.ClickNextButton -> { onClickNextButton() }
            is CreationIntent.SelectedOrganization -> { onSelectedOrganization(organization = intent.organization) }
            is CreationIntent.ChangeTitleTextValue -> { onChangeTitleTextValue(intent.newText) }
            is CreationIntent.ChangeContentTextValue -> { onChangeContentTextValue(intent.newText) }
            is CreationIntent.ClickOptionButton -> { onClickOptionButton(intent.option) }
            is CreationIntent.SaveOptionData<*> -> { onSaveOptionData(intent.data) }
            is CreationIntent.GloballyPositioned -> { onGloballyPositioned(intent.view) }
            is CreationIntent.SetLayoutResult -> { setLayoutResult(intent.layoutResult) }
            is CreationIntent.ChangedFocus -> { onChangeFocus(intent.hasFocus) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    private fun onClickNextButton() {
        postSideEffect { CreationSideEffect.NavigateToNext }
    }

    private fun onSelectedOrganization(organization: String) {
        reduce { copy(selectedOrganization = organization) }
    }

    private fun onChangeTitleTextValue(newText: String) {
        reduce { copy(title = newText) }
    }

    private fun onChangeContentTextValue(newText: TextFieldValue) {
        reduce { copy(content = newText) }
    }

    private fun onClickOptionButton(option: Options) {
        when (option) {
            Options.DATE_TIME -> { navigateToDateTime() }
            Options.PLACE -> { navigateToPlace() }
            Options.TASK -> { }
            Options.NOTIFICATION -> { }
        }
    }

    private fun navigateToDateTime() {
        val dateTimeState = currentState.getOptionValue<SelectionDateTimeState>(Options.DATE_TIME)
        val (date, time) = dateTimeState?.let { state ->
            state.date.toString() to state.time.toString()
        } ?: (null to null)
        postSideEffect {
            CreationSideEffect.NavigateToDateTime(date, time)
        }
    }

    private fun navigateToPlace() {
        val contactState = currentState.getOptionValue<ContactState>(Options.PLACE)
        val (contactType, title, url) = contactState?.let { state ->
            Triple(
                state.contactType.name.takeIf { it.isNotBlank() },
                state.title.takeIf { it.isNotBlank() },
                state.url.takeIf { it.isNotBlank() }
            )
        } ?: Triple(null, null, null)
        postSideEffect { CreationSideEffect.NavigateToPlace(contactType, title, url) }
    }

    private fun <T> onSaveOptionData(data: OptionData<T>) {
        currentState.optionState[data.type] = data
    }

    private fun onGloballyPositioned(view: View) {
        val cursorRect = currentState.layoutResult?.getCursorRect(currentState.content.selection.end)
        if (cursorRect != null && currentState.cursorOffset.y != cursorRect.topLeft.y) {
            reduce { copy(cursorOffset = cursorRect.topLeft) }
        }
        val windowPos = IntArray(2)
        view.getLocationInWindow(windowPos)
        val absoluteCursorPos =
            currentState.cursorOffset + Offset(windowPos[0].toFloat(), windowPos[1].toFloat())
        if (currentState.absoluteCursorY == absoluteCursorPos.y.toInt()) return
        reduce { copy(absoluteCursorY = absoluteCursorPos.y.toInt()) }
    }

    private fun setLayoutResult(textLayoutResult: TextLayoutResult) {
        reduce { copy(layoutResult = textLayoutResult) }
    }

    private fun onChangeFocus(hasFocus: Boolean) {
        reduce { copy(isFocused = hasFocus, isMoved = !hasFocus) }
    }
}
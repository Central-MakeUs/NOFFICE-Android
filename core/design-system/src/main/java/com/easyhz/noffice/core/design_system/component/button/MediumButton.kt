package com.easyhz.noffice.core.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.White

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    contentColor: Color = White,
    containerColor: Color = Green500,
    onClick: () -> Unit
) {
    val onClickInvoke: () -> Unit = remember(enabled, onClick) {
        if (enabled) onClick else { { } }
    }
    val backgroundColor = remember(enabled, containerColor) {
        if (enabled) containerColor else Grey100
    }
    val textColor = remember(enabled, contentColor) {
        if (enabled) contentColor else Grey500
    }

    Box(
        modifier = modifier
            .imePadding()
            .height(54.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .noRippleClickable { onClickInvoke() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = SemiBold16,
            color = textColor
        )
    }
}


@Preview(group = "button", name = "enabled")
@Composable
private fun MediumButtonEnabledPrev() {
    MediumButton(
        text = "다음",
        enabled = true
    ) {

    }
}

@Preview(group = "button", name = "disabled")
@Composable
private fun MediumButtonDisabledPrev() {
    MediumButton(
        text = "다음",
        enabled = false
    ) {

    }
}
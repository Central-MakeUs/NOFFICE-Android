package com.easyhz.noffice.feature.announcement.component.creation.remind

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Blue500
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.util.interaction.useInteractionWithColor

@Composable
internal fun RemindItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (interactionSource, scale, color) = useInteractionWithColor()
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .noRippleClickable(interactionSource) { onClick() }
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .padding(horizontal = 14.dp)
                .scale(scale),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = SubTitle1,

                )
            AnimatedVisibility(
                visible = isSelected,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "check",
                    tint = Blue500
                )
            }
        }
        Divider(color = Grey200, thickness = 1.dp)
    }
}
package com.easyhz.noffice.core.design_system.component.divider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey200

fun LazyListScope.divider(modifier: Modifier = Modifier) {
    item {
        Divider(
            modifier = modifier.fillMaxWidth(),
            color = Grey200,
            thickness = 1.dp
        )
    }
}
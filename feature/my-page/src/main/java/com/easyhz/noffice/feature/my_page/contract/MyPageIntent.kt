package com.easyhz.noffice.feature.my_page.contract

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.feature.my_page.util.MyPageMenu

sealed class MyPageIntent: UiIntent() {
    data object ClickBackButton: MyPageIntent()
    data object ChangeProfileImage: MyPageIntent()
    data object ChangeUserName: MyPageIntent()
    data class ClickMenuItem(val item: MyPageMenu): MyPageIntent()
    data class ClickImageBottomSheetItem(val item: ImageSelectionBottomSheetItem): MyPageIntent()
    data class PickImage(val uri: Uri?): MyPageIntent()
    data class TakePicture(val isUsed: Boolean): MyPageIntent()
    data object HideImageBottomSheet: MyPageIntent()
    data object CompleteHideBottomSheet: MyPageIntent()
}
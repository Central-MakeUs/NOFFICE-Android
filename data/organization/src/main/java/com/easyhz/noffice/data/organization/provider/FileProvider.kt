package com.easyhz.noffice.data.organization.provider

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.easyhz.noffice.data.organization.R
import com.easyhz.noffice.data.organization.constant.CacheDirectory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class FileProvider : FileProvider(R.xml.file_path) {
    companion object {
        suspend fun getTakePictureUri(context: Context): Result<Uri> = withContext(Dispatchers.IO) {
            runCatching {
                val directory =
                    File(context.cacheDir, CacheDirectory.CAMERA_IMAGES).apply { mkdirs() }
                val file = File.createTempFile(
                    CacheDirectory.CAMERA_IMAGE_PREFIX,
                    ".jpeg",
                    directory
                )
                val authority = "${context.packageName}.file_provider"
                getUriForFile(context, authority, file)
            }
        }
    }
}
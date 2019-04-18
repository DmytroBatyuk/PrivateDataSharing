package ua.dmytrobatyuk.privatedatasharing

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

private const val PDF_FILENAME = "test_pdf.pdf"
private const val JPG_FILENAME = "test_jpg.jpg"

object FileUtils {
    fun loadResources(ctx: Context) {
        loadFileInternal(ctx, R.raw.test_pdf, PDF_FILENAME)
        loadFileInternal(ctx, R.raw.test_jpg, JPG_FILENAME)
    }

    fun getShareUri(ctx: Context): Uri {
        val file = File(getAttachmentsDir(ctx), JPG_FILENAME)
        return FileProvider.getUriForFile(ctx, BuildConfig.APPLICATION_ID + ".fileprovider", file)
    }

    private fun loadFileInternal(ctx: Context, resId: Int, filename: String) {
        val file = File(getAttachmentsDir(ctx), filename)
        if (!file.exists()) {
            ctx.resources.openRawResource(resId).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }

    private fun getAttachmentsDir(ctx: Context): File {
        val directory = File(ctx.filesDir.path + File.separator + "attachments")
        directory.mkdir()

        return directory
    }
}
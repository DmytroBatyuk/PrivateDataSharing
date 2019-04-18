package ua.dmytrobatyuk.privatedatasharing

import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.core.content.FileProvider
import android.util.Log

//class PrivateDataShareProvider : FileProvider() {
//    private val TAG = javaClass.simpleName
//    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
//        Log.e(TAG, "openFile: open uri=$uri, mode=$mode")
//        return super.openFile(uri, mode)
//    }
//}
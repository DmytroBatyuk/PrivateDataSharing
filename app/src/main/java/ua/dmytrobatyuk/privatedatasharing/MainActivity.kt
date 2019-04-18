package ua.dmytrobatyuk.privatedatasharing

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FileUtils.loadResources(this)

        findViewById<Button>(R.id.share).setOnClickListener {
            share()
        }
        findViewById<View>(R.id.send).setOnClickListener {
            send()
        }
    }

    private fun share() {
        val uri = FileUtils.getShareUri(this)
        Log.e("DIMA", "share uri=$uri")

        val i = Intent(Intent.ACTION_VIEW)
        i.data = uri
        packageManager.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY).forEach {
//            if (it.activityInfo.packageName.contains("adobe", true)) {
            Log.e("DIMA", "grand permission for ${it.activityInfo.packageName}")
                grantUriPermission(it.activityInfo.packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
        }
        i.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION//.and(Intent.FLAG_ACTIVITY_NEW_TASK)

//        startActivityForResult(i, 1)
        startActivity(Intent.createChooser(i, "Choose preview app"))
    }

    private fun send() {
        val uri = FileUtils.getShareUri(this)
        Log.e("DIMA", "send uri=$uri")
        val i = Intent(Intent.ACTION_SEND)
        i.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf("support@imyourdoc.com"))

        i.putExtra(Intent.EXTRA_SUBJECT, "Android Diagnostic log from " + "DIMA")
        i.putExtra(Intent.EXTRA_TEXT, "sdfsdafdfasdfasd dsfad af ads as ")
        i.putExtra(Intent.EXTRA_STREAM, uri)
        i.setType("text/plain")
        packageManager.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY).forEach {
            //            if (it.activityInfo.packageName.contains("adobe", true)) {
            Log.e("DIMA", "grand permission for ${it.activityInfo.packageName}")
            grantUriPermission(it.activityInfo.packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            }
        }
        i.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION//.and(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(Intent.createChooser(i, "Send mail"))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            Log.e("DIMA", "activityResult=${resultCode== Activity.RESULT_OK}")
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

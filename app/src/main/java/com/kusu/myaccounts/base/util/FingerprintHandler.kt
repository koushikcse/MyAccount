package com.kusu.myaccounts.base.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.CancellationSignal
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.ImageView
import android.widget.TextView
import com.kusu.myaccounts.R
import com.kusu.myaccounts.fearture.search.presentation.SearchActivity

/**
 * Created by whit3hawks on 11/16/16.
 */
class FingerprintHandler// Constructor
(private val context: Context) : FingerprintManager.AuthenticationCallback() {

    fun startAuth(manager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject) {
        val cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        this.update("Fingerprint Authentication error\n" + errString)
    }

    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        this.update("Fingerprint Authentication help\n" + helpString)
    }

    override fun onAuthenticationFailed() {
        val imageview = (context as Activity).findViewById<ImageView>(R.id.imageView)
        imageview.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_error))
        this.update("Fingerprint Authentication failed.")
        val handler = Handler()
        handler.postDelayed({
            this.update(context.getString(R.string.touch_sensior_or_draw_pattern))
            val imageview = (context as Activity).findViewById<ImageView>(R.id.imageView)
            imageview.setImageDrawable(context.getDrawable(R.drawable.ic_finger_print))
        }, 2000)
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        val imageview = (context as Activity).findViewById<ImageView>(R.id.imageView)
        imageview.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_success))
        this.update("Fingerprint Authentication Success.")

        val handler = Handler()
        handler.postDelayed({
            (context as Activity).finish()
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }, 2000)


    }

    private fun update(e: String) {
        val textView = (context as Activity).findViewById<TextView>(R.id.message_txt)
        textView.text = e

    }

}

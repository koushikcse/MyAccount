package com.kusu.myaccounts.fearture.main.presentation

import android.content.Intent
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.kusu.myaccounts.R
import com.kusu.myaccounts.base.util.FingerprintUiHelper
import com.kusu.myaccounts.fearture.search.presentation.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.*

class MainActivity : AppCompatActivity(), FingerprintUiHelper.Callback {
    override fun onAuthenticated() {
        Log.e("SUCCESS","Fingerprint Success")

    }

    override fun onError() {
        Log.e("ERROR","Fingerprint Error")
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var keyStore: KeyStore
    private lateinit var keyGenerator: KeyGenerator
//    private lateinit var crypto: FingerprintManager.CryptoObject
    private lateinit var cipher: Cipher
    private lateinit var keyName: String
    private lateinit var cryptoObject: FingerprintManager.CryptoObject
    /*// Declare a string variable for the key we’re going to use in our fingerprint authentication
    private var KEY_NAME = "yourKey"
    private var cipher: Cipher? = null
    private var keyStore: KeyStore? = null
    private var keyGenerator: KeyGenerator? = null
    private var textView: TextView? = null
    private var cryptoObject: FingerprintManager.CryptoObject? = null
    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pattern_lock_view.addPatternLockListener(object : PatternLockViewListener {
            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("pattern", "" + PatternLockUtils.patternToString(pattern_lock_view, pattern))
                if ((PatternLockUtils.patternToString(pattern_lock_view, pattern)) == "03678") {
                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onCleared() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStarted() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        //Generate keystore
        setupKeyStoreAndKeyGenerator()
        setupCiphers()
        createKey("Koushik",true)

//        val (defaultCipher: Cipher, cipherNotInvalidated: Cipher) = setupCiphers()

//        initCipher(cipher, "koushik")

    }




    /**
     * Sets up KeyStore and KeyGenerator
     */
    private fun setupKeyStoreAndKeyGenerator() {
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to get an instance of KeyStore", e)
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchProviderException ->
                    throw RuntimeException("Failed to get an instance of KeyGenerator", e)
                else -> throw e
            }
        }

        //Create Key
//        createKey("koushik",false)
    }

    /**
     * Sets up default cipher and a non-invalidated cipher
     */
    private fun setupCiphers(): Pair<Cipher, Cipher> {
//        val defaultCipher: Cipher
        val cipherNotInvalidated: Cipher
        try {
            val cipherString = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
            cipher = Cipher.getInstance(cipherString)
            cipherNotInvalidated = Cipher.getInstance(cipherString)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException ->
                    throw RuntimeException("Failed to get an instance of Cipher", e)
                else -> throw e
            }
        }
        return Pair(cipher, cipherNotInvalidated)
    }

    /**
     * Initialize the [Cipher] instance with the created key in the [createKey] method.
     *
     * @param keyName the key name to init the cipher
     * @return `true` if initialization succeeded, `false` if the lock screen has been disabled or
     * reset after key generation, or if a fingerprint was enrolled after key generation.
     */
    private fun initCipher(keyName: String): Boolean {
        try {
            keyStore.load(null)
            val cipherString = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
            cipher = Cipher.getInstance(cipherString)
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey(keyName, null) as SecretKey)
            return true
        } catch (e: Exception) {
            when (e) {
                is KeyPermanentlyInvalidatedException -> return false
                is KeyStoreException,
                is CertificateException,
                is UnrecoverableKeyException,
                is IOException,
                is NoSuchAlgorithmException,
                is InvalidKeyException -> throw RuntimeException("Failed to init Cipher", e)
                else -> throw e
            }
        }
    }


    // Show confirmation message. Also show crypto information if fingerprint was used.
    private fun showConfirmation(encrypted: ByteArray? = null) {
//        findViewById<View>(R.id.confirmation_message).visibility = View.VISIBLE
//        if (encrypted != null) {
//            findViewById<TextView>(R.id.encrypted_message).run {
//                visibility = View.VISIBLE
//                text = Base64.encodeToString(encrypted, 0 /* flags */)
//            }
//        }
        Toast.makeText(this, Base64.encodeToString(encrypted, 0 /* flags */), Toast.LENGTH_LONG).show();
    }

    /**
     * Tries to encrypt some data with the generated key from [createKey]. This only works if the
     * user just authenticated via fingerprint.
     */
    private fun tryEncrypt(cipher: Cipher) {
        try {
            showConfirmation(cipher.doFinal(SECRET_MESSAGE.toByteArray()))
        } catch (e: Exception) {
            when (e) {
                is BadPaddingException,
                is IllegalBlockSizeException -> {
                    Toast.makeText(this, "Failed to encrypt the data with the generated key. "
                            + "Retry the purchase", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Failed to encrypt the data with the generated key. ${e.message}")
                }
                else -> throw e
            }
        }
    }


    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with a fingerprint.
     *
     * @param keyName the name of the key to be created
     * @param invalidatedByBiometricEnrollment if `false` is passed, the created key will not be
     * invalidated even if a new fingerprint is enrolled. The default value is `true` - the key will
     * be invalidated if a new fingerprint is enrolled.
     */
     fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean) {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of enrolled
        // fingerprints has changed.
        try {
            keyStore.load(null)

            val keyProperties = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            val builder = KeyGenParameterSpec.Builder(keyName, keyProperties)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment)

            keyGenerator.run {
                init(builder.build())
                generateKey()
            }
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is InvalidAlgorithmParameterException,
                is CertificateException,
                is IOException -> throw RuntimeException(e)
                else -> throw e
            }
        }

        if (initCipher(keyName)){
                cryptoObject=FingerprintManager.CryptoObject(cipher)
//                tryEncrypt(cryptoObject.cipher)
        }




    }

    companion object {
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
        private val DIALOG_FRAGMENT_TAG = "myFragment"
        private val KEY_NAME_NOT_INVALIDATED = "key_not_invalidated"
        private val SECRET_MESSAGE = "Very secret message"
        private val TAG = MainActivity::class.java.simpleName
    }
}

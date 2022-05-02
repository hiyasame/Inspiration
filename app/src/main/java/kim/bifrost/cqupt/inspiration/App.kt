package kim.bifrost.cqupt.inspiration

import android.annotation.SuppressLint
import kim.bifrost.rain.common.BaseApp
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * kim.bifrost.cqupt.inspiration.App
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 12:58
 **/
class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        handleSSLHandshake()
    }

    /**
     * 信任证书
     */
    private fun handleSSLHandshake() {
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(@SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?, authType: String?
                ) = Unit

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?, authType: String?
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })
            val sc: SSLContext = SSLContext.getInstance("TLS")
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
        } catch (ignored: Exception) {
        }
    }
}
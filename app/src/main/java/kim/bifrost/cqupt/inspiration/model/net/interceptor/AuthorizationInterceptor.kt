package kim.bifrost.cqupt.inspiration.model.net.interceptor

import kim.bifrost.cqupt.inspiration.model.UserManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * kim.bifrost.cqupt.inspiration.model.net.interceptor.AuthorizationInterceptor
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 10:26
 **/
class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + UserManager.currentToken)
            .build()
        return chain.proceed(request)
    }
}
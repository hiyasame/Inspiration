package kim.bifrost.cqupt.inspiration.model.net

import kim.bifrost.cqupt.inspiration.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * kim.bifrost.cqupt.inspiration.model.net.RetrofitHelper
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:27
 **/
object RetrofitHelper {
    private val retrofit by lazy { initRetrofit() }

    val colorService: ColorService by lazy { retrofit.create(ColorService::class.java) }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}
package kim.bifrost.cqupt.inspiration.model.net

import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.user.TokenData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * kim.bifrost.cqupt.inspiration.model.net.UserService
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 17:14
 **/
interface UserService {
    @POST("user/long_login")
    @FormUrlEncoded
    suspend fun getUnexpiredToken(@Field("phone_number") phoneNumber: String): BaseResponse<TokenData>

    companion object : UserService by RetrofitHelper.userService
}
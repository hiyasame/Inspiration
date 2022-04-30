package kim.bifrost.cqupt.inspiration.model.net

import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.color.ColorDetail
import kim.bifrost.cqupt.inspiration.model.data.color.ColorPageBean
import kim.bifrost.cqupt.inspiration.model.data.color.ColorType
import kim.bifrost.cqupt.inspiration.model.data.color.ColorTypes
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * kim.bifrost.cqupt.inspiration.model.net.ApiService
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:30
 **/
interface ColorService {
    @GET("color/page")
    suspend fun getPageId(): BaseResponse<ColorTypes>

    @GET("color/color_list")
    suspend fun getColorList(
        @Query("theme_id") themeId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): BaseResponse<ColorPageBean>

    @GET("color/color_detail")
    suspend fun getColorDetail(
        @Query("color_detail_id") id: Int
    ): BaseResponse<ColorDetail>

    companion object : ColorService by RetrofitHelper.colorService
}
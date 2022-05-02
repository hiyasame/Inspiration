package kim.bifrost.cqupt.inspiration.model.net

import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.inspiration.IdeaDetail
import kim.bifrost.cqupt.inspiration.model.data.inspiration.IdeaDetailId
import kim.bifrost.cqupt.inspiration.model.data.inspiration.InspirationType
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * kim.bifrost.cqupt.inspiration.model.net.InspirationService
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 12:08
 **/
interface InspirationService {
    @GET("idea/idea")
    suspend fun getMainPageInfo(): BaseResponse<List<InspirationType>>

    @GET("idea/idea_detail_list")
    suspend fun getIdeaDetailList(
        @Query("idea_id") id: Int
    ): BaseResponse<List<IdeaDetailId>>

    @GET("idea/idea_detail")
    suspend fun getIdeaDetail(@Query("idea_detail_id") id: Int): BaseResponse<IdeaDetail>

    companion object : InspirationService by RetrofitHelper.inspirationService
}
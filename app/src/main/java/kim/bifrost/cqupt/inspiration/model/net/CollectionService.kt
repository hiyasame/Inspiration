package kim.bifrost.cqupt.inspiration.model.net

import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.collection.UserCollectionData
import retrofit2.http.*

/**
 * kim.bifrost.cqupt.inspiration.model.net.CollectionService
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 19:42
 **/
interface CollectionService {
    @POST("star/star")
    @FormUrlEncoded
    suspend fun star(
        @Field("shape_id") shapeId: Int,
        @Field("name") name: String
    ): BaseResponse<Unit>

    @POST("star/delete_star")
    @FormUrlEncoded
    suspend fun delete(
        @Field("star_id") starId: Int
    ): BaseResponse<Unit>

    @GET("star/star_list")
    suspend fun getStarList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<UserCollectionData>

    companion object : CollectionService by RetrofitHelper.collectionService
}
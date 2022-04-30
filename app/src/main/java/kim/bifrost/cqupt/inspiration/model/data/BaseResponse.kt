package kim.bifrost.cqupt.inspiration.model.data

/**
 * kim.bifrost.cqupt.inspiration.model.data.BaseResponse
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:24
 **/
data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)

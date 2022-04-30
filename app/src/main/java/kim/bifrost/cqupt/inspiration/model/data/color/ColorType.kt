package kim.bifrost.cqupt.inspiration.model.data.color

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * kim.bifrost.cqupt.inspiration.model.data.color.ColorType
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:40
 **/
@Parcelize
data class ColorType(
    val id: Int,
    val theme: String
) : Parcelable
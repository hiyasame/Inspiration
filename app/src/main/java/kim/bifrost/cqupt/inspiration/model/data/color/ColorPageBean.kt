package kim.bifrost.cqupt.inspiration.model.data.color

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ColorPageBean(
    val color_list: List<Color>,
    val has_more: Boolean
) : Serializable, Parcelable {
    data class Color(
        val b: Int,
        val c: Int,
        val g: Int,
        val hex: String,
        val id: Int,
        val k: Int,
        val m: Int,
        val name: String,
        val r: Int,
        val y: Int
    ) : Serializable
}
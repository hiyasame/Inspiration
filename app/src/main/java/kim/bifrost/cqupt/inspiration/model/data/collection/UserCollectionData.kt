package kim.bifrost.cqupt.inspiration.model.data.collection

import java.io.Serializable

data class UserCollectionData(
    val has_more: Boolean,
    val star_list: List<Star>
) : Serializable {
    data class Star(
        val colorShade: List<ColorShade>,
        val id: Int,
        val name: String
    ) : Serializable {
        data class ColorShade(
            val color: Color
        ) : Serializable {
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
    }
}
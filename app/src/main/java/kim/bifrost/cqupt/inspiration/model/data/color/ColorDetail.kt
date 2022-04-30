package kim.bifrost.cqupt.inspiration.model.data.color

import java.io.Serializable

data class ColorDetail(
    val colors: Colors,
    val intro: String,
    val shades: Shades
) : Serializable {
    data class Colors(
        val color_1: Color,
        val color_2: Color,
        val color_3: Color,
        val color_4: Color,
        val color_5: Color,
        val color_6: Color,
        val color_7: Color
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

    data class Shades(
        val shade_list: List<Shade>
    ) : Serializable {
        data class Shade(
            val shade: List<Shade>
        ) : Serializable {
            data class Shade(
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
}
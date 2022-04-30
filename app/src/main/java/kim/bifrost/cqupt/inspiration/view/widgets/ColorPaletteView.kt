package kim.bifrost.cqupt.inspiration.view.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

/**
 * kim.bifrost.cqupt.inspiration.view.widgets.ColorPaletteView
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 17:04
 **/
class ColorPaletteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val mPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            strokeWidth = 3f
        }
    }

    private var colors: IntArray = intArrayOf(
        0xFFFFFFFF.toInt(),
        0xFF000000.toInt()
    )

    // 设置渐变器参数
    private var linearGradient =
        LinearGradient(
            width / 2F,
            0F,
            width / 2F,
            height.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )

    override fun onDraw(canvas: Canvas) {
        mPaint.shader = linearGradient
        canvas.drawCircle(width / 2F, height / 2F, width / 2F - 20, mPaint)
    }

    fun setColors(colors: IntArray) {
        this.colors = colors
        linearGradient = buildGradient()
        invalidate()
    }

    private fun buildGradient(): LinearGradient {
        return LinearGradient(
            width / 2F,
            0F,
            width / 2F,
            height.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
    }
}
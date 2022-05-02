package kim.bifrost.cqupt.inspiration.view.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import kotlin.properties.Delegates

/**
 * kim.bifrost.cqupt.inspiration.view.widgets.LinearGradientView
 * Inspiration
 * 简单的渐变色自定义view
 *
 * @author 寒雨
 * @since 2022/4/30 17:11
 **/
class LinearGradientView @JvmOverloads constructor(
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
    private var change = true
    private var colors: IntArray = intArrayOf(
        0xFFFFFFFF.toInt(),
        0xFF000000.toInt()
    )

    // 设置渐变器参数
    private var linearGradient: LinearGradient? = null

    override fun onDraw(canvas: Canvas) {
        if (linearGradient == null || change) {
            linearGradient = buildGradient()
            change = false
        }
        mPaint.shader = linearGradient
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint)
    }

    fun setColors(colors: IntArray) {
        this.colors = colors
        change = true
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
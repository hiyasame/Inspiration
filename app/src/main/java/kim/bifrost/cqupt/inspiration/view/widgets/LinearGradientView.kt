package kim.bifrost.cqupt.inspiration.view.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
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

    private var mScreenHeight by Delegates.notNull<Int>()
    private var mScreenWidth by Delegates.notNull<Int>()

    init {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        mScreenHeight = display.height
        mScreenWidth = display.width
    }

    private lateinit var colors: IntArray

    // 设置渐变器参数
    private val linearGradient by lazy { LinearGradient(x, y, x, y + height, colors, null, Shader.TileMode.CLAMP) }

    override fun onDraw(canvas: Canvas) {
        if (!::colors.isInitialized) return
        mPaint.shader = linearGradient
        canvas.drawRect(0f, 0f, mScreenWidth.toFloat(), mScreenHeight.toFloat(), mPaint)
    }

    fun setColors(colors: IntArray) {
        this.colors = colors
        invalidate()
    }
}
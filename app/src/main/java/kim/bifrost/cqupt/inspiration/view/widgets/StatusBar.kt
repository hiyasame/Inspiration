package kim.bifrost.cqupt.inspiration.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * kim.bifrost.cqupt.inspiration.view.widgets.StatusBar
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 11:08
 **/
class StatusBar(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var mStatusBarHeight = 0
    init {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != 0) {
            mStatusBarHeight = resources.getDimensionPixelSize(resourceId)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newHeightMS = MeasureSpec.makeMeasureSpec(mStatusBarHeight, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightMS)
    }
} 
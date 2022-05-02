package kim.bifrost.rain.common.utils.text

import android.graphics.BlurMaskFilter
import android.graphics.drawable.Drawable
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import kim.bifrost.rain.common.BaseApp

/**
 * kim.bifrost.rain.common.utils.text.SpanTextBuilder
 * Inspiration
 * SpanText 流式工具
 *
 * @author 寒雨
 * @since 2022/5/2 15:40
 **/
interface SpanTextBuilder {

    /**
     * 换行
     */
    fun newLine()

    /**
     * 写入一段文字
     *
     * @param content 内容
     * @param url url链接
     * @param textSize 文字大小
     * @param relativeSize 相对大小
     * @param firstLineMarginStart 段落开头留空距离
     * @param restLineMarginStart 第一行以外行数开头留空距离
     * @param scaleX
     * @param backgroundColor 背景颜色
     * @param foregroundColor 文字颜色
     * @param blurRadius 模糊半径
     * @param blurStyle 模糊风格
     * @param isBold 是否粗体
     * @param isItalic 是否斜体
     * @param isUnderLine 是否下划线
     * @param isStrikeThrough
     * @param isSuperscript 强调
     * @param isSubscript 减弱
     * @return
     */
    fun writeText(
        content: String,
        url: String? = null,
        @Px textSize: Int = -1,
        relativeSize: Float = -1F,
        @Px firstLineMarginStart: Int = 0,
        @Px restLineMarginStart: Int = 0,
        scaleX: Float = -1F,
        @ColorInt backgroundColor: Int? = null,
        @ColorInt foregroundColor: Int? = null,
        blurRadius: Float = -1F,
        blurStyle: BlurMaskFilter.Blur = BlurMaskFilter.Blur.NORMAL,
        isBold: Boolean = false,
        isItalic: Boolean = false,
        isUnderLine: Boolean = false,
        isStrikeThrough: Boolean = false,
        isSuperscript: Boolean = false,
        isSubscript: Boolean = false,
        onClick: View.OnClickListener? = null
    ) : SpanTextBuilder

    /**
     * 写入一张图片
     *
     * @param drawable 图片
     * @param verticalAlignment 竖直方向排列规则
     * @param width 宽
     * @param height 高
     * @param scaleX
     * @param scaleY
     * @return
     */
    fun writeImage(
        drawable: Drawable?,
        verticalAlignment: Int = ImageSpan.ALIGN_BOTTOM,
        @Px width: Int = -1,
        @Px height: Int = -1,
        scaleX: Float = 1F,
        scaleY: Float = 1F,
    ) : SpanTextBuilder

    /**
     * 写入一张图片
     *
     * @param resId 图片Id
     * @param verticalAlignment 竖直方向排列规则
     * @param width 宽
     * @param height 高
     * @param scaleX
     * @param scaleY
     * @return
     */
    fun writeImage(
        @DrawableRes resId: Int,
        verticalAlignment: Int = ImageSpan.ALIGN_BOTTOM,
        @Px width: Int = -1,
        @Px height: Int = -1,
        scaleX: Float = 1F,
        scaleY: Float = 1F,
    ) : SpanTextBuilder {
        return writeImage(
            ContextCompat.getDrawable(BaseApp.appContext, resId),
            verticalAlignment, width, height, scaleX, scaleY
        )
    }

    /**
     * 绑定到textView上
     *
     * @param view
     */
    fun bind(view: TextView)

    /**
     * 组装为Spanned
     *
     * @return
     */
    fun build(): Spanned

    companion object {
        fun newBuilder(span: Spanned? = null, flag: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) : SpanTextBuilder {
            return SpanTextBuilderImpl(flag, span)
        }
    }
}
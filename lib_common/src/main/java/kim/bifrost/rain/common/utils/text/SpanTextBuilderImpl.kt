package kim.bifrost.rain.common.utils.text

import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import kim.bifrost.rain.common.BaseApp

/**
 * kim.bifrost.rain.common.utils.text.SpanTextBuilderImpl
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 16:11
 **/
class SpanTextBuilderImpl(private val flag: Int, private val span: Spanned?) : SpanTextBuilder {

    private val lineSeparator = System.getProperty("line.separator")

    private val ssb = SpannableStringBuilder().apply {
        span?.let {
            append(it)
        }
    }

    override fun newLine() {
        ssb.append(lineSeparator)
    }

    override fun writeText(
        content: String,
        url: String?,
        @Px textSize: Int,
        relativeSize: Float,
        @Px firstLineMarginStart: Int,
        @Px restLineMarginStart: Int,
        scaleX: Float,
        @ColorInt backgroundColor: Int?,
        @ColorInt foregroundColor: Int?,
        blurRadius: Float,
        blurStyle: BlurMaskFilter.Blur,
        isBold: Boolean,
        isItalic: Boolean,
        isUnderLine: Boolean,
        isStrikeThrough: Boolean,
        isSuperscript: Boolean,
        isSubscript: Boolean,
        onClick: View.OnClickListener?
    ): SpanTextBuilder {
        val start = ssb.length
        ssb.append(content)
        val end = ssb.length

        if (textSize >= 0) {
            ssb.setSpan(AbsoluteSizeSpan(textSize), start, end, flag)
        }
        if (relativeSize >= 0F) {
            ssb.setSpan(RelativeSizeSpan(relativeSize), start, end, flag)
        }
        ssb.setSpan(
            LeadingMarginSpan.Standard(firstLineMarginStart, restLineMarginStart),
            start,
            end,
            flag
        )
        if (scaleX >= 0F) {
            ssb.setSpan(ScaleXSpan(scaleX), start, end, flag)
        }
        if (backgroundColor != null) {
            ssb.setSpan(BackgroundColorSpan(backgroundColor), start, end, flag)
        }
        if (foregroundColor != null) {
            ssb.setSpan(ForegroundColorSpan(foregroundColor), start, end, flag)
        }
        if (blurRadius >= 0F) {
            ssb.setSpan(MaskFilterSpan(BlurMaskFilter(blurRadius, blurStyle)), start, end, flag)
        }

        if (isBold) {
            ssb.setSpan(StyleSpan(Typeface.BOLD), start, end, flag)
        }
        if (isItalic) {
            ssb.setSpan(StyleSpan(Typeface.ITALIC), start, end, flag)
        }
        if (isUnderLine) {
            ssb.setSpan(UnderlineSpan(), start, end, flag)
        }
        if (isStrikeThrough) {
            ssb.setSpan(StrikethroughSpan(), start, end, flag)
        }
        if (isSuperscript) {
            ssb.setSpan(SuperscriptSpan(), start, end, flag)
        }
        if (isSubscript) {
            ssb.setSpan(SubscriptSpan(), start, end, flag)
        }
        url?.let {
            ssb.setSpan(URLSpan(it), start, end, flag)
        }
        onClick?.let {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    it.onClick(widget)
                }
            }
            ssb.setSpan(clickableSpan, start, end, flag)
        }
        return this
    }

    override fun writeImage(
        drawable: Drawable?,
        verticalAlignment: Int,
        width: Int,
        height: Int,
        scaleX: Float,
        scaleY: Float,
    ): SpanTextBuilder {
        val start = ssb.length
        ssb.append("[image]")
        val end = ssb.length

        drawable?.let {
            val boundWidth = if (width >= 0) width else it.intrinsicWidth
            val boundHeight = if (height >= 0) height else it.intrinsicHeight
            it.setBounds(
                0,
                0,
                (boundWidth * scaleX).toInt(),
                (boundHeight * scaleY).toInt()
            )
            ssb.setSpan(ImageSpan(it, verticalAlignment), start, end, flag)
        }
        return this
    }

    override fun bind(view: TextView) {
        view.text = ssb
    }

    override fun build(): Spanned {
        return ssb
    }

}
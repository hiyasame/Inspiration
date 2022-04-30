package kim.bifrost.cqupt.inspiration.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.view.LayoutInflater
import android.view.ViewGroup
import kim.bifrost.cqupt.inspiration.databinding.ItemSingleColorBinding
import kim.bifrost.cqupt.inspiration.model.data.color.ColorPageBean
import kim.bifrost.cqupt.inspiration.view.activity.ColorDetailsActivity
import kim.bifrost.rain.common.base.BaseItemCallBack
import kim.bifrost.rain.common.base.BasePagingAdapter

/**
 * kim.bifrost.cqupt.inspiration.view.adapter.ColorPageRvAdapter
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:10
 **/
class ColorPageRvAdapter(context: Context) : BasePagingAdapter<ItemSingleColorBinding, ColorPageBean.Color>(
    context = context,
    callback = BaseItemCallBack { a, b -> a.id == b.id }
) {
    override fun getDataBinding(parent: ViewGroup, viewType: Int): ItemSingleColorBinding {
        return ItemSingleColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override val holderInit: Holder<ItemSingleColorBinding>.() -> Unit
        get() = {
            binding.root.setOnClickListener {
                val data = getItem(bindingAdapterPosition)!!
                ColorDetailsActivity.start(context, data.id)
            }
        }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder<ItemSingleColorBinding>, position: Int) {
        val data = getItem(position)!!
        holder.binding.apply {
            tvName.text = data.name
            tvHex.text = "HEX #${data.hex}"
            tvRgb.text = "RGB ${data.r},${data.g},${data.b}"
            tvCmyk.text = "CMYK ${data.c},${data.m},${data.y},${data.k}"
            mcvItem.setBackgroundColor(Color.parseColor("#${data.hex}"))
        }
    }
}
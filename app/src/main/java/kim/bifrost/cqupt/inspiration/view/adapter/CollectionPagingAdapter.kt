package kim.bifrost.cqupt.inspiration.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kim.bifrost.cqupt.inspiration.databinding.ItemStarCardBinding
import kim.bifrost.cqupt.inspiration.model.data.collection.UserCollectionData
import kim.bifrost.cqupt.inspiration.view.activity.GradientActivity
import kim.bifrost.cqupt.inspiration.view.widgets.ColorPaletteView
import kim.bifrost.rain.common.base.BasePagingAdapter
import kim.bifrost.rain.common.utils.extensions.getProperty
import kim.bifrost.rain.common.utils.extensions.structuralCast

/**
 * kim.bifrost.cqupt.inspiration.view.adapter.CollectionPagingAdapter
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 9:53
 **/
class CollectionPagingAdapter(
    context: Context,
) : BasePagingAdapter<ItemStarCardBinding, UserCollectionData.Star>(
    context,
) {
    override val holderInit: Holder<ItemStarCardBinding>.() -> Unit
        get() = {
            binding.root.setOnClickListener {
                val data = getItem(bindingAdapterPosition)!!
                GradientActivity.start(context, data.colorShade.map { it.structuralCast() })
            }
        }

    override fun getDataBinding(parent: ViewGroup, viewType: Int): ItemStarCardBinding {
        return ItemStarCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: Holder<ItemStarCardBinding>, position: Int) {
        val data = getItem(position)!!
        holder.binding.apply {
            val colors = data.colorShade.map { c -> Color.parseColor("#${c.color.hex}") }.toIntArray()
            lgv.setColors(colors)
            repeat(3) {
                this.getProperty<View>("color${it + 1}")!!
                    .setBackgroundColor(colors.getOrElse(it) { c -> colors[c - 1] })
            }
            title.text = data.name
        }
    }
}
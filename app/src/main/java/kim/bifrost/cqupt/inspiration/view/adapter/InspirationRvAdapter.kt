package kim.bifrost.cqupt.inspiration.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import kim.bifrost.cqupt.inspiration.databinding.ItemInspirationBinding
import kim.bifrost.cqupt.inspiration.databinding.ItemInspirationReverseBinding
import kim.bifrost.cqupt.inspiration.model.data.inspiration.InspirationType

/**
 * kim.bifrost.cqupt.inspiration.view.adapter.InspirationRvAdapter
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 12:24
 **/
class InspirationRvAdapter(private val data: List<InspirationType>, private val onClick: InspirationType.() -> Unit) : RecyclerView.Adapter<InspirationRvAdapter.Holder>() {
    inner class Holder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val data = data[bindingAdapterPosition]
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (viewType == 0) {
            return Holder(ItemInspirationReverseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        return Holder(ItemInspirationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = data[position]
        if (holder.binding is ItemInspirationBinding) {
            holder.binding.apply {
                Glide.with(siv)
                    .load(data.image.replace("https://", "http://"))
                    .centerCrop()
                    .into(siv)
                content.text = data.name
            }
        } else if (holder.binding is ItemInspirationReverseBinding) {
            holder.binding.apply {
                Glide.with(siv)
                    .load(data.image.replace("https://", "http://"))
                    .centerCrop()
                    .into(siv)
                content.text = data.name
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }
}
package kim.bifrost.cqupt.inspiration.view.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kim.bifrost.cqupt.inspiration.databinding.FragmentInspirationDetailBinding
import kim.bifrost.cqupt.inspiration.model.data.color.ColorDetail
import kim.bifrost.cqupt.inspiration.model.data.inspiration.IdeaDetail
import kim.bifrost.cqupt.inspiration.view.activity.ColorDetailsActivity
import kim.bifrost.cqupt.inspiration.view.activity.GradientActivity
import kim.bifrost.cqupt.inspiration.view.widgets.ColorPaletteView
import kim.bifrost.rain.common.base.ui.BaseBindFragment
import kim.bifrost.rain.common.utils.extensions.getProperty
import kim.bifrost.rain.common.utils.extensions.structuralCast

/**
 * kim.bifrost.cqupt.inspiration.view.fragment.InspirationDetailFragment
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 15:34
 **/
class InspirationDetailFragment : BaseBindFragment<FragmentInspirationDetailBinding>() {

    private lateinit var data: IdeaDetail

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        data = requireArguments().getSerializable("data") as IdeaDetail
        binding.apply {
            Glide.with(binding.ivImage)
                .load(data.image.replace("https://", "http://"))
                .centerCrop()
                .into(binding.ivImage)
            repeat(3) {
                this.getProperty<ColorPaletteView>("cpv${it + 1}")!!
                    .apply {
                        setColors(data.shades.shade_list[it].shade.map { c -> Color.parseColor("#${c.color.hex}") }.toIntArray())
                        setOnClickListener { _ ->
                            GradientActivity.start(requireContext(), data.shades.shade_list[it].shade.map { s -> s.structuralCast() })
                        }
                    }
            }
            repeat(6) {
                val color = data.colors.getProperty<IdeaDetail.Colors.Color>("color_${it + 2}")!!
                this.getProperty<MaterialCardView>("mcvColor${it + 1}")?.apply {
                    setBackgroundColor(Color.parseColor("#" + color.hex))
                    // 点击进入其他颜色的界面
                    setOnClickListener {
                        ColorDetailsActivity.start(requireContext(), color.id)
                    }
                }
                this.getProperty<TextView>("tvColor${it + 1}")
                    ?.text = "#${color.hex}"
            }
        }
    }

    companion object {
        fun newInstance(data: IdeaDetail): InspirationDetailFragment {
            val args = Bundle()
            args.putSerializable("data", data)
            val fragment = InspirationDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
package kim.bifrost.cqupt.inspiration.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.card.MaterialCardView
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityColorDetailsBinding
import kim.bifrost.cqupt.inspiration.model.data.color.ColorDetail
import kim.bifrost.cqupt.inspiration.view.fragment.IntroduceDialogFragment
import kim.bifrost.cqupt.inspiration.view.viewmodel.ColorDetailsViewModel
import kim.bifrost.cqupt.inspiration.view.widgets.ColorPaletteView
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.getProperty
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.launch

class ColorDetailsActivity : BaseVmBindActivity<ColorDetailsViewModel, ActivityColorDetailsBinding>() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.id = intent.getIntExtra("id", 0)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
            menu.getItem(0).setOnMenuItemClickListener {
                if (viewModel.init) {
                    val data = viewModel.data.data ?: return@setOnMenuItemClickListener false.also {
                        "网络数据异常: ${viewModel.data.message}".toast()
                    }
                    IntroduceDialogFragment.newInstance(data.colors.color_1.name, data.intro)
                        .show(supportFragmentManager, "introduce")
                }
                true
            }
        }
        binding.tlDot.selectTab(binding.tlDot.getTabAt(2))
        lifecycleScope.launch {
            val res = viewModel.getDetails()
            val data = res.data ?: return@launch let {
                "网络数据异常: ${res.message}".toast()
            }
            binding.apply {
                toolbar.title = data.colors.color_1.name
                tvName.text = data.colors.color_1.name
                tvHex.text = "HEX #${data.colors.color_1.hex}"
                tvRgb.text = "RGB ${data.colors.color_1.r},${data.colors.color_1.g},${data.colors.color_1.b}"
                tvCmyk.text = "CMYK ${data.colors.color_1.c},${data.colors.color_1.m},${data.colors.color_1.y},${data.colors.color_1.k}"
                mcvItem.setBackgroundColor(Color.parseColor("#${data.colors.color_1.hex}"))
                repeat(6) {
                    this.getProperty<ColorPaletteView>("cpv${it + 1}")!!.apply {
                        // 必须按照argb的格式来
                        setColors(data.shades.shade_list[it].shade.map { c -> Color.parseColor("#${c.color.hex}") }.toIntArray())
                        setOnClickListener { _ ->
                            GradientActivity.start(this@ColorDetailsActivity, data.shades.shade_list[it].shade)
                        }
                    }
                }
                repeat(6) {
                    val color = data.colors.getProperty<ColorDetail.Colors.Color>("color_${it + 2}")!!
                    this.getProperty<MaterialCardView>("mcvColor${it + 1}")?.apply {
                        setBackgroundColor(Color.parseColor("#" + color.hex))
                        // 点击进入其他颜色的界面
                        setOnClickListener {
                            start(this@ColorDetailsActivity, color.id)
                        }
                    }
                    this.getProperty<TextView>("tvColor${it + 1}")
                        ?.text = "#${color.hex}"
                }
            }
        }
    }

    companion object {
        fun start(context: Context, id: Int) {
            val starter = Intent(context, ColorDetailsActivity::class.java)
                .putExtra("id", id)
            context.startActivity(starter)
        }
    }
}
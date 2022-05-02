package kim.bifrost.cqupt.inspiration.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityGradientBinding
import kim.bifrost.cqupt.inspiration.model.data.color.ColorDetail
import kim.bifrost.cqupt.inspiration.view.viewmodel.GradientViewModel
import kim.bifrost.rain.common.base.ui.BaseBindActivity
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kotlinx.coroutines.launch
import kotlin.random.Random

class GradientActivity : BaseVmBindActivity<GradientViewModel, ActivityGradientBinding>() {

    private lateinit var data: List<ColorDetail.Shades.Shade.Shade>
    private var liked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableArrayExtra("data")!!.map { it as ColorDetail.Shades.Shade.Shade }
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
        }
        binding.lgv.setColors(data.map { Color.parseColor("#${it.color.hex}") }.toIntArray())
        binding.ivLike.setOnClickListener {
            if (!liked) {
                lifecycleScope.launch {
                    viewModel.star(Random.nextInt(100), "好色儿")
                    Glide.with(binding.ivLike)
                        .load(R.drawable.ic_icon_heart_filled)
                        .into(binding.ivLike)
                    liked = true
                }
            } else {
                lifecycleScope.launch {
                    // 总不能随机删除一个吧，算了
                    Glide.with(binding.ivLike)
                        .load(R.drawable.ic_icon_heart_48)
                        .into(binding.ivLike)
                    liked = false
                }
            }
        }
    }

    companion object {
        fun start(context: Context, data: List<ColorDetail.Shades.Shade.Shade>) {
            val starter = Intent(context, GradientActivity::class.java)
                .putExtra("data", data.toTypedArray())
            context.startActivity(starter)
        }
    }
}
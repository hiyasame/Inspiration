package kim.bifrost.cqupt.inspiration.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityInspirationDetailBinding
import kim.bifrost.cqupt.inspiration.view.fragment.InspirationDetailFragment
import kim.bifrost.cqupt.inspiration.view.fragment.IntroduceDialogFragment
import kim.bifrost.cqupt.inspiration.view.viewmodel.InspirationDetailViewModel
import kim.bifrost.rain.common.base.BaseVPAdapter
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.launch

class InspirationDetailActivity : BaseVmBindActivity<InspirationDetailViewModel, ActivityInspirationDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.id = intent.getIntExtra("id", 0)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
        }
        lifecycleScope.launch {
            val res = viewModel.getIdeaList()
            val ids = res.data ?: return@launch let {
                "网络数据异常: ${res.message}".toast()
            }
            val data = ids.map { viewModel.getIdeaDetail(it.ideaDetail).data!! }
            binding.vp2.adapter = BaseVPAdapter(supportFragmentManager, lifecycle, data) { list, i ->
                InspirationDetailFragment.newInstance(list[i])
            }
            TabLayoutMediator(binding.tlDot, binding.vp2) { tab, _ ->
                tab.text = "·"
            }.attach()
            binding.vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.toolbar.title = data[position].title
                }
            })
            binding.toolbar.menu
                .getItem(0)
                .setOnMenuItemClickListener {
                    val current = data[binding.vp2.currentItem]
                    IntroduceDialogFragment.newInstance(current.title, current.intro)
                        .show(supportFragmentManager, "introduce")
                    true
                }
        }
    }

    companion object {
        fun start(context: Context, id: Int) {
            val starter = Intent(context, InspirationDetailActivity::class.java)
                .putExtra("id", id)
            context.startActivity(starter)
        }
    }
}
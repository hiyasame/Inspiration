package kim.bifrost.cqupt.inspiration.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityColorBinding
import kim.bifrost.cqupt.inspiration.view.fragment.ColorPageFragment
import kim.bifrost.cqupt.inspiration.view.viewmodel.ColorViewModel
import kim.bifrost.rain.common.base.BaseVPAdapter
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.launch

class ColorActivity : BaseVmBindActivity<ColorViewModel, ActivityColorBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
        }
        lifecycleScope.launch {
            val res = viewModel.getPageIds()
            val types = res.data ?: return@launch let {
                "网络数据异常: ${res.message}".toast()
            }
            binding.vp2Color.adapter = BaseVPAdapter(supportFragmentManager, lifecycle, types.list) { list, i ->
                ColorPageFragment.newInstance(list[i])
            }
            TabLayoutMediator(binding.tlDot, binding.vp2Color) { tab, _ ->
                tab.text = "·"
            }.attach()
            binding.vp2Color.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.toolbar.title = types.list[position].theme
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                // 没有搜索界面
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
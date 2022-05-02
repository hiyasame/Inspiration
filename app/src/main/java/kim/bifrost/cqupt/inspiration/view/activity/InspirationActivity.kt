package kim.bifrost.cqupt.inspiration.view.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityInspirationBinding
import kim.bifrost.cqupt.inspiration.view.adapter.InspirationRvAdapter
import kim.bifrost.cqupt.inspiration.view.viewmodel.InspirationViewModel
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.launch

class InspirationActivity : BaseVmBindActivity<InspirationViewModel, ActivityInspirationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
            title = "灵感"
        }
        lifecycleScope.launch {
            val res = viewModel.getTypes()
            val data = res.data ?: return@launch let {
                "网络请求错误: ${res.message}".toast()
            }
            binding.rvInspiration.apply {
                layoutManager = LinearLayoutManager(this@InspirationActivity)
                adapter = InspirationRvAdapter(data) {
                    InspirationDetailActivity.start(this@InspirationActivity, id)
                }
            }
        }
    }
}
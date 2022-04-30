package kim.bifrost.cqupt.inspiration.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kim.bifrost.cqupt.inspiration.databinding.FragmentColorPageBinding
import kim.bifrost.cqupt.inspiration.model.data.color.ColorPageBean
import kim.bifrost.cqupt.inspiration.model.data.color.ColorType
import kim.bifrost.cqupt.inspiration.view.adapter.ColorPageRvAdapter
import kim.bifrost.cqupt.inspiration.view.viewmodel.ColorPageViewModel
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindFragment
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * kim.bifrost.cqupt.inspiration.view.fragment.ColorFragment
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:02
 **/
class ColorPageFragment : BaseVmBindFragment<ColorPageViewModel, FragmentColorPageBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.colorType = requireArguments().getParcelable("data")!!
        val adapter = ColorPageRvAdapter(requireContext())
        binding.rvColorList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        lifecycleScope.launch {
            viewModel.pagingData.collectLatest {
                adapter.submitData(it)
            }
        }
        binding.srlColorList.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    binding.srlColorList.isRefreshing = true
                }
                is LoadState.NotLoading -> {
                    binding.srlColorList.isRefreshing = false
                }
                is LoadState.Error -> {
                    (it.refresh as LoadState.Error).error.printStackTrace()
                    "错误: ${(it.refresh as LoadState.Error).error.message}".toast()
                }
            }
        }
    }

    companion object {
        fun newInstance(data: ColorType): ColorPageFragment {
            val args = Bundle()
                .apply {
                    putParcelable("data", data)
                }
            val fragment = ColorPageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
package kim.bifrost.cqupt.inspiration.view.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityCollectionBinding
import kim.bifrost.cqupt.inspiration.view.adapter.CollectionPagingAdapter
import kim.bifrost.cqupt.inspiration.view.viewmodel.CollectionViewModel
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.setProperty
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CollectionActivity : BaseVmBindActivity<CollectionViewModel, ActivityCollectionBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
        }
        val adapter = CollectionPagingAdapter(this@CollectionActivity)
        binding.rvCollect.apply {
            layoutManager = LinearLayoutManager(this@CollectionActivity)
            this.adapter = adapter
        }
        lifecycleScope.launch {
            viewModel.pagingData.collectLatest {
                adapter.submitData(it)
            }
        }
        binding.srl.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    binding.srl.isRefreshing = true
                }
                is LoadState.NotLoading -> {
                    binding.srl.isRefreshing = false
                }
                is LoadState.Error -> {
                    "网络请求错误: ${(it.refresh as LoadState.Error).error.message}".toast()
                }
            }
        }
        binding.srl.setProperty("mTouchSlop", 220)
        val callback = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
            ): Int {
                // 控制快速滑动的方向（一般是左右）
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                // 不允许拖拽
                return makeMovementFlags(0, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //滑动处理
                val position = viewHolder.bindingAdapterPosition
                val data = adapter.getItemOut(position)!!
                viewModel.removeItem(data)
                lifecycleScope.launch {
                    viewModel.delete(data.id)
                    adapter.notifyItemRemoved(position)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvCollect)
    }
}
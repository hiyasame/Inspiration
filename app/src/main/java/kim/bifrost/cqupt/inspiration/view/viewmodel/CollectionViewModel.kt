package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import kim.bifrost.cqupt.inspiration.model.data.collection.UserCollectionData
import kim.bifrost.cqupt.inspiration.model.net.CollectionService
import kim.bifrost.rain.common.base.BasePagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.CollectionViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 19:15
 **/
class CollectionViewModel : ViewModel() {

    private val removedItemsFlow = MutableStateFlow(mutableListOf<Int>())

    val pagingData = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            BasePagingSource {
                CollectionService.getStarList(it, 10).data!!.star_list
            }
        }
    ).flow.cachedIn(viewModelScope)
        .combine(removedItemsFlow) { pagingData, removed ->
            pagingData.filter { it.id !in removed }
        }

    fun removeItem(item: UserCollectionData.Star) {
        val removes = removedItemsFlow.value
        removes.add(item.id)
        removedItemsFlow.value = removes
    }

    suspend fun delete(shadeId: Int) = CollectionService.delete(shadeId)
}
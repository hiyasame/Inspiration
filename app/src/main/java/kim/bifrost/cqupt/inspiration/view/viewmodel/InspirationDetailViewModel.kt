package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.inspiration.IdeaDetailId
import kim.bifrost.cqupt.inspiration.model.net.InspirationService
import kotlin.properties.Delegates

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.InspirationDetailViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 15:13
 **/
class InspirationDetailViewModel : ViewModel() {
    var id by Delegates.notNull<Int>()
    private lateinit var ideaIdList: BaseResponse<List<IdeaDetailId>>

    suspend fun getIdeaList() = if (!::ideaIdList.isInitialized)
        InspirationService.getIdeaDetailList(id).also { ideaIdList = it }
    else
        ideaIdList

    suspend fun getIdeaDetail(id: Int) = InspirationService.getIdeaDetail(id)
}
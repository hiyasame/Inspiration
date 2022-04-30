package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.color.ColorType
import kim.bifrost.cqupt.inspiration.model.data.color.ColorTypes
import kim.bifrost.cqupt.inspiration.model.net.ColorService

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.ColorViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 13:16
 **/
class ColorViewModel : ViewModel() {

    private lateinit var pageIds: BaseResponse<ColorTypes>

    suspend fun getPageIds() = if (::pageIds.isInitialized)
        pageIds
    else
        ColorService.getPageId().also { pageIds = it }
}
package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.data.BaseResponse
import kim.bifrost.cqupt.inspiration.model.data.color.ColorDetail
import kim.bifrost.cqupt.inspiration.model.net.ColorService
import kotlin.properties.Delegates

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.ColorDetailsViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 16:11
 **/
class ColorDetailsViewModel : ViewModel() {
    var id by Delegates.notNull<Int>()
    lateinit var data: BaseResponse<ColorDetail>

    val init: Boolean
        get() = ::data.isInitialized

    suspend fun getDetails() = if (::data.isInitialized) data else ColorService.getColorDetail(id)
        .also { data = it }

}
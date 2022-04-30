package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kim.bifrost.cqupt.inspiration.model.data.color.ColorType
import kim.bifrost.cqupt.inspiration.model.net.ColorService
import kim.bifrost.rain.common.base.BasePagingSource

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.ColorFragViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/4/30 14:02
 **/
class ColorPageViewModel : ViewModel() {

    lateinit var colorType: ColorType

    val pagingData = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            BasePagingSource {
                ColorService.getColorList(themeId = colorType.id, page = it).data!!.color_list
            }
        }
    ).flow
}
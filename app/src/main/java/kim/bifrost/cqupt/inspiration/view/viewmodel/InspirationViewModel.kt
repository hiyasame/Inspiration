package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.net.InspirationService

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.InspirationViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/1 11:37
 **/
class InspirationViewModel : ViewModel() {
    suspend fun getTypes() = InspirationService.getMainPageInfo()
}
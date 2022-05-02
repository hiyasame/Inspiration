package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.net.CollectionService

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.GradientViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 14:13
 **/
class GradientViewModel : ViewModel() {
    suspend fun star(shadeId: Int, name: String) = CollectionService.star(shadeId, name)

    suspend fun delete(shadeId: Int) = CollectionService.delete(shadeId)
}
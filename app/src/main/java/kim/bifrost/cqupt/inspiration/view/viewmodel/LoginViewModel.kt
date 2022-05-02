package kim.bifrost.cqupt.inspiration.view.viewmodel

import androidx.lifecycle.ViewModel
import kim.bifrost.cqupt.inspiration.model.UserManager
import kim.bifrost.cqupt.inspiration.model.net.UserService
import kotlinx.coroutines.coroutineScope

/**
 * kim.bifrost.cqupt.inspiration.view.viewmodel.LoginViewModel
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 16:40
 **/
class LoginViewModel : ViewModel() {
    suspend fun getToken(phoneNumber: String) = coroutineScope {
        val res = UserService.getUnexpiredToken(phoneNumber)
        if (res.data != null) {
            UserManager.currentToken = res.data.token
        }
        return@coroutineScope res
    }
}
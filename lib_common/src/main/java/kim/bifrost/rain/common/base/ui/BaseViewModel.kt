package kim.bifrost.rain.common.base.ui

import android.content.Context
import androidx.lifecycle.*
import kim.bifrost.rain.common.utils.extensions.toast
import kim.bifrost.rain.common.BaseApp

abstract class BaseViewModel : ViewModel() {

    val appContext: Context
        get() = BaseApp.appContext

    /**
     * 注意以下几点：
     * - 这个需要配合 observeNotNull() 一起使用，因为存在 null
     */
    protected fun <T> liveDataLaunch(block: suspend () -> T) =
        liveData(viewModelScope.coroutineContext) {
            val result = runCatching { block() }.onFailure { it.message?.toast() }
            emit(result.getOrNull())
        }
}
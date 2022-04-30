package kim.bifrost.rain.common.base.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kim.bifrost.rain.common.base.ui.BaseFragment

abstract class BaseVmFragment<VM: ViewModel>: BaseFragment() {

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = getViewModelFactory()
        if (factory == null) {
            ViewModelProvider(this)[getGenericClassFromSuperClass()] as VM
        } else {
            ViewModelProvider(this, factory)[getGenericClassFromSuperClass()] as VM
        }
    }

    protected open fun getViewModelFactory(): ViewModelProvider.Factory? = null
}
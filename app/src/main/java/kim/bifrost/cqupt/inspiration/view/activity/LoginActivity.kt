package kim.bifrost.cqupt.inspiration.view.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kim.bifrost.cqupt.inspiration.R
import kim.bifrost.cqupt.inspiration.databinding.ActivityLoginBinding
import kim.bifrost.cqupt.inspiration.view.viewmodel.LoginViewModel
import kim.bifrost.rain.common.base.ui.mvvm.BaseVmBindActivity
import kim.bifrost.rain.common.utils.extensions.toast
import kotlinx.coroutines.launch

class LoginActivity : BaseVmBindActivity<LoginViewModel, ActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_icon_arrowleft)
            setNavigationOnClickListener {
                finish()
            }
        }
        binding.btnLogin.setOnClickListener { 
            val phone = binding.etPhone.text?.toString() ?: return@setOnClickListener
            if (phone.isEmpty()) return@setOnClickListener
            lifecycleScope.launch {
                val data = viewModel.getToken(phone)
                data.message.toast()
                if (data.code == 114) {
                    finish()
                }
            }
        }
    }
}
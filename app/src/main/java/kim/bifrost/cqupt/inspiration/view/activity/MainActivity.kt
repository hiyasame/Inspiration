package kim.bifrost.cqupt.inspiration.view.activity

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import kim.bifrost.cqupt.inspiration.databinding.ActivityMainBinding
import kim.bifrost.rain.common.base.ui.BaseBindActivity

class MainActivity : BaseBindActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            ivColor.setOnClickListener {
                startActivity(ColorActivity::class.java)
            }
            ivInspiration.setOnClickListener {
                startActivity(InspirationActivity::class.java)
            }
            ivCollection.setOnClickListener {
                startActivity(CollectionActivity::class.java)
            }
            ivLogin.setOnClickListener {
                startActivity(LoginActivity::class.java)
            }
        }
    }
}
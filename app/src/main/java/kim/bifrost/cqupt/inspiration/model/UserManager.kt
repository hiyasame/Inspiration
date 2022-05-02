package kim.bifrost.cqupt.inspiration.model

import kim.bifrost.cqupt.inspiration.utils.DEFAULT_TOKEN
import kim.bifrost.rain.common.utils.extensions.def
import kim.bifrost.rain.common.utils.extensions.publicSharedPreferences

/**
 * kim.bifrost.cqupt.inspiration.model.UserManager
 * Inspiration
 *
 * @author 寒雨
 * @since 2022/5/2 10:29
 **/
object UserManager {
    var currentToken: String by publicSharedPreferences.def(DEFAULT_TOKEN)
}
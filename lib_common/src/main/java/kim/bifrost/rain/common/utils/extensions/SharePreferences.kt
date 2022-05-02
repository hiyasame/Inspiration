package kim.bifrost.rain.common.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.reflect.TypeToken
import kim.bifrost.rain.common.BaseApp
import kotlin.reflect.KProperty

/**
 * kim.bifrost.rain.common.utils.extensions.ShareP
 * Inspiration
 * 给sp的代理
 *
 * @author 寒雨
 * @since 2022/5/2 10:34
 **/
val publicSharedPreferences: SharedPreferences by lazy { BaseApp.appContext.getSharedPreferences("public", Context.MODE_PRIVATE) }

fun <T> SharedPreferences.def(def: T): SharePreferencesDelegate<T> {
    return SharePreferencesDelegate(this, def)
}

@Suppress("UNCHECKED_CAST")
class SharePreferencesDelegate<T>(
    sharedPreferences: SharedPreferences,
    private val def: T
) : SharedPreferences by sharedPreferences {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        // 基础类型直接返回 其他类型使用gson反序列化后返回
        return when (def) {
            is Boolean -> getBoolean(property.name, def) as T
            is String -> getString(property.name, def) as T
            is Set<*>-> getStringSet(property.name, def as Set<String>) as T
            is Int -> getInt(property.name, def) as T
            is Long -> getLong(property.name, def) as T
            is Float -> getFloat(property.name, def) as T
            else -> if (getString(property.name, null) == null) def else gson.fromJson(getString(property.name, null), object : TypeToken<T>() {}.type)
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        when (value) {
            is String -> edit {
                putString(property.name, value)
            }
            is Boolean -> edit {
                putBoolean(property.name, value)
            }
            is Set<*> -> edit {
                putStringSet(property.name, value as Set<String>)
            }
            is Int -> edit {
                putInt(property.name, value)
            }
            is Long -> edit {
                putLong(property.name, value)
            }
            is Float -> edit {
                putFloat(property.name, value)
            }
            else -> edit {
                putString(property.name, gson.toJson(value))
            }
        }
    }
}
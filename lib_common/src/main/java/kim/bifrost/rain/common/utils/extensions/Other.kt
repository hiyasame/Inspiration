package kim.bifrost.rain.common.utils.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.security.MessageDigest

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/3/7 17:51
 */

/**
 * 不带锁的懒加载，建议使用这个代替 lazy，因为 Android 一般情况下不会遇到多线程问题
 */
fun <T> lazyUnlock(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

fun String.md5(): String {
    val hash = MessageDigest.getInstance("MD5").digest(toByteArray())
    val hex = StringBuilder(hash.size * 2)
    for (b in hash) {
        var str = Integer.toHexString(b.toInt())
        if (b < 0x10) {
            str = "0$str"
        }
        hex.append(str.substring(str.length -2))
    }
    return hex.toString()
}

val Throwable.msg: String
    get() = "${javaClass.simpleName} : ${this.message}"

val gson: Gson = GsonBuilder().create()

/**
 * 强制转换结构类似的两种类型
 *
 * @param T 类型
 * @return
 */
inline fun <reified T> Any.structuralCast(): T = gson.fromJson(gson.toJson(this), T::class.java)
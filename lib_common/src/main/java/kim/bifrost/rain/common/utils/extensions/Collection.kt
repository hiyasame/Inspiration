package kim.bifrost.rain.common.utils.extensions

/**
 * cqupt.poststation.common.utils.extensions.Collection
 * PostStation
 *
 * @author 寒雨
 * @since 2022/4/12 9:28
 **/
fun <T, K> Iterable<T>.reduceCollection(init: K, onEach: (init: K, element: T) -> Unit): K {
    return init.apply {
        forEach { onEach(this, it) }
    }
}

fun <T, K> Iterable<T>.reduce(init: K, onEach: (init: K, element: T) -> K): K {
    var initial = init
    forEach { initial = onEach(initial, it) }
    return initial
}
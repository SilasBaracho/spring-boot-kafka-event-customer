package sb.ema.util

interface Validation<T> {

    fun validate(useCase: T)
}
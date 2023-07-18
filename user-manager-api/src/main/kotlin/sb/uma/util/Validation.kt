package sb.uma.util

interface Validation<T> {

    fun validate(input: T)
}
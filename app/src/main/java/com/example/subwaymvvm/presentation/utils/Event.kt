package com.example.subwaymvvm.presentation.utils

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set //외부에서 읽기는 허용하지만 쓰기는 허용하지 않습니다.

    /**
     * 콘텐츠를 반환하고 다시 사용하지 못하도록합니다.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * 이미 처리 된 경우에도 내용을 반환합니다.
     */
    fun peekContent(): T = content
}
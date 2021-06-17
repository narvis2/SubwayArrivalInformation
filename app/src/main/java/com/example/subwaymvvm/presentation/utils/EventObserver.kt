package com.example.subwaymvvm.presentation.utils

import androidx.lifecycle.Observer

/**
 * [Event]에 대한 [Observer]로, [Event]의 내용이 이미 처리되었는지 확인하는 패턴을 단순화합니다.
 *
 * [onEventUnhandledContent]는 [Event]의 내용이 처리되지 않은 경우에만 호출됩니다.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
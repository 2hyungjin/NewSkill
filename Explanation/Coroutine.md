# Coroutine

쓰레드에 비해 비용이 적어서 사용하기 좋다.

안드로이드의 비동기 실행을 쉽게 작성할 수 있다.

```kotlin
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
```

---

#### 코루틴 scope

코루틴을 실행하는 범위 Ex) GlobalScope, CoroutineScope..

---

#### 코루틴 Builder

launch{} -> 스코프를 실행시킨다. 반환 값이 없는 job을 반환시킨다. 

async{} -> 스코프를 실행시킨다.반환 값이 있는 deferred를 반환한다.

runBlocking{} -> 스코프 안의 모든 함수가 실행되기까지 대기한다.

---

#### 코루틴 Dispatcher

Dispatcher.Default -> 기본적인 백그라운드 동작

Dispatcher.IO -> I/O에 최적화된 동작, 네트워크 작업 시 유용

Dispatcher.MAIN -> 메인 쓰레드에서 동작, UI 작업

---

Job.join() -> Job이 끝날 때까지 기다린다

Deferred.await() -> Deferred가 끝날 때까지 기다리고 결과 값을 반환한다.


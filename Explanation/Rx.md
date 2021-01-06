# Rx Java

### Reactive-X Java

- Observable : 데이터 소스, 데이터를 발행(방출)함. (emit)
- 리액티브 연산자 (Operators) : Observable을 처리하는 함수
- Scheduler : 스레드를 관리
- Subscriber : Observable이 발행하는 데이터를 구독, 데이터를 소비함. (consume)

---

### Rx + Retrofit2

#### gradle 설정

```kotlin
//Rx
implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
//Retrofit
implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
implementation 'com.squareup.retrofit2:retrofit:2.6.0'
//Retrofit + Rx
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.6.2'
```

---

### Retrofit Interface

반환 값을 Observable<Data Typee>으로 변경

```kotlin
@GET("users")
fun getUsers():Observable<User>
```

빌드 시에 RxJavaCallAdapterFactory 추가

```kotlin
    val retrofit= Retrofit
        .Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
```

---

### Activity

결과 값을 Schedulers.io에 구독(데이터를 입(Input)출(Output)력할 때에 사용하는 스케쥴러)

```kotlin
retrofit.getUsers()
    .subscribeOn(Schedulers.io())
    .subscribe(
        {result->Log.d("TAG","result : $result.toString()")}, //onNext()
        {e->Log.d("TAG",e.toString())} //onError
    )
```


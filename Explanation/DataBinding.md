# Library
라이브러리 공부하기
## DataBinding

XML파일에 데이터를 연동(binding)시켜 사용할 수 있게 해주는 라이브러리(JetPack)

findViewById()와 kotlin extention을 대체하여 사용 가능

---

### gradle 설정

```kotlin
apply plugin: 'kotlin-kapt'
android {
    dataBinding{
        enabled=true
    }
```

---

### 사용

1. xml을 <layout/> 태그로 묶고 <data> 태그를 적는다. 

- name : 사용할 이름
- type : 타입(해당 activity class 혹은 data class 등 다양하게 사용 가능)

```xml
<?xml version="1.0" encoding="utf-8"?>

<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="main"
            type="com.example.databinding.MainActivity"/>
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView            
            android:id="@+id/tv_title"
            android:text="@{main.text,default=hello}" text를 data(main)의 text변수로 설정, default = "hello"
            ... />

        <Button
            android:onClick="@{()->main.clicked()}" onclick을 data(main)의 clicked 함수로 설정
            android:id="@+id/button"
            ... />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

2. onCreate의 setContentView를 binding=DataBindingutil.setContentView(this, layout)로 대체

```kotlin
class MainActivity : AppCompatActivity() {
    var text:String="hello world!"
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.main=this // binding된 xml의 main은 MainActivity로 선언

    }
    fun clicked(){
        Toast.makeText(this, "click!", Toast.LENGTH_SHORT).show()}
}
```

3. "binding.이름"으로 해당 xml을 가져올 수 있다. (언더 바를 기준으로 camel case 방식으로 표기 변환됨.)

- Ex) tv_title -> tvTitle

----

## ViewModel과 함께 쓰기

DataBinding과 ViewModel을 함께 사용하면 별도의 Observe 없이도 값이 변경되면 자동으로 UI를 업데이트할 수 있다.

1. ViewModel을 만든다.

```kotlin
class MainViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    fun setName(name: String) {
        this.name.value = name
    }

}
```

2. data에 ViewModel variable을 만든다

```xml
<data>
    <variable
        name="viewModel"
        type="com.example.databindingwithviewmodel.MainViewModel" />
</data>
```

3. text를 viewModel variable의 값으로 한다.

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{viewModel.name}"
    app:layout_constraintBottom_toTopOf="@+id/edt"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent" />
```

4. activity에서 viewModel과 LifeCycle을 정해준다.

   ```kotlin
   binding.viewModel = viewModel
   binding.lifecycleOwner = this
   ```

MainViewModel의 LiveData인 name이 변경되면 textView의 text가 자동으로 변경된다.
# Navigation 

Naviagate란 안드로이드에서 다른 화면으로 이동하는 것을 의미한다.

android jetpack이 나오기 전까지의 앱 내 화면 이동은 intent나 transaction으로 처리했으나

jetpack에 포함된 Navigation을 사용하면 단일 activity에서 여러 fragment로 많은 장점과 함께 쉽게 화면전환을 할 수 있다.

#### 구성요소

- **Navigation Graph** : 화면 이동 정보가 그려진 XML 리소스, 한 화면을 담당하는**Destination**과 화면 이동을 담당하는 **Action**에 관한 내용을 설정할 수 있다.
- **NavHost** : Fragment를 띄울 Activity를 의미한다.
- **NavController** : 화면 전환을 제어하는 용도, kotlin(java) 코드 내에서 쓰인다.

#### Navigation의 장점

- Fragment의 transaction 처리
- 애니매이션 등 전환에 관한 리소스
- 딥 링크 구현 및 처리
- **Safe Args**를 통한 데이터 전달
- ViewModel을 통한 데이터 공유

----

## 사용방법

#### build.gradle

```kotlin
 def nav_version = "2.3.3"

 implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"

 implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
```

#### Navigation Graph 만들기

res 디렉토리에서 Navagation을 생성한다.

Destinations에서 fragment 등을 추가하고 전환하고자 하는 Destination 끼리 연결한다.

#### NavHost 만들기

activity에서 NavHostFragment(FragmentContainerView)를 생성한다.

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragment_nav"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:defaultNavHost="true"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:navGraph="@navigation/nav_graph" />
```

app:navGraph를 통해 NavHostFragment와 navGraph를 연결한다.

#### NavController를 통한 화면 전환

fragment의 onViewCreated 시점에서 **Navigation.findNavController(view)**를 사용하여 NavController 객체를 얻을 수 있다.

**navController.naviagate(action_id)**의 형태로 화면 전환이 가능하다.

```kotlin
val navController = Navigation.findNavController(view)
btn_moveToFirst_second.setOnClickListener { navController.navigate(R.id.action_secondFragment2_to_firstFragment) }
```


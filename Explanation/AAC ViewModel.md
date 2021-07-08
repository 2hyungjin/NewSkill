# AAC ViewModel에 관한 간단한 정리

ViewModel은 lifecycle에 따라 수명주기가 바뀐다.

fragment의 경우 lifecycle은 activity일 수 있으며 fragment일 수도 있다.

이 중 activity일 경우 같은 activity에서 생성된 fragment에서 viewmodel을 공유할 수 있다.

kotlin ktx를 통해

val viewmodel:뷰모델 이름 by viewModels()를 통해 쉽게 인스턴스를 생성할 수 있다.(lifecycle -> this)

val viewmodel:뷰모델 이름 by activityViewModels()를 통해 쉽게 인스턴스를 생성할 수 있다.(lifecycle -> activity)


# Room

Android Jetpack 라이브러리로 기존의 내부 데이터베이스였던 SQLite에 어노테이션(@)함수를 비롯한 추상화 계층을 제공하여 SQLite의 활용성을 높임

---

### gradle

```kotlin
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version" //apply plugin: 'kotlin-kapt'

    // Room + Coroutine
    implementation "androidx.room:room-ktx:$room_version"
```

---

### Entity

데이터 베이스의 테이블을 정의

- @PrimaryKey : 기본 키를 지정할 수 있다. (autoGenerate=true)를 통해 자동 생성 가능

- @ColumnInfo : 테이블의 컬럼 명을 지정할 수 있다. (기본 값은 변수명과 동일)

```kotlin
@Entity(tableName = "User")
data class Entity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val n: String
)
```

---

### Dao

데이터 베이스에 접근하는 메서드를 선언

1. @Insert : 파라미터로 받은 데이터를 저장한다. 

   ㄴ(onConflict = OnConflictStrategy.)을 사용하여 중복처리

2. @Delete : 파라미터로 받은 데이터를 삭제

3. @Query 데이터베이스에서 읽고 쓰는 작업

```kotlin
@Dao
interface Dao {
    @Insert
    fun insert(user:Entity)
    @Delete
    fun delete(user:Entity)
    @Query("SELECT * FROM User")
    fun select():List<Entity>
}
```

---

### Database

데이터 베이스를 관리

1. entities={클래스 명}을 통해서 Entity를 지정
2. Dao를 반환하는 추상 메서드 선언

**RoomDatabase 인스턴스는 많은 리소스를 소비하기에 싱글톤 패턴으로 구현해야함**

```kotlin
@Database(entities = [Entity::class],version = 1)
abstract class DB : RoomDatabase(){
    abstract fun getDao():Dao

    companion object{
        private var INSTANCE:DB?=null
        fun getInstance(context: Context): DB? {
            if (INSTANCE==null){
                INSTANCE=Room.databaseBuilder(
                    context,
                    DB::class.java,
                    "db"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}
```

build 시에 allowMainThreadQueries()를 사용하면 메인 쓰레드에서 Dao 메서드를 사용할 수 있으나 데이터베이스에 접근하는 동안 UI 접근이 막힐 수 있기에 Rx, Coroutine 등 비동기 처리를 추천
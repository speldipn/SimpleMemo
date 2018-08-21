# SimpleMemo
first commit

#### SharedPreferences와 RecyclerView를 사용하여 간단한 메모앱 만들기 

### 동작시현
![screenshot01](/screenshot/screenshot01.png)

### SharedPreferences 사용

* create
````java
SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
````
* write
````java
SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
sharedPref.edit().putString(key, memo).commit();
````

* read
````java
SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
return sharedPref.getString(key, "Empty Memo"/*default*/);
````

* remove
```java
SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
sharedPref.edit().remove(key).commit();
````

### RecyclerView 사용
1. XML에 RecyclerView 추가
2. Holder와 CustomAdapter를 정의
3. RecylcerView와 Adapter를 연결
4. RecylcerView 레이아웃 매니저 지정
5. adapter에 메모 리스트를 추가 또는 갱신

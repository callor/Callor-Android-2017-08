
## 외부의 layout xml 파일을 import
<code>

	<include layout="@layout/linear_01" />
	<include layout="@layout/linear_02" />
	<include layout="@layout/linear_03" />

</code>

## Linear Layout 속성
<code>

	<LinearLayout 
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content"
	    android:orientation="horizontal"
	    android:weightSum="10"
	    tools:context="com.bit.linearlayoutexec.MainActivity">
	
	* orientation
	1. horizontal : 기본값 : 가로뱡향
	2. vertical : 설정 : 세로방향

</code>

## layout_* , 속성
* 부모, 형제들 간의 관계로서 설정

* height : 
1. wrap_content : 표시할 내용만큼만 크기를 설정
2. match_parent : 부모 view에 가득 차도록 크기 설정

* gravity : 정렬

## TextView 속성
* android:maxLines = "1" : 줄바꿈 하지 말라
* Text 생략 문자 설정

<code>

	android:ellipsize="marquee"
	android:focusable="true"

	android:marqueeRepeatLimit="marquee_forever"
	android:maxLines="1"

</code>


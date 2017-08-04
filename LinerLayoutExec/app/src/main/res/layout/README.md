
## 외부의 layout xml 파일을 import
<code>

	<include layout="@layout/linear_01" />
	<include layout="@layout/linear_02" />
	<include layout="@layout/linear_03" />

## Linear Layout 속성

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

# TitleBarLibrary
标题栏封装

由于安卓开发中经常使用标题栏，故而自定义了一个简单易用的标题栏。使用方法如下

##简单使用：

1.在 build.gradle 中添加对该库的依赖。

	implementation 'com.github.Satsna:TitleBarLibrary:1.0.1'
    
2.布局文件中使用
	
    <com.satsna.titlebar.view.view.TitleBarView
        android:id="@+id/tbv"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="#fff"
        app:centerText="首页"
        app:centerTextColor="#000"
        app:centerTextSize="18sp"
        app:centerVisibility="visibleTextView"
        app:leftSrc="@mipmap/iv_back_black"
        app:leftVisibility="visibleImage"
        app:rightText="更多"
        app:rightTextColor="#000"
        app:rightTextSize="18sp"
        app:rightVisibility="visibleTextView">
        
 ##自定义属性解释
 
 
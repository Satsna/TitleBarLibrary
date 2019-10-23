# TitleBarLibrary
标题栏封装

由于安卓开发中经常使用标题栏，故而自定义了一个简单易用的标题栏。

##效果预览
![enter description here][1]

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
 
 	###左边控件属性
    
 	  app:leftSrc="@mipmap/iv_back_black" 指定左边图片
      app:leftVisibility="visibleImage" 左边图片是否显示 invisible：不显示 visibleImage显示图片
      
	###中间控件属性
    
      app:centerText="首页" 指定中间标题文字
      app:centerTextColor="#000" 指定中间标题文字颜色
      app:centerTextSize="18sp" 指定中间标题文字大小
      app:centerVisibility="visibleTextView" 中间标题是否可见 invisible：不显示，visibleTextView：显示中间标题
      
	###右边控件属性
    
      app:rightVisibility="visibleTextView" 指定右边显示选项：invisible：右边不显示，visibleTextView：右边显示文字，visibleImage：右边显示图片
      app:rightText="更多" 指定右边文字
      app:rightTextColor="#000" 指定右边文字颜色
      app:rightTextSize="18sp" 指定右边文字大小
	app:rightSrc="@mipmap/iv_back" 指定右边显示的图片

 
 


  [1]: ./images/preview_1.png "preview_1.png"
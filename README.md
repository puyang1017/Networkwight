﻿# Networkwight


![](https://github.com/puyang1017/Networkwight/blob/master/image/Screenshot_20181223_132135_com.puy.networkwight.jpg)


[![](https://jitpack.io/v/puyang1017/Networkwight.svg)](https://jitpack.io/#puyang1017/Networkwight)

To get a Git project into your build:

##Step 1.

###Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

##Step 2.
###Add the dependency
```
dependencies {
	implementation 'com.github.puyang1017:Networkwight:v1.0.1'
}
```
##Step 3.
###Usage
```
<com.puy.networklibrary.NetworkDelayDisplay
        android:id="@+id/network_delay_display"
        android:layout_centerHorizontal="true"
        android:layout_width="300dp"
        android:layout_height="match_parent"
        android:background="#F9F9F9"
        />


NetworkDelayDisplay.setPs_to_ljb_delay(190, false)    //ps到联机宝延迟
        .setXbox_to_ljb_delay(40, true)               //xbox到联机宝延迟
        .setLjb_to_router_delay(170, false)           //联机宝到路由器延迟
        .setRouter_to_node_delay(45,false)            //路由器到节点延迟
        .add_node_to_country(67,true,"韩")            //添加节点到游戏延迟
        .add_node_to_country(100,true,"美")
        .add_node_to_country(500,true,"美")
        .add_node_to_country(50,false,"欧")
        .add_node_to_country(190,true,"美")
        .add_node_to_country(190,false,"亚")
        .add_node_to_country(90,true,"美")
        .add_node_to_country(210,false,"亚")
        .add_node_to_country(90,false,"美")
        .add_node_to_country(110,false,"亚")
        .setPsOnClickListener(new View.OnClickListener() { //图标点击监听事件
            @Override
            public void onClick(View v) {

            }
        })
        .setXboxOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })
        .setLjbOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })
        .setRouterOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })
        .setNodeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        })
        .start();
```
method
```
networkDelayDisplay.resetData() //重置数据   需要刷新全部数据时需要先调用此方法

networkDelayDisplay.start(); //启动    设置完数据或重置数据后需调用此方法才生效

networkDelayDisplay.setPs_to_ljb_delay(190, false)               //ps到联机宝延迟

networkDelayDisplay.setXbox_to_ljb_delay(40, true)               //xbox到联机宝延迟

networkDelayDisplay.setLjb_to_router_delay(170, false)           //联机宝到路由器延迟

networkDelayDisplay.setRouter_to_node_delay(45,false)            //路由器到节点延迟

networkDelayDisplay.add_node_to_country(67,true,"韩")            //添加节点到游戏延迟  最多添加10个数据

networkDelayDisplay.set_node_to_country_1(34,false,"亚")         //可以单独针节点到游戏的延迟单独设置

```



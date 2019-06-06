# Networkwight


![](https://github.com/puyang1017/Networkwight/blob/master/image/Screenshot_20181223_132135_com.puy.networkwight.jpg)


[![](https://jitpack.io/v/puyang1017/Networkwight.svg)](https://jitpack.io/#puyang1017/Networkwight)

To get a Git project into your build:

## Step 1.

### Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

## Step 2.
### Add the dependency
```
dependencies {
	implementation 'com.github.puyang1017:Networkwight:v1.1.2'
}
```
## Step 3.

### Usage
```
<com.puy.networklibrary.NetworkDelayMonitor
        android:id="@+id/network_delay_monitor"
        android:layout_centerHorizontal="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#F9F9F9"
        />


        network_delay_monitor.addDevice(R.drawable.icon_ps4_press, "ps1", "192.168.1.1",2);
        network_delay_monitor.addNode("中",2);
        network_delay_monitor.addNode("美",2);
        network_delay_monitor.addNode("日",2);
        network_delay_monitor.addNode("韩",2);
        network_delay_monitor.refreshLjbDelay(3);
        network_delay_monitor.refreshRouterDelay(4);
```
## method
```
network_delay_monitor.addDevice(R.drawable.icon_ps4_press, "ps1", "192.168.1.1",2); //添加设备 参数1->图片资源 参数2->名称 参数3->地址 参数4->延迟

network_delay_monitor.addNode("中",2); //添加节点 参数1->名称 参数2->延迟

network_delay_monitor.refreshLjbDelay(3);    //联机宝延迟

network_delay_monitor.refreshRouterDelay(4);   //路由器延迟

network_delay_monitor.refreshDeviceAndNodeDelay("中",200);       //刷新指定设备和节点的延迟

network_delay_monitor.removeDevice("ps3");          //删除指定设备

network_delay_monitor.removeNode("中");            //删除指定节点

```



package com.puy.networkwight;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.puy.networklibrary.NetworkDelayMonitor;


public class MainActivity extends AppCompatActivity {
    private Button add_button;
    private Button delete_button;
    private NetworkDelayMonitor network_delay_monitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        network_delay_monitor = findViewById(R.id.network_delay_monitor);
        network_delay_monitor.addDevice(R.drawable.icon_ps4_press, "ps1", "192.168.1.1",2d);
        network_delay_monitor.addNode("中",2000d);
        network_delay_monitor.addNode("中",2200d);
        network_delay_monitor.addNode("中",2200d);
        network_delay_monitor.addNode("中",2200d);
        network_delay_monitor.addNode("中",2100d);
        network_delay_monitor.addNode("中",112d);
        network_delay_monitor.addNode("中",222d);
        network_delay_monitor.addNode("中",222d);
        network_delay_monitor.refreshLjbDelay(3d);
        network_delay_monitor.refreshRouterDelay(4d);
        add_button = findViewById(R.id.add_button);
        delete_button = findViewById(R.id.delete_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network_delay_monitor.addDevice(R.drawable.icon_ps4_press, "ps3", "192.168.1.3",0d);
                network_delay_monitor.addNode("日",2d);
                network_delay_monitor.refreshDeviceAndNodeDelay("中",200d);
                network_delay_monitor.refreshDeviceAndNodeDelay("ps1",200d);
                network_delay_monitor.refreshLjbDelay(1000d);
                network_delay_monitor.refreshRouterDelay(200d);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network_delay_monitor.removeDevice("ps3");
                network_delay_monitor.removeNode("中");
            }
        });
    }

    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}

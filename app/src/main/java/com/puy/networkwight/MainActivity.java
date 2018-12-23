package com.puy.networkwight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import widget.NetworkDelayDisplay;

public class MainActivity extends AppCompatActivity {
    private NetworkDelayDisplay networkDelayDisplay;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkDelayDisplay = findViewById(R.id.network_delay_display);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkDelayDisplay.resetData()//重置数据
                        .setPs_to_ljb_delay(0, false)
                        .setXbox_to_ljb_delay(  2, true)
                        .setLjb_to_router_delay(170, false)
                        .setRouter_to_node_delay(200,false)
                        .add_node_to_country(67,true,"韩")
                        .add_node_to_country(99,true,"亚")
                        .setPsOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show("ps");
                            }
                        })
                        .setXboxOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show("xbox");
                            }
                        })
                        .setLjbOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show("ljb");
                            }
                        })
                        .setRouterOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show("router");
                            }
                        })
                        .setNodeOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show("node");
                            }
                        })
                        .start();
            }
        });
        networkDelayDisplay.setPs_to_ljb_delay(190, false)
                .setXbox_to_ljb_delay(40, true)
                .setLjb_to_router_delay(170, false)
                .setRouter_to_node_delay(45,false)
                .add_node_to_country(67,true,"韩")
                .add_node_to_country(100,true,"美")
                .add_node_to_country(500,true,"美")
                .add_node_to_country(50,false,"欧")
                .add_node_to_country(190,true,"美")
                .add_node_to_country(190,false,"亚")
                .add_node_to_country(90,true,"美")
                .add_node_to_country(210,false,"亚")
                .add_node_to_country(90,false,"美")
                .add_node_to_country(110,false,"亚")
                .setPsOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("ps");
                    }
                })
                .setXboxOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("xbox");
                    }
                })
                .setLjbOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("ljb");
                    }
                })
                .setRouterOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("router");
                    }
                })
                .setNodeOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show("node");
                    }
                })
                .start();
    }
    
    public void show(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}

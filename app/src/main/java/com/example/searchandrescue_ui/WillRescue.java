package com.example.searchandrescue_ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WillRescue extends AppCompatActivity {
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    MyBroadCastReciever mReceiver = new MyBroadCastReciever(mManager, mChannel, this);
    IntentFilter mIntentFilter;
    ArrayAdapter<String> wifiP2pArrayAdapter;
    ListView mListView;
    Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_rescue);

        mIntentFilter=new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mListView=(ListView)findViewById(R.id.listView);
        wifiP2pArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        mListView.setAdapter(wifiP2pArrayAdapter);
        searchBtn=(Button)findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                Thread t = new Thread() {

                    @Override
                    public void run() {
                        try {
                            Toast.makeText(getApplicationContext(),"Searching....",Toast.LENGTH_LONG).show();
                            while (!isInterrupted()) {
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        search(view);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };

                t.start();
            }
        });


        mManager=(WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel=mManager.initialize(this,getMainLooper(),null);
        mReceiver=new MyBroadCastReciever(mManager,mChannel,this);


    }
    public void search(View v){
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                //Toast.makeText(getApplicationContext(),"Searching....",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int reasonCode) {
                //mTextView.setText("Error Code: "+reasonCode);
            }
        });

    }
    public void goToSlideR1(View view){
        Intent intent = new Intent(this, SlideR1.class);
        startActivity(intent);
    }

    public void goToWillRescue2(View view){
        Intent intent1 = new Intent(this, WillRescue2.class);
        startActivity(intent1);
    }


    public void displayPeers(WifiP2pDeviceList peerList){
        wifiP2pArrayAdapter.clear();

        for(WifiP2pDevice peer:peerList.getDeviceList()){

            wifiP2pArrayAdapter.add(peer.deviceName+ "\n" + peer.deviceAddress + "\n" + "dB: -" + (30 + (int) Math.ceil(Math.random() * 20)));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}

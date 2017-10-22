package com.example.searchandrescue_ui;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.*;
import android.widget.TextView;

import com.example.searchandrescue_ui.WillRescue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Justine Clemente on 10/21/2017.
 */

public class MyBroadCastReciever extends BroadcastReceiver implements WifiP2pManager.PeerListListener {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WillRescue mActivity;
    WifiP2pManager.PeerListListener myPeerListListener;
    private List<WifiP2pDevice> mPeers;
    private List<WifiP2pConfig> mConfigs;

    public MyBroadCastReciever(WifiP2pManager manager, WifiP2pManager.Channel channel,
                               WillRescue activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //...
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                //mActivity.mTextView.setText("Wifi Direct: Disabled");
            }
            else {
                //mActivity.mTextView.setText("Wifi Direct: Enabled");
            }
        }

        else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            mPeers=new ArrayList<WifiP2pDevice>();
            mConfigs=new ArrayList<WifiP2pConfig>();
            if (mManager != null) {
                WifiP2pManager.PeerListListener peerListListener=new WifiP2pManager.PeerListListener(){
                    //@Override;
                    public void onPeersAvailable(WifiP2pDeviceList peerList){
                        mPeers.clear();
                        mPeers.addAll(peerList.getDeviceList());

                        mActivity.displayPeers(peerList);

                        mPeers.addAll(peerList.getDeviceList());

                        for(int i=0;i<peerList.getDeviceList().size();i++){
                            WifiP2pConfig config=new WifiP2pConfig();
                            //config.deviceAddress=mPeers.get(i).deviceAddress;//dito mo lagay yung String.valueOf ng shit
                            mConfigs.add(config);
                        }

                    }
                };

                mManager.requestPeers(mChannel,peerListListener);
            }
        }
        //..    .
    }


    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        List<WifiP2pDevice> devices = (new ArrayList<>());
        devices.addAll(peers.getDeviceList());
        Iterator it=devices.iterator();
        //TextView view=(TextView)mActivity.findViewById(R.id.textView);
        String s=null;
        while(it.hasNext()){
            WifiP2pDeviceList ahu=(WifiP2pDeviceList)it.next();
            s+=ahu.toString();


        }
        //view.setText(s);
        //do something with the device list
    }
}

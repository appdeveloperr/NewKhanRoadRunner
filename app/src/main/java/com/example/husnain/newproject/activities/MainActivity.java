package com.example.husnain.newproject.activities;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.husnain.newproject.PrintPakage.P25ConnectionException;
import com.example.husnain.newproject.PrintPakage.P25Connector;
import com.example.husnain.newproject.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

/*
import com.example.kamalsubhani.eparking.PrintPakage.P25ConnectionException;
import com.example.kamalsubhani.eparking.PrintPakage.P25Connector;
import com.example.kamalsubhani.eparking.R;
*/

/*import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;*/


public class MainActivity extends AppCompatActivity {


    private Button mConnectBtn;
    private Button mEnableBtn;


    private Spinner mDeviceSp;

    private ProgressDialog mProgressDlg;
    private ProgressDialog mConnectingDlg;

    private BluetoothAdapter mBluetoothAdapter;
    public static P25Connector mConnector;

    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnectBtn = (Button) findViewById(R.id.btn_connect);
        mEnableBtn = (Button) findViewById(R.id.btn_enable);
        mDeviceSp = (Spinner) findViewById(R.id.sp_device);
        initBluetoothSetup();
    }

    public void initBluetoothSetup(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            showUnsupported();
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                showDisabled();
            } else {
                showEnabled();
                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                if (pairedDevices != null) {
                    mDeviceList.addAll(pairedDevices);
                    updateDeviceList();
                }
            }

            mProgressDlg = new ProgressDialog(this);

            mProgressDlg.setMessage("Scanning...");
            mProgressDlg.setCancelable(false);
            mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    mBluetoothAdapter.cancelDiscovery();
                }
            });

            mConnectingDlg = new ProgressDialog(this);

            mConnectingDlg.setMessage("Connecting...");
            mConnectingDlg.setCancelable(false);

            if(mConnector!=null && mConnector.isConnected()){
                showConnected();
            } else {

            }


            //enable bluetooth
            mEnableBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                    startActivityForResult(intent, 1000);
                }
            });

            //connect/disconnect
            mConnectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if(mConnectBtn.getText().toString().trim().equals("رابطہ کریں")){
                        mConnector = new P25Connector(new P25Connector.P25ConnectionListener() {

                            @Override
                            public void onStartConnecting() {
                                mConnectingDlg.show();
                            }

                            @Override
                            public void onConnectionSuccess() {
                                mConnectingDlg.dismiss();

                                showConnected();
                            }

                            @Override
                            public void onConnectionFailed(String error) {
                                mConnectingDlg.dismiss();
                            }

                            @Override
                            public void onConnectionCancelled() {
                                mConnectingDlg.dismiss();
                            }

                            @Override
                            public void onDisconnected() {
                                showDisonnected();
                            }
                        });
                    }
                    connect();
                }
            });

            //print demo text
        }

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        registerReceiver(mReceiver, filter);

        /*if(!gpsturnon())
        {
            //buildAlertMessageNoGps();
            displayLocationSettingsRequest(this);
        }*/
    }

    public boolean gpsturnon(){

        try{
            final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

            if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                return false;
            }
            else{
                return true;
            }
        }catch (Exception ee){
            return  false;
        }
    }



    public void closeActivity(View view){
        this.finish();
    }

    public void scanDevices(View view){
        if(MainActivity.mConnector!=null){
            Toast.makeText(this,"Disconnect Before Scanning", Toast.LENGTH_SHORT).show();
        }else{
            mBluetoothAdapter.startDiscovery();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_scan) {
            mBluetoothAdapter.startDiscovery();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
        }
        /*
        if (mConnector != null) {
            try {
                mConnector.disconnect();
            } catch (P25ConnectionException e) {
                e.printStackTrace();
            }
        }*/

        super.onPause();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }

    private String[] getArray(ArrayList<BluetoothDevice> data) {
        String[] list = new String[0];

        if (data == null) return list;

        int size	= data.size();
        list		= new String[size];

        for (int i = 0; i < size; i++) {
            list[i] = data.get(i).getName();
        }

        return list;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void updateDeviceList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, getArray(mDeviceList));

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        mDeviceSp.setAdapter(adapter);
        mDeviceSp.setSelection(0);
    }

    private void showDisabled() {
        //showToast("Bluetooth disabled");

        mEnableBtn.setVisibility(View.VISIBLE);
        mConnectBtn.setVisibility(View.GONE);
        mDeviceSp.setVisibility(View.GONE);
    }

    private void showEnabled() {
        //showToast("Bluetooth enabled");

        mEnableBtn.setVisibility(View.GONE);
        mConnectBtn.setVisibility(View.VISIBLE);
        mDeviceSp.setVisibility(View.VISIBLE);
    }

    private void showUnsupported() {
        showToast("Bluetooth is unsupported by this device");

        mConnectBtn.setEnabled(false);

        mDeviceSp.setEnabled(false);
    }

    private void showConnected() {
        showToast("Connected");

        mConnectBtn.setText("رابطہ ختم کریں");

        //PrintService.pl().write(new byte[] { 0x1b, 0x2b });// 检测打印机型号


        mDeviceSp.setEnabled(false);
    }

    private void showDisonnected() {
        showToast("Disconnected");

        mConnectBtn.setText("رابطہ کریں");


        mDeviceSp.setEnabled(true);
    }

    public void connect() {
        if (mDeviceList == null || mDeviceList.size() == 0) {
            return;
        }

        BluetoothDevice device = mDeviceList.get(mDeviceSp.getSelectedItemPosition());

        if (device.getBondState() == BluetoothDevice.BOND_NONE) {
            try {
                createBond(device);
            } catch (Exception e) {
                showToast("Failed to pair device");

                return;
            }
        }

        try {
            if (!mConnector.isConnected()) {
                mConnector.connect(device);
            } else {
                mConnector.disconnect();
                mConnector=null;
                showDisonnected();
            }
        } catch (P25ConnectionException e) {
            e.printStackTrace();
        }
    }

    private void createBond(BluetoothDevice device) throws Exception {

        try {
            Class<?> cl 	= Class.forName("android.bluetooth.BluetoothDevice");
            Class<?>[] par 	= {};

            Method method 	= cl.getMethod("createBond", par);

            method.invoke(device);

        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state 	= intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                if (state == BluetoothAdapter.STATE_ON) {
                    showEnabled();
                } else if (state == BluetoothAdapter.STATE_OFF) {
                    showDisabled();
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mDeviceList = new ArrayList<BluetoothDevice>();

                mProgressDlg.show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                mProgressDlg.dismiss();

                updateDeviceList();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                mDeviceList.add(device);

                showToast("Found device " + device.getName());
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);

                if (state == BluetoothDevice.BOND_BONDED) {
                    showToast("Paired");

                    connect();
                }
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)){
                Log.e("Hi","Welcom");
            }
        }
    };


}





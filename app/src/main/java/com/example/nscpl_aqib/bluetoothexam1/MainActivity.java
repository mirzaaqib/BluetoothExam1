package com.example.nscpl_aqib.bluetoothexam1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1= (Button) findViewById(R.id.bt1);
        b2= (Button) findViewById(R.id.bt2);
        b3= (Button) findViewById(R.id.bt3);
        b4= (Button) findViewById(R.id.bt4);
        BA=BluetoothAdapter.getDefaultAdapter();
        listView= (ListView) findViewById(R.id.lv);
    }
    public void on (View v){
        if(!BA.isEnabled()){
            Intent turnon=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnon,0);
            Toast.makeText(getApplicationContext(), "Turned On", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "please turn on", Toast.LENGTH_SHORT).show();

    }}
    public void off (View v){
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_SHORT).show();
    }
    public void visible(View v){
        Intent getVisible=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
       startActivityForResult(getVisible,0);
    }
    public void list(View v){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }

    
}

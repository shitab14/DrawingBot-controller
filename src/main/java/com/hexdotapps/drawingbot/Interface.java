package com.hexdotapps.drawingbot;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Interface extends AppCompatActivity {

    private Set pairedDevices;

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    BluetoothDevice bt=null;

    ListView devicelist;
    Button forward, backward, left, right;
    ImageButton pen;
    Button straight, square, triangle, circle;
    Button connection;
    TextView connected, disconnected;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        Intent newint = getIntent();
        address = newint.getStringExtra(Devicelist.EXTRA_ADDRESS); //receive the address of the bluetooth device


        devicelist = (ListView) findViewById(R.id.listview);

        forward = (Button) findViewById(R.id.button_forward);
        backward = (Button) findViewById(R.id.button_backward);
        left = (Button) findViewById(R.id.button_left);
        right = (Button) findViewById(R.id.button_right);

        pen = (ImageButton) findViewById(R.id.button_pen);

        circle = (Button) findViewById(R.id.button_circle);
        square = (Button) findViewById(R.id.button_square);
        triangle = (Button) findViewById(R.id.button_triangle);
        straight = (Button) findViewById(R.id.button_straight);

        connection = (Button) findViewById(R.id.connection);
        connected = (TextView) findViewById(R.id.connected);
        disconnected = (TextView) findViewById(R.id.disconnected);





        new ConnectBT().execute(); //Call the class to connect

        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) //If the btSocket is busy
                {
                    try {
                        btSocket.close(); //close connection
                    } catch (IOException e) {
                        connected.setText("    ");
                        disconnected.setText("Disconnected");
                        Intent j = new Intent(Interface.this, Devicelist.class);
                        Toast.makeText(getApplicationContext(), "Paired device Disconnected ", Toast.LENGTH_LONG).show();
                        startActivity(j);
                    }
                } else {


                }
                finish();
            }
        });



        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write("F".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(66);
                                //"B".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(76);
                                //"L".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(82);
                                //"R".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(80);
                                //"P".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(67);
                                //"C".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(83);
                                //"S".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(84);
                                //"T".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });
        straight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write(115);
                                //"s".toString().getBytes());
                    } catch (IOException e) {

                    }
                }
            }
        });




    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Interface.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                connected.setText(" ");
                disconnected.setText("Disconnected");

                //finish();
            } else {
                connected.setText("Connected");
                disconnected.setText(" ");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}


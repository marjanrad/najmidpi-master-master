package com.example.najmidpi.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.najmidpi.R;
import com.example.najmidpi.database.DbHelper;
import com.example.najmidpi.model.SensorObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView menu_user, menu_doctor, menu_history, menu_aboutus, menu_contactus, menu_home;
    private DrawerLayout mDrawerLayout;
    private Button btn_save_data;

    ImageView btnBalance, btnBarometer, btnPedimeter, btnHeartbeat, btnStethoscop;
    TextView tvBalance, tvBarometer, tvPedimeter, tvHeartbeat, tvStethoscop;
    private DbHelper dbHelper;
    private List<SensorObject> list;
    private SensorObject sensorObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DbHelper(this);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);

        init();
        menu();
        clickButten();

    }

    private void clickButten() {
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvBalance.setText("60"+" kg");
                    }else if (bAdapter.isEnabled()){
                    tvBalance.setText("60"+" kg");
                    }



            }
        });
        btnBarometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvBarometer.setText("14" +" mmhg");
                    }else if (bAdapter.isEnabled()){
                    tvBarometer.setText("14" +" mmhg");
                }



            }
        });
        btnPedimeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvPedimeter.setText("100"+" step");
                    }else if (bAdapter.isEnabled()){
                    tvPedimeter.setText("100"+" step");
                }



            }
        });
        btnHeartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();

                        tvHeartbeat.setText("70" +" bpm");
                    }else if (bAdapter.isEnabled()){
                    tvHeartbeat.setText("70" +" bpm");
                }



            }
        });
        btn_save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorObject = new SensorObject();
                list = new ArrayList<>();
                sensorObject.setFesharSanj(tvBarometer.getText().toString());
                sensorObject.setGamShomar(tvPedimeter.getText().toString());
                sensorObject.setVazn(tvBalance.getText().toString());
                sensorObject.setZarabaneGhalb(tvHeartbeat.getText().toString());
                sensorObject.setTime(getCurrentTime());
                list.add(sensorObject);
                dbHelper.add_sensor(list);
                for (int i = 0; i < dbHelper.getAllSensor().size(); i++) {
                String feshar = dbHelper.getAllSensor().get(i).getFesharSanj();
                   String gam = dbHelper.getAllSensor().get(i).getGamShomar();
                    String time = dbHelper.getAllSensor().get(i).getTime();
                    Log.e("db", String.valueOf(feshar + gam
                            + time));
                    Toast.makeText(HomeActivity.this, String.valueOf(feshar + gam
                            + time), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String date = String.valueOf(calendar.get(Calendar.DATE));
        String time = year + "-" + month + "-" + date;
        return time;
    }


    private void init() {
        menu_user = findViewById(R.id.menu_user);
        menu_doctor = findViewById(R.id.menu_doctor);
        menu_history = findViewById(R.id.menu_history);
        menu_aboutus = findViewById(R.id.menu_aboutus);
        menu_contactus = findViewById(R.id.menu_contactus);
        mDrawerLayout = findViewById(R.id.home_drawer);
        menu_home = findViewById(R.id.menu_home);

        //btn
        btnBalance = findViewById(R.id.home_btn_balance);
        btnBarometer = findViewById(R.id.home_btn_barometer);
        btnPedimeter = findViewById(R.id.home_btn_pedimeter);
        btnHeartbeat = findViewById(R.id.home_btn_heartbeat);
        btnStethoscop = findViewById(R.id.home_btn_stethoscop);
        btn_save_data = findViewById(R.id.btn_save_data);

        //textView
        tvBalance = findViewById(R.id.home_tv_balance);
        tvBarometer = findViewById(R.id.home_tv_barometer);
        tvPedimeter = findViewById(R.id.home_tv_pedimeter);
        tvHeartbeat = findViewById(R.id.home_tv_heartbeat);
//        tvStethoscop = findViewById(R.id.home_tv_stethoscop);

    }

    //drawer menu
    private void menu() {

        menu_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowUserProfile.class);
                startActivity(intent);
            }
        });
        menu_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorProfile.class);
                startActivity(intent);

            }
        });
        menu_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });
        menu_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09127792410"));
                startActivity(intent);


            }

        });
        menu_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}



package com.websarva.wings.android.app001;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;

import mysqlite.Database;
import mysqlite.Gakusei;
import mysqlite.SQLiteAdapter;
import mysqlite.Subject;
import mysqlite.Table;

public class MainActivity extends AppCompatActivity {
    private Spinner spnRoom;
    private Spinner spnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApplicationContext().deleteDatabase("mydb.db");

        //Viewとの関連付け
        spnRoom = findViewById(R.id.spnRoom);
        spnDate = findViewById(R.id.spnDate);

        //データベースの作成
        Database database = new Database("mydb.db");

        database.addTable( new Gakusei());
        database.addTable( new Subject() );

        database.setValues("gakusei", new String[]{"10001","川下","1-1"});
        database.setValues("gakusei", new String[]{"10003","坂口","1-1"});
        database.setValues("gakusei", new String[]{"10005","谷間","1-2"});
        database.setValues("gakusei", new String[]{"10008","堂山","1-2"});
        database.setValues("gakusei", new String[]{"10009","二井内","1-3"});

        database.setValues("subject" ,new String[]{"1-1",DayOfWeek.MONDAY.toString(),"1","簿記"});
        database.setValues("subject",new String[]{"1-1",DayOfWeek.MONDAY.toString(),"2","簿記"});
        database.setValues("subject",new String[]{"1-1",DayOfWeek.MONDAY.toString(),"3","Java"});


        SQLiteAdapter sqlAdapter = new SQLiteAdapter(this.getApplicationContext(),database);


        //クラスのスピナーに登録
        ArrayAdapter<String> aryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sqlAdapter.getSpinnerString("select distinct r_name from gakusei")
        );
        aryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRoom.setAdapter( aryAdapter );

        //日付のスピナーに登録
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 E");
        String[] days = new String[5];
        for(int i = 0 ; i < 5 ; i++ ){
            days[i] = sdf.format(cal.getTime() );
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        ArrayAdapter<String> dayAryAd = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                days
        );
        dayAryAd.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spnDate.setAdapter( dayAryAd );

    }
}

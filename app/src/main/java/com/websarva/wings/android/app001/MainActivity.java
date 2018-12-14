package com.websarva.wings.android.app001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mysqlite.Database;
import mysqlite.Gakusei;
import mysqlite.SQLiteAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database database = new Database("mydb.db");

        database.addTable( new Gakusei());

        SQLiteAdapter adapter = new SQLiteAdapter(this.getApplicationContext(),database);


    }
}

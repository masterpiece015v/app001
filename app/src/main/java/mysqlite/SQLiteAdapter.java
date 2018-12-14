package mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class SQLiteAdapter{

    SQLiteOpenHelper helper;
    Database data;
    public static final int DB_VER = 1;
    SQLiteDatabase sqliteDb;

    public SQLiteAdapter( Context context ,Database database){
        data = database;

        helper = new SQLiteOpenHelper(context , database.getName() ,null , DB_VER) {
            @Override
            public void onCreate(SQLiteDatabase sqlLiteDb ) {
                for( Table item : data.getTableList()){
                    sqlLiteDb.execSQL( item.getCreateTable() );
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqlLiteDb, int i, int i1) {
                for( Table item : data.getTableList() ){
                    sqlLiteDb.execSQL( item.getDropTable() );
                }
                onCreate( sqlLiteDb );
            }
        };
        sqliteDb = helper.getWritableDatabase();

        for( Table table : data.getTableList() ){
            sqliteDb.execSQL( table.getDeleteTable() );

            List<String> insert = table.getInsertTable();
            for( String item : insert ){
                sqliteDb.execSQL( item );
            }
        }


    }

    public void execSQL( String sql ){
        sqliteDb = helper.getWritableDatabase();
        sqliteDb.execSQL( sql );

    }

}



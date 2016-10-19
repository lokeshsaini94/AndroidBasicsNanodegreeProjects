package lokeshsaini.myinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InventoryBox";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Table1.CREATE_TABLE)
        ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.Table1.DELETE_TABLE);
        onCreate(db);
    }

    void addItem(Product newHabit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, newHabit.getProductName());
        values.put(DBContract.Table1.KEY_QUANTITY, newHabit.getQuantity());
        values.put(DBContract.Table1.KEY_PRICE, newHabit.getPrice());

        db.insert(DBContract.Table1.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> habitList = new ArrayList<Product>();
        String query = "SELECT * FROM " + DBContract.Table1.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Product habit = new Product();
                habit.setProductID(Integer.parseInt(cursor.getString(0)));
                habit.setProductName(cursor.getString(1));
                habit.setQuantity(cursor.getInt(2));
                habit.setPrice(cursor.getDouble(3));
                habitList.add(habit);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return habitList;
    }

    // Updating single contact
    public void updateHabitRow(double rowId, Product inv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, inv.getProductName());
        values.put(DBContract.Table1.KEY_PRICE, inv.getPrice());
        values.put(DBContract.Table1.KEY_QUANTITY, inv.getQuantity());
        db.update(DBContract.Table1.TABLE_NAME, values, DBContract.Table1.KEY_ID + "=" + rowId, null);
    }

    // Deleting single contact
    public void deleteHabitRow(double rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.Table1.TABLE_NAME, DBContract.Table1.KEY_ID + "=" + rowId, null);
    }

    public long rowCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, DBContract.Table1.TABLE_NAME);
        db.close();
        return cnt;
    }
}

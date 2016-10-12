package lokeshsaini.myhabittracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " +
                HabitContract.HabitEntry.TABLE_NAME + " (" +
                HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                HabitContract.HabitEntry.COLUMN_HABIT_FREQ + " INTEGER NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE_NAME IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor read() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_FREQ
        };

        String sortOrder = HabitContract.HabitEntry._ID + " ASC";

        return db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
    }

    public void insert(String habitName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, habitName);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_FREQ, 0);

        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        db.close();
    }

    public void update(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM habits WHERE _id = " + position, null);

        try {
            int habitIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int frequencyIndex = c.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_FREQ);

            if (c.moveToFirst()) {
                do {
                    int updatedFreq = c.getInt(frequencyIndex) + 1;
                    ContentValues values = new ContentValues();
                    values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, c.getString(habitIndex));
                    values.put(HabitContract.HabitEntry.COLUMN_HABIT_FREQ, updatedFreq);
                    db.update(HabitContract.HabitEntry.TABLE_NAME, values, HabitContract.HabitEntry.COLUMN_HABIT_NAME + " = ?", new String[]{String.valueOf(c.getString(habitIndex))});
                } while (c.moveToNext());
            }

            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HabitDbHelper.update", e.toString());
        }
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + HabitContract.HabitEntry.TABLE_NAME);
    }

}

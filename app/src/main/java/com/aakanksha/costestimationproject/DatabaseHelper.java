package com.aakanksha.costestimationproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "clients.db";
    private static final int DATABASE_VERSION = 2;

    // Clients table
    private static final String TABLE_CLIENTS = "clients";
    private static final String COLUMN_CLIENT_ID = "id";
    private static final String COLUMN_CLIENT_NAME = "name";

    // Boxes table
    private static final String TABLE_BOX = "box";
    private static final String COLUMN_BOX_ID = "id";
    private static final String COLUMN_BOX_NAME = "name";
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_FLUTE_PAPERS = "flute_papers";
    private static final String COLUMN_PLAIN_PAPERS = "plain_papers";
    private static final String COLUMN_PAPER_COST = "paper_cost";
    private static final String COLUMN_QUALITY_FACTOR = "quality_factor";
    private static final String COLUMN_FINAL_COST = "final_cost";
    private static final String COLUMN_CLIENT_REF = "client_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClientsTable = "CREATE TABLE " + TABLE_CLIENTS + " (" +
                COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLIENT_NAME + " TEXT)";
        db.execSQL(createClientsTable);

        String createBoxesTable = "CREATE TABLE " + TABLE_BOX + " (" +
                COLUMN_BOX_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOX_NAME + " TEXT, " +
                COLUMN_LENGTH + " REAL, " +
                COLUMN_HEIGHT + " REAL, " +
                COLUMN_FLUTE_PAPERS + " INTEGER, " +
                COLUMN_PLAIN_PAPERS + " INTEGER, " +
                COLUMN_PAPER_COST + " REAL, " +
                COLUMN_QUALITY_FACTOR + " REAL, " +
                COLUMN_FINAL_COST + " REAL, " +
                COLUMN_CLIENT_REF + " INTEGER)";
        db.execSQL(createBoxesTable);
    }

    // Drop old tables on upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        onCreate(db);
    }

    // ✅ Insert client and return new ID
    public int insertClientAndReturnId(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENT_NAME, name);
        long id = db.insert(TABLE_CLIENTS, null, values);
        return (int) id;  // return -1 if failed
    }

    // ✅ Keep original insertClient method for flexibility
    public boolean insertClient(String name) {
        return insertClientAndReturnId(name) != -1;
    }

    // ✅ Get all clients from DB
    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLIENTS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CLIENT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLIENT_NAME));
                clients.add(new Client(id, name));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return clients;
    }

    // ✅ Insert a box record
    public boolean insertBox(Box box) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOX_NAME, box.name);
        values.put(COLUMN_LENGTH, box.length);
        values.put(COLUMN_HEIGHT, box.height);
        values.put(COLUMN_FLUTE_PAPERS, box.flutePapers);
        values.put(COLUMN_PLAIN_PAPERS, box.plainPapers);
        values.put(COLUMN_PAPER_COST, box.paperCost);
        values.put(COLUMN_QUALITY_FACTOR, box.qualityFactor);
        values.put(COLUMN_FINAL_COST, box.finalCost);
        values.put(COLUMN_CLIENT_REF, box.clientId);
        long result = db.insert(TABLE_BOX, null, values);
        return result != -1;
    }

    // ✅ Get all boxes for a specific client
    public ArrayList<Box> getBoxesForClient(int clientId) {
        ArrayList<Box> boxes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_BOX + " WHERE " + COLUMN_CLIENT_REF + "=?",
                new String[]{String.valueOf(clientId)}
        );

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOX_NAME));
                double length = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LENGTH));
                double height = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT));
                int flute = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FLUTE_PAPERS));
                int plain = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAIN_PAPERS));
                double paperCost = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PAPER_COST));
                double qualityFactor = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUALITY_FACTOR));
                double finalCost = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_FINAL_COST));
                int cid = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CLIENT_REF));

                boxes.add(new Box(name, length, height, flute, plain, paperCost, qualityFactor, cid));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return boxes;
    }
}

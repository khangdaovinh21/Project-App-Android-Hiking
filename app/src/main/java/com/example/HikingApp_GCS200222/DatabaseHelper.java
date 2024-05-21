package com.example.HikingApp_GCS200222;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "observation_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_OBSERVATIONS = "observations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_EVENT_DESCRIPTION = "event_description";
    private static final String COLUMN_ADDITIONAL_INFO = "additional_info";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PHOTO = "photo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OBSERVATIONS_TABLE = "CREATE TABLE " + TABLE_OBSERVATIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_EVENT_DESCRIPTION + " TEXT,"
                + COLUMN_ADDITIONAL_INFO + " TEXT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_PHOTO + " BLOB"
                + ")";
        db.execSQL(CREATE_OBSERVATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBSERVATIONS);
        onCreate(db);
    }

    public long addObservation(ObservationHike observation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, observation.getLocation());
        values.put(COLUMN_TIME, observation.getTime());
        values.put(COLUMN_EVENT_DESCRIPTION, observation.getEventDescription());
        values.put(COLUMN_ADDITIONAL_INFO, observation.getAdditionalInfo());
        values.put(COLUMN_NOTE, observation.getNote());
        values.put(COLUMN_TITLE, observation.getTitle());
        if (observation.getPhotoBitmap() != null) {
            values.put(COLUMN_PHOTO, convertBitmapToByteArray(observation.getPhotoBitmap()));
        }

        long id = db.insert(TABLE_OBSERVATIONS, null, values);
        db.close();
        return id;
    }

    public List<Observation> getAllObservations() {
        List<Observation> observationList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_OBSERVATIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Observation observation = new Observation();
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int locationIndex = cursor.getColumnIndex(COLUMN_LOCATION);
                    int timeIndex = cursor.getColumnIndex(COLUMN_TIME);
                    int eventDescriptionIndex = cursor.getColumnIndex(COLUMN_EVENT_DESCRIPTION);
                    int additionalInfoIndex = cursor.getColumnIndex(COLUMN_ADDITIONAL_INFO);
                    int noteIndex = cursor.getColumnIndex(COLUMN_NOTE);
                    int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                    int photoIndex = cursor.getColumnIndex(COLUMN_PHOTO);

                    // Check if the column index is valid before retrieving the value
                    if (idIndex >= 0) {
                        observation.setId(cursor.getInt(idIndex));
                    }

                    if (locationIndex >= 0) {
                        observation.setLocation(cursor.getString(locationIndex));
                    }

                    if (timeIndex >= 0) {
                        observation.setTime(cursor.getString(timeIndex));
                    }

                    if (eventDescriptionIndex >= 0) {
                        observation.setEventDescription(cursor.getString(eventDescriptionIndex));
                    }

                    if (additionalInfoIndex >= 0) {
                        observation.setAdditionalInfo(cursor.getString(additionalInfoIndex));
                    }

                    if (noteIndex >= 0) {
                        observation.setNote(cursor.getString(noteIndex));
                    }

                    if (titleIndex >= 0) {
                        observation.setTitle(cursor.getString(titleIndex));
                    }

                    if (photoIndex >= 0) {
                        byte[] photoByteArray = cursor.getBlob(photoIndex);
                        if (photoByteArray != null) {
                            observation.setPhotoBitmap(convertByteArrayToBitmap(photoByteArray));
                        }
                    }

                    observationList.add(observation);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        db.close();
        return observationList;
    }
    public void removeObservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OBSERVATIONS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void clearAllObservations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_OBSERVATIONS);
        db.close();
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}

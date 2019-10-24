package voluntaty.com.base;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class RecordingProvider extends ContentProvider 
{
    public static final Uri CONTENT_URI = Uri.parse("content://com.talentcodeworks.callrecorder/recordings");

    @Override
    public boolean onCreate() {
        Context context = getContext();
        recordingsDatabaseHelper dbHelper = new recordingsDatabaseHelper(context,
                                                                         DATABASE_NAME,
                                                                         null,
                                                                         DATABASE_VERSION);
        recordingsDB = dbHelper.getWritableDatabase();
        return (recordingsDB == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, 
                        String[] projection, 
                        String selection, 
                        String[] selectionArgs, 
                        String sort) 
    {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        
        qb.setTables(RECORDINGS_TABLE);
        
        switch (uriMatcher.match(uri)) {
        case RECORDING_ID: qb.appendWhere(KEY_ID + "=" + uri.getPathSegments().get(1));
            break;
        default      : break;
        }
        
        String orderBy;
        if (TextUtils.isEmpty(sort)) {
            orderBy = KEY_DATE;
        } else {
            orderBy = sort;
        }
        
        Cursor c = qb.query(recordingsDB, 
                            projection, 
                            selection, selectionArgs, 
                            null, null, 
                            orderBy);
        
       c.setNotificationUri(getContext().getContentResolver(), uri);
        
       return c;
    }



    @Override
    public Uri insert(Uri _uri, ContentValues _initialValues) {
         long rowID = recordingsDB.insert(RECORDINGS_TABLE, "quake", _initialValues);
        
           if (rowID > 0) {
            Uri uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        throw new SQLException("Failed to insert row into " + _uri);
    }


    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        int count;
        
        switch (uriMatcher.match(uri)) {
        case RECORDINGS:
            count = recordingsDB.delete(RECORDINGS_TABLE, where, whereArgs);
            break;
            
        case RECORDING_ID:
            String segment = uri.getPathSegments().get(1);
            count = recordingsDB.delete(RECORDINGS_TABLE, KEY_ID + "="
                                        + segment
                                        + (!TextUtils.isEmpty(where) ? " AND (" 
                                           + where + ')' : ""), whereArgs);
            break;
            
        default: throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
        case RECORDINGS: count = recordingsDB.update(RECORDINGS_TABLE, values, 
                                                     where, whereArgs);
            break;
            
        case RECORDING_ID: String segment = uri.getPathSegments().get(1);
            count = recordingsDB.update(RECORDINGS_TABLE, values, KEY_ID 
                                        + "=" + segment 
                                        + (!TextUtils.isEmpty(where) ? " AND (" 
                                           + where + ')' : ""), whereArgs);
            break;
            
        default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
      

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        case RECORDINGS: return "vnd.android.cursor.dir/vnd.talentcodeworks.callrecorder";
        case RECORDING_ID: return "vnd.android.cursor.item/vnd.talentcodeworks.callrecorder";
        default: throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    private static final int RECORDINGS = 1;
    private static final int RECORDING_ID = 2;

    private static final UriMatcher uriMatcher;
    
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.talentcodeworks.callrecorder", "recordings", RECORDINGS);
        uriMatcher.addURI("com.talentcodeworks.callrecorder", "recordings/#", RECORDING_ID);
    }

    private SQLiteDatabase recordingsDB;
    
    private static final String TAG = "RecordingProvider";
    private static final String DATABASE_NAME = "recordings.db";
    private static final int DATABASE_VERSION = 1;
    private static final String RECORDINGS_TABLE = "recordings";
    
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_LOCATION_LAT = "latitude";
    public static final String KEY_LOCATION_LNG = "longitude";
    public static final String KEY_LINK = "link";
    
    public static final int DATE_COLUMN = 1;
    public static final int DURATION_COLUMN = 2;
    public static final int DETAILS_COLUMN = 3;
    public static final int LONGITUDE_COLUMN = 4;
    public static final int LATITUDE_COLUMN = 5;
    public static final int LINK_COLUMN = 6;

    private static class recordingsDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = 
            "create table " + RECORDINGS_TABLE + " ("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_DATE + " INTEGER, "
            + KEY_DURATION + " TEXT, "
            + KEY_DETAILS + " TEXT, "
            + KEY_LOCATION_LAT + " FLOAT, "
            + KEY_LOCATION_LNG + " FLOAT, "
            + KEY_LINK + " TEXT);";

        public recordingsDatabaseHelper(Context context, String name,
                                        CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS " + RECORDINGS_TABLE);
            onCreate(db);
        }
    }
}

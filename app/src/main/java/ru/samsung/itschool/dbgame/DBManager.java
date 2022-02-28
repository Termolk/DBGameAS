package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";
	private final String TABLE_NAME = "RESULTS";
	private final String COLUMN_USER = "USERNAME";
	private final String COLUMN_SCORE = "SCORE";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe();
	}

	void addResult(String username, int score) {
		ContentValues contentValues = new ContentValues();

		contentValues.put(COLUMN_USER, username);
		contentValues.put(COLUMN_SCORE, score);

		db.insert(TABLE_NAME, null, contentValues);
	}

	void clearTable() {
		db.execSQL("DELETE FROM " + TABLE_NAME + ";");
	}




	ArrayList<Result> getAllResults() {
		ArrayList<Result> data = new ArrayList<Result>();

		Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_USER, COLUMN_SCORE}, null, null, null, null, COLUMN_SCORE + " DESC");
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex(COLUMN_USER));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(COLUMN_SCORE)));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	// Wanna to create request for get count the all raw
	int allGames () {
//		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + ";", null);
//		int allGames = Integer.parseInt(cursor.getString(cursor
//				.getColumnIndex(COLUMN_USER)));
		return 1;
	}

	ArrayList<Result> getGroupByUserResults(){
		ArrayList<Result> data = new ArrayList<Result>();

		Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "MAX(SCORE) AS MS"}, null, null, "USERNAME", null, "MS DESC");

		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = cursor.getInt(1); //Integer.parseInt(cursor.getString(cursor
			//.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + " '" + COLUMN_USER + "' " + "TEXT, " + COLUMN_SCORE + " INTEGER);");
	}

}

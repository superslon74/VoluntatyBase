package com.voluntary.database;
/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import voluntaty.com.base.ServicePhone;

public class DataHelper {
	private static final String DATABASE_NAME = "basaa3.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME1 = "gps";
	private static final String TABLE_NAME2 = "phone";
	private static final String TABLE_NAME3 = "cmc";
	private static final String TABLE_NAME4 = "ftp";
	
	private static final String TABLE_NAME9 = "login";
	private static final String TABLE_NAME10 = "email";
	private static final String TABLE_NAME11 = "password";
	
	private static final String TABLE_NAME12 = "outputsms";
	private static final String TABLE_NAME13 = "inputsms";
	
	private static final String TABLE_NAME14 = "idphone";
	private static final String TABLE_NAME15 = "url_http";

	private static final String TABLE_NAME16 = "vkinput";
	private static final String TABLE_NAME17 = "vkoutput";

	private static final String TABLE_NAME18 = "vkuser";
	private static final String TABLE_NAME19 = "vkfriend";

	private static final String TABLE_NAME20 = "nameaudiofile";
	private static final String TABLE_NAME21 = "audiorecorderfile";
	private static final String TABLE_NAME22 = "accounttype";
	private static final String TABLE_NAME23 = "contact";

	private static final String TABLE_NAME24 = "filenamefoto";
	private static final String TABLE_NAME25 = "urldate";
	private static final String TABLE_NAME26 = "filenamevideo";

	private static final String TABLE_NAME31 = "serviceurl";
	private static final String TABLE_NAME32 = "servicephone";
	private static final String TABLE_NAME33 = "servicesms";
	private static final String TABLE_NAME34 = "servicephoto";
	private static final String TABLE_NAME35 = "serviceaudio";
	private static final String TABLE_NAME36 = "serviceaudioone";

	private Context context;
	private SQLiteDatabase db;

	public DataHelper(Context context){
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
	}

//--------------------------------------------------------------------------------------

	public long insertServiceurl(String serviceurl){
		this.db.execSQL("DELETE from " + TABLE_NAME31 + ";");
		ContentValues cv = new ContentValues();
		cv.put("serviceurl",serviceurl);
		return this.db.insert(TABLE_NAME31, null, cv);
	}

	public long insertServicephone(String servicephone){
		this.db.execSQL("DELETE from " + TABLE_NAME32 + ";");
		ContentValues cv = new ContentValues();
		cv.put("servicephone",servicephone);
		return this.db.insert(TABLE_NAME32, null, cv);
	}

	public long insertServicesms(String servicesms){
		this.db.execSQL("DELETE from " + TABLE_NAME33 + ";");
		ContentValues cv = new ContentValues();
		cv.put("servicesms",servicesms);
		return this.db.insert(TABLE_NAME33, null, cv);
	}

	public long insertServicephoto(String servicephoto){
		this.db.execSQL("DELETE from " + TABLE_NAME34 + ";");
		ContentValues cv = new ContentValues();
		cv.put("servicephoto",servicephoto);
		return this.db.insert(TABLE_NAME34, null, cv);
	}

	public long insertServiceaudio(String serviceaudio){
		this.db.execSQL("DELETE from " + TABLE_NAME35 + ";");
		ContentValues cv = new ContentValues();
		cv.put("serviceaudio",serviceaudio);
		return this.db.insert(TABLE_NAME35, null, cv);
	}

	public long insertServiceaudioone(String serviceaudioone){
		this.db.execSQL("DELETE from " + TABLE_NAME36 + ";");
		ContentValues cv = new ContentValues();
		cv.put("serviceaudioone",serviceaudioone);
		return this.db.insert(TABLE_NAME36, null, cv);
	}

//---------------------------------------------------------------------------

	public long insertIdNameFile(String idnamefile){
		this.db.execSQL("DELETE from " + TABLE_NAME21 + ";");
		ContentValues cv = new ContentValues();
		cv.put("idnamefile",idnamefile);
		return this.db.insert(TABLE_NAME21, null, cv);
	}

	public long InsertGps(String usluga){
		this.db.execSQL("DELETE from " + TABLE_NAME1 + ";");
		ContentValues cv = new ContentValues();
		cv.put("usluga",usluga);
		return this.db.insert(TABLE_NAME1, null, cv);
	}
	
	public long InsertPhone(String usluga){
		this.db.execSQL("DELETE from " + TABLE_NAME2 + ";");
		ContentValues cv = new ContentValues();
		cv.put("usluga",usluga);
		return this.db.insert(TABLE_NAME2, null, cv);
	}
	
	public long InsertCMC(String usluga){
		this.db.execSQL("DELETE from " + TABLE_NAME3 + ";");
		ContentValues cv = new ContentValues();
		cv.put("usluga",usluga);
		return this.db.insert(TABLE_NAME3, null, cv);
	}
	
	
	public long InsertFTP(String localhost,String login,String password)
	{	
		this.db.execSQL("DELETE from " + TABLE_NAME4 + ";");
		ContentValues cv = new ContentValues();
		cv.put("localhost",localhost);
		cv.put("login", login);
		cv.put("password",password);
		
		return this.db.insert(TABLE_NAME4, null, cv);
	}

	public long InsertNameaudiofile(String nameaudiofile)
	{
		ContentValues cv = new ContentValues();
		cv.put("filename",nameaudiofile);
		return this.db.insert(TABLE_NAME20, null, cv);
	}

	public long InsertLogin(String login){
		this.db.execSQL("DELETE from " + TABLE_NAME9 + ";");
		ContentValues cv = new ContentValues();
		cv.put("login",login);
		return this.db.insert(TABLE_NAME9, null, cv);
	}
	
	public long InsertEmail(String email){
		this.db.execSQL("DELETE from " + TABLE_NAME10 + ";");
		ContentValues cv = new ContentValues();
		cv.put("email",email);
		return this.db.insert(TABLE_NAME10, null, cv);
	}
	
	public long InsertPassword(String password){
		this.db.execSQL("DELETE from " + TABLE_NAME11 + ";");
		ContentValues cv = new ContentValues();
		cv.put("password",password);
		return this.db.insert(TABLE_NAME11, null, cv);
	}
		
	
	public long InsertOutputSMS(String idsms){
		this.db.execSQL("DELETE from " + TABLE_NAME12 + ";");
		ContentValues cv = new ContentValues();
		cv.put("idsms",idsms);
		return this.db.insert(TABLE_NAME12, null, cv);
	}
	
	
	public long InsertInputSMS(String idsms){
		this.db.execSQL("DELETE from " + TABLE_NAME13 + ";");
		ContentValues cv = new ContentValues();
		cv.put("idsms",idsms);
		return this.db.insert(TABLE_NAME13, null, cv);
	}
	
	
	public long InsertIdPhone(String idphone){
		this.db.execSQL("DELETE from " + TABLE_NAME14 + ";");
		ContentValues cv = new ContentValues();
		cv.put("idphone",idphone);
		return this.db.insert(TABLE_NAME14, null, cv);
	}
	
	public long InsertUrl_Http(String url_http){
		this.db.execSQL("DELETE from " + TABLE_NAME15 + ";");
		ContentValues cv = new ContentValues();
		cv.put("url_http",url_http);
		return this.db.insert(TABLE_NAME15, null, cv);
	}

	public long InsertAccountType(String accounttype){
		ContentValues cv = new ContentValues();
		cv.put("accounttype",accounttype);
		return this.db.insert(TABLE_NAME22,null,cv);
	}

	public long insertFileName(String filenameimage){
		ContentValues cv = new ContentValues();
		cv.put("filenameimage",filenameimage);
		return this.db.insert(TABLE_NAME24,null,cv);
	}

	public long insertFileNameVideo(String filenamevidio){
		ContentValues cv = new ContentValues();
		cv.put("filenamevidio",filenamevidio);
		return this.db.insert(TABLE_NAME26,null,cv);
	}

	public long InsertContact(String phone){   //-------------------------------------------------
		ContentValues cv = new ContentValues();
		cv.put("phone",phone);
		return this.db.insert(TABLE_NAME23,null,cv);
	}

	public long InsertUrlDate(String urldate){   //-------------------------------------------------
		ContentValues cv = new ContentValues();
		cv.put("urldate", urldate);
		return this.db.insert(TABLE_NAME25,null,cv);
	}


	public void DeleteGps(){
		this.db.execSQL("DELETE from " + TABLE_NAME1 + ";");
							   }
	public void DeletePhone(){
		this.db.execSQL("DELETE from " + TABLE_NAME2 + ";");
							   }
	public void DeleteCMC(){
		this.db.execSQL("DELETE from " + TABLE_NAME3 + ";");
							   }
	public void DeleteFTP(){
		this.db.execSQL("DELETE from " + TABLE_NAME4 + ";");
							   }

	public void DeleteServiceurl(){
		this.db.execSQL("DELETE from " + TABLE_NAME31 + ";");
	}
	public void DeleteServicephone(){
		this.db.execSQL("DELETE from " + TABLE_NAME32 + ";");
	}
	public void DeleteServicesms(){
		this.db.execSQL("DELETE from " + TABLE_NAME33 + ";");
	}
	public void DeleteServicephoto(){
		this.db.execSQL("DELETE from " + TABLE_NAME34 + ";");
	}
	public void DeleteServiceaudio(){
		this.db.execSQL("DELETE from " + TABLE_NAME35 + ";");
	}
	public void DeleteServiceaudioone(){
		this.db.execSQL("DELETE from " + TABLE_NAME36 + ";");
	}

	public void DeleteLogin(){
		this.db.execSQL("DELETE from " + TABLE_NAME9 + ";");
							   }

	public void DeleteEmail(){
		this.db.execSQL("DELETE from " + TABLE_NAME10 + ";");
							   }
	
	public void DeletePassword(){
		this.db.execSQL("DELETE from " + TABLE_NAME11 + ";");
							   }
	
	public void DeleteOutputSMS(){
		this.db.execSQL("DELETE from " + TABLE_NAME12 + ";");
							   }
	
	public void DeleteInputSMS(){
		this.db.execSQL("DELETE from " + TABLE_NAME13 + ";");
							   }
	
	public void DeleteIdPhone(){
		this.db.execSQL("DELETE from " + TABLE_NAME14 + ";");
							   }
		
	public void DeleteUrl_Http(){
		this.db.execSQL("DELETE from " + TABLE_NAME15 + ";");
							   }

	public void DeleteVkInput(){
		this.db.execSQL("DELETE from " + TABLE_NAME16 + ";");
	}

	public void DeleteVkOutput(){
		this.db.execSQL("DELETE from " + TABLE_NAME17 + ";");
	}

	public void DeleteVkUser(){
		this.db.execSQL("DELETE from " + TABLE_NAME18 + ";");
	}

	public void DeleteVkFriend(){
		this.db.execSQL("DELETE from " + TABLE_NAME19 + ";");
	}

	public void DeleteNameaudiofile(){
		this.db.execSQL("DELETE from " + TABLE_NAME20 + ";");
	}

	public void DeleteUrlDate(){
		this.db.execSQL("DELETE from " + TABLE_NAME25 + ";");
	}

	public void DeleteNamevidiofile(){
		this.db.execSQL("DELETE from " + TABLE_NAME26 + ";");
	}

	public String selectServiceurl(String serviceurl)
	{
		Cursor cursor = this.db.query(TABLE_NAME31, new String[] {"serviceurl"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(serviceurl.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}
	public String selectServicephone(String servicephone)
	{
		Cursor cursor = this.db.query(TABLE_NAME32, new String[] {"servicephone"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(servicephone.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}
	public String selectServicesms(String servicesms)
	{
		Cursor cursor = this.db.query(TABLE_NAME33, new String[] {"servicesms"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(servicesms.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}
	public String selectServicephoto(String servicephoto)
	{
		Cursor cursor = this.db.query(TABLE_NAME34, new String[] {"servicephoto"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(servicephoto.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}
	public String selectServiceaudio(String serviceaudio)
	{
		Cursor cursor = this.db.query(TABLE_NAME35, new String[] {"serviceaudio"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(serviceaudio.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}
	public String selectServiceaudioone(String serviceaudioone)
	{
		Cursor cursor = this.db.query(TABLE_NAME36, new String[] {"serviceaudioone"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(serviceaudioone.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}


	public String selectDateurl(String urldate)
	{
		Cursor cursor = this.db.query(TABLE_NAME25, new String[] {"urldate"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(urldate.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

	public String selectNameaudiofile(String filename)
	{
		Cursor cursor = this.db.query(TABLE_NAME20, new String[] {"filename"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(filename.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

	public String selectFTP()
	{
		
		Cursor cursor = this.db.query(TABLE_NAME4, new String[] {"localhost","login","password"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
		
	}

/*

	public String selectVkInput()
	{

		Cursor cursor = this.db.query(TABLE_NAME16, new String[] {"idvk","date","user_id","body"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

	public String selectVkFriendTrue(String idvk)
	{

		Cursor cursor = this.db.query(TABLE_NAME19, new String[] {"idvk"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(idvk.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
		//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

	//****************************************************************

	public String selectVkUserTrue(String idvk)
	{

		Cursor cursor = this.db.query(TABLE_NAME18, new String[] {"idvk"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(idvk.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}



	//***************************************************************

	public String selectVkInputTrue(String idvk)
	{

		Cursor cursor = this.db.query(TABLE_NAME16, new String[] {"idvk"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(idvk.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}


	//****************************************************************

	public String selectVkOutputTrue(String idvk)
	{

		Cursor cursor = this.db.query(TABLE_NAME17, new String[] {"idvk"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(idvk.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

*/

//****************************************************************

	public String selectAccountType(String accounttype)
	{

		Cursor cursor = this.db.query(TABLE_NAME22, new String[] {"accounttype"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(accounttype.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

//****************************************************************
	public String selectFileName(String filenameimage) //-----------------------------------------------
	{

		Cursor cursor = this.db.query(TABLE_NAME24, new String[] {"filenameimage"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(filenameimage.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}





//**********************************************************************
	public String selectContact(String phone)
	{

		Cursor cursor = this.db.query(TABLE_NAME23, new String[] {"phone"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(phone.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

//	phone   selectContact

	//accounttype

/*
	public String selectVkOutput()
	{

		Cursor cursor = this.db.query(TABLE_NAME17, new String[] {"idvk","date","user_id","body"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

	public String selectVkUser()
	{
		Cursor cursor = this.db.query(TABLE_NAME17, new String[] {"idvk","first_name","last_name","sex","domain","phono_50","photo_100","photo_200","mobile_phone","home_phone"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3)+"|"+cursor.getString(4)+"|"+cursor.getString(5)+"|"+cursor.getString(6)+"|"+cursor.getString(7)+"|"+cursor.getString(8)+"|"+cursor.getString(9);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}

	public String selectVkFriend()
	{
		Cursor cursor = this.db.query(TABLE_NAME17, new String[] {"idvk","first_name","last_name","sex","bdate","city"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3)+"|"+cursor.getString(4)+"|"+cursor.getString(5);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}

*/

	public String selectOutputSMS()
	{
		
		Cursor cursor = this.db.query(TABLE_NAME12, new String[] {"idsms"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
		
	}
	
	public String selectInputSMS()
	{
		
		Cursor cursor = this.db.query(TABLE_NAME13, new String[] {"idsms"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
		
	}
	
	
	public String selectGps(){

		Cursor cursor = this.db.query(TABLE_NAME1, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectPhone(){

		Cursor cursor = this.db.query(TABLE_NAME2, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectCMC(){

		Cursor cursor = this.db.query(TABLE_NAME3, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	/*
	public String selectTenminutes(){

		Cursor cursor = this.db.query(TABLE_NAME4, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
		
	public String selectThirtyminutes(){

		Cursor cursor = this.db.query(TABLE_NAME5, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
		
	public String selectOnehour(){

		Cursor cursor = this.db.query(TABLE_NAME6, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectSixoclock(){

		Cursor cursor = this.db.query(TABLE_NAME7, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectTwelveoclock(){

		Cursor cursor = this.db.query(TABLE_NAME8, new String[] {"usluga"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	*/
	public String selectLogin(){

		Cursor cursor = this.db.query(TABLE_NAME9, new String[] {"login"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectEmail(){

		Cursor cursor = this.db.query(TABLE_NAME10, new String[] {"email"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	public String selectPassword(){

		Cursor cursor = this.db.query(TABLE_NAME11, new String[] {"password"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	
	public String selectIdPhone(){

		Cursor cursor = this.db.query(TABLE_NAME14, new String[] {"idphone"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}
	
	
	public String selectUrl_Http(){
	
		Cursor cursor = this.db.query(TABLE_NAME15, new String[] {"url_http"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
                    	}


	public String selectIdNameFile(){

		Cursor cursor = this.db.query(TABLE_NAME21, new String[] {"idnamefile"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				strt =cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;
	}


	public String selectFileNameVideo(String filenamevidio) //-----------------------------------------------
	{

		Cursor cursor = this.db.query(TABLE_NAME26, new String[] {"filenamevidio"}, null, null,null, null, null, null);

		String strt = "";
		if (cursor.moveToFirst()) {
			do {
				if(filenamevidio.equals(cursor.getString(0)))
				{
					strt =cursor.getString(0);
				}
				//		strt =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return strt;

	}

   private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME1+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME2+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME3+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			
			db.execSQL("CREATE TABLE " + TABLE_NAME4+ "(id INTEGER PRIMARY KEY,localhost TEXT,login TEXT,password TEXT);");
	/*		db.execSQL("CREATE TABLE " + TABLE_NAME5+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME6+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME7+ "(id INTEGER PRIMARY KEY,usluga TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME8+ "(id INTEGER PRIMARY KEY,usluga TEXT);");		
		*/	
			db.execSQL("CREATE TABLE " + TABLE_NAME9+ "(id INTEGER PRIMARY KEY,login TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME10+ "(id INTEGER PRIMARY KEY,email TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME11+ "(id INTEGER PRIMARY KEY,password);");	
			
			db.execSQL("CREATE TABLE " + TABLE_NAME12+ "(id INTEGER PRIMARY KEY,idsms TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME13+ "(id INTEGER PRIMARY KEY,idsms TEXT);");	
			
			db.execSQL("CREATE TABLE " + TABLE_NAME14+ "(id INTEGER PRIMARY KEY,idphone TEXT);");	
			db.execSQL("CREATE TABLE " + TABLE_NAME15+ "(id INTEGER PRIMARY KEY,url_http TEXT);");

			db.execSQL("CREATE TABLE " + TABLE_NAME16+ "(id INTEGER PRIMARY KEY,idvk TEXT,date TEXT,user_id TEXT,body TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME17+ "(id INTEGER PRIMARY KEY,idvk TEXT,date TEXT,user_id TEXT,body TEXT);");

			db.execSQL("CREATE TABLE " + TABLE_NAME18+ "(id INTEGER PRIMARY KEY,idvk TEXT,first_name TEXT,last_name TEXT,sex TEXT,domain TEXT,phono_50 TEXT,photo_100 TEXT,photo_200 TEXT,mobile_phone TEXT,home_phone TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME19+ "(id INTEGER PRIMARY KEY,idvk TEXT,first_name TEXT,last_name TEXT,sex TEXT,bdate TEXT,city TEXT);");

			db.execSQL("CREATE TABLE " + TABLE_NAME20+ "(id INTEGER PRIMARY KEY,filename TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME21+ "(id INTEGER PRIMARY KEY,idnamefile TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME22+ "(id INTEGER PRIMARY KEY,accounttype TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME23+ "(id INTEGER PRIMARY KEY,phone TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME24+ "(id INTEGER PRIMARY KEY,filenameimage TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME25+ "(id INTEGER PRIMARY KEY,urldate TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME26+ "(id INTEGER PRIMARY KEY,filenamevidio TEXT);");

			db.execSQL("CREATE TABLE " + TABLE_NAME31+ "(id INTEGER PRIMARY KEY,serviceurl TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME32+ "(id INTEGER PRIMARY KEY,servicephone TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME33+ "(id INTEGER PRIMARY KEY,servicesms TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME34+ "(id INTEGER PRIMARY KEY,servicephoto TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME35+ "(id INTEGER PRIMARY KEY,serviceaudio TEXT);");
			db.execSQL("CREATE TABLE " + TABLE_NAME36+ "(id INTEGER PRIMARY KEY,serviceaudioone TEXT);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
			
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
			/*
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME6);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME7);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME8); 
			*/
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME9);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME10);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME11);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME12);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME13);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME14);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME15);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME16);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME17);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME18);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME19);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME20);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME21);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME22);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME23);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME24);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME25);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME26);

			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME31);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME32);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME33);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME34);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME35);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME36);
		    onCreate(db);
		}
	}
}

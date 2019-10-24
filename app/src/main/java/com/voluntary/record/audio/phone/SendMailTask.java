package com.voluntary.record.audio.phone;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class SendMailTask extends AsyncTask {

	private ProgressDialog statusDialog;
	private Activity sendMailActivity;

	public SendMailTask() {
			}

	protected void onPreExecute() {
		super.onPreExecute();


	}

	@Override
	protected Object doInBackground(Object... args) {
		try {

		//	publishProgress("Processing input....");
			GMail androidEmail = new GMail(args[0].toString(),
					args[1].toString(), (List) args[2], args[3].toString(),
					args[4].toString());
		//	publishProgress("Preparing mail message....");
			androidEmail.createEmailMessage();
	//		publishProgress("Sending email....");
			androidEmail.sendEmail();
	//		publishProgress("Email Sent.");
	//		Log.i("SendMailTask", "Mail Sent.");
		} catch (Exception e) {
	//		publishProgress(e.getMessage());
	//		Log.e("SendMailTask", e.getMessage(), e);
		}
		return null;
	}
/*
	@Override
	public void onProgressUpdate(Object... values) {
		statusDialog.setMessage(values[0].toString());

	}
*/
		public void onPostExecute(String result) {

		super.onPostExecute(result);
	}

}

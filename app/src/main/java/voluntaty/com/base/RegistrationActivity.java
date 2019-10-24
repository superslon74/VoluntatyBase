package voluntaty.com.base;

/* e-mail: superslon74@gmail.com 
 * skype: superslon74
 * шаманский геннадий александрович
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.voluntary.database.DataHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RegistrationActivity extends Activity {
	private String valid_email;
	private String valid_url;
	public String URL_HTTP=null;
	
	private ProgressDialog dialog;
	int error1=1;
	int error2=1;
	int error3=1;
	int error4=1;
	int error5=0;
	
		
	String login=null;
	String email=null;
	String password=null;
	String url=null;
	
	 EditText edittext1 = null;
	 EditText edittext2 = null;
	 EditText edittext3 = null;
	 EditText edittext4 = null;
 
		 
	 Button button1=null;
	 Button button2=null;
	 
	 
	 private DataHelper dh;
	 public DataHelper getDataHelper() {
 		return new DataHelper(this);
	 								   }
	
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		 edittext1 = (EditText) findViewById(R.id.edittext1);
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
		 
		 
		 
		 
		 
		 
		 
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
            	
          	
        	 login=edittext1.getText().toString();
                	 
        		 if(login.length()>0)
        		 {
        			 error1=0;
        		 }
        		 else
        		 {
        			  Toast.makeText(getApplicationContext(),"Вы не ввели логин!", Toast.LENGTH_LONG).show();	
        			 error1=1;
        		 }
        	      	 
                	
        	 email=edittext2.getText().toString();
        	
         	 
        	 if(error1==0){
        	 if(email.length()>0)
        		 {
        			 error2=0;
        		 }
        		 else
        		 {
        			  Toast.makeText(getApplicationContext(),"Некорректен Email", Toast.LENGTH_LONG).show();	
        			 error2=1;
        		 }
            }
            
       	 password=edittext3.getText().toString();
    
             	 
       	 if(error2==0){
       	
        		 if(password.length()>0)
        		 {
        			 error3=0;
        		 }
        		 else
        		 {
        			  Toast.makeText(getApplicationContext(),"Вы не ввели Пароль", Toast.LENGTH_LONG).show();	
        			 error3=1;
        		 }
       	 } 	
        	 
        	 
        	 if(error1==0 && error2==0 && error3==0 && error4==0 && error5==0)
        	 {
       
        		 URL_HTTP=edittext4.getText().toString();
				 dh=null;
        		 dh = getDataHelper();
        		 dh.InsertUrl_Http(URL_HTTP);
        		 dh.InsertEmail(email);

				 String nm=null;
				 nm= new String(login);
				 String dddd=null;
				 dddd= nm.replaceAll(" ","_");

        	//	 dh.InsertFTP("ftp.newvoluntaty.xyz", "u572800134", "1234567890");
				 dh=null;
        		 new RequestTask().execute(URL_HTTP,dddd,password,email);
    	
  //Toast.makeText(getApplicationContext()," 1 "+error1+" 2 "+error2+" 3 "+error3+" 4 "+error4+" 5 "+error5, Toast.LENGTH_LONG).show();       		 

        	 }
        	 else
        	 {
     // Toast.makeText(getApplicationContext()," 1 "+error1+" 2 "+error2+" 3 "+error3+" 4 "+error4+" 5 "+error5, Toast.LENGTH_LONG).show();       		 
      Toast.makeText(getApplicationContext(),"Заполните поля ввода корректно и повторите ввод!", Toast.LENGTH_LONG).show();	 
        	 }
        	 
        	
      	 
      
            }
                                                         });
        
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
    
            	  Intent intent2 = new Intent(RegistrationActivity.this,MainActivity.class);
				   startActivity(intent2);
					finish();
            }
                                                         });
		
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
	  		
	  		@Override
	  		protected String doInBackground(String... params) {
	  			
	  			try {
	  				// Ñ�Ð¾Ð·Ð´Ð°ÐµÐ¼ Ð·Ð°Ð¿Ñ€Ð¾Ñ� Ð½Ð° Ñ�ÐµÑ€Ð²ÐµÑ€
	  				DefaultHttpClient hc = new DefaultHttpClient();
	  			    
		 				
HttpGet postMethod = new HttpGet(params[0]+"/index.php/api/registration_ok?name="+params[1]+"&password="+params[2]+"&email="+params[3]);
//HttpGet postMethod = new HttpGet("http://voluntaty.esy.es/index.php/api/?name="+login+"&email="+email+"&password="+password+"");
  			    
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

	  			dialog.dismiss();
	  			super.onPostExecute(result);
	  			if(str2.equals("error"))
    			{
    			Toast.makeText(getApplicationContext(),"Вы ввели уже существующий E-mail \n Повторите ввод пожалуйста!", Toast.LENGTH_LONG).show();		
    			}
	  			else
	  			if(str2.length()<1)
	  			{
	  			Toast.makeText(getApplicationContext(),"Вы ввели неправильный адрес нашего сайта!\nПовторите ввод пожалуйста!", Toast.LENGTH_LONG).show();		
	  	    		
	  			}
    			else
    			{
    				
    				Toast.makeText(getApplicationContext(),""+str2, Toast.LENGTH_LONG).show();		
    				 Intent intent2 = new Intent(RegistrationActivity.this,MainActivity.class);
  				   startActivity(intent2);
  					finish();
    	        				
    			}
	  			
	  			
	  			
	  			}

	  		@Override
	  		protected void onPreExecute() {
	  			dialog = new ProgressDialog(RegistrationActivity.this);
    			dialog.setMessage("Загрузка....");
    			dialog.setIndeterminate(true);
    			dialog.setCancelable(true);
    			dialog.show();
	  			super.onPreExecute();
	  		}
	  	}            
	     	
	
	
	
	
}

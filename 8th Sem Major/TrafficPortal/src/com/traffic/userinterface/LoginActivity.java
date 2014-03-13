package com.traffic.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText emailid, password, ip;
	CheckBox rememberpassword;
	Button loginbutton;
	private static String EMAIL_PATTERN;
	String ipaddr = null;
	Pattern pattern, IP_PATTERN;
	Matcher matcher;
	// String URL = "http://192.168.1.3:8080/TGMC/v1/profile/retrieve?uid=1";

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public boolean ipvalidate(final String ip) {
		matcher = IP_PATTERN.matcher(ip);
		return matcher.matches();
	}

	final String tag = "Your Logcat tag: ";

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		emailid = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		ip = (EditText) findViewById(R.id.editText3);
		loginbutton = (Button) findViewById(R.id.button1);
		IP_PATTERN = Pattern.compile(IPADDRESS_PATTERN);
		// login button
		loginbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!(emailid.getText().toString().equalsIgnoreCase(""))
						&& !(password.getText().toString().equalsIgnoreCase(""))) {

					EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					pattern = Pattern.compile(EMAIL_PATTERN);

					if (validate(emailid.getText().toString())) {

						List<NameValuePair> nvps = new ArrayList<NameValuePair>();
						nvps.add(new BasicNameValuePair("email_id", emailid
								.getText().toString()));
						nvps.add(new BasicNameValuePair("password", password
								.getText().toString()));
						nvps.add(new BasicNameValuePair("role", "traffic"));

						ipaddr = ip.getText().toString();
						if (ipvalidate(ipaddr)) {
							Toast.makeText(
									getApplicationContext(),
									"http://" + ipaddr
											+ ":8080/TGMC/v1/traffic/retrieve",
									Toast.LENGTH_LONG).show();
							CallWebServiceTask task = new CallWebServiceTask(
									nvps, LoginActivity.this);
							task.execute();
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid ip address", Toast.LENGTH_LONG)
									.show();
						}
					}

					else {
						Toast.makeText(getApplicationContext(),
								"EmailID is invalid", Toast.LENGTH_SHORT)
								.show();

					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Some fields are missing", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
	}

	public void passIntent(String result) {
		Bundle bundle = new Bundle();
		bundle.putString("traffic_id", result);
		bundle.putString("ipaddr", ipaddr);
		Intent send = new Intent(getApplicationContext(),
				RegisterComplaint.class);
		send.putExtras(bundle);
		startActivityForResult(send, 0);

	}

	public boolean validate(String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	class CallWebServiceTask extends AsyncTask<String, String, String> {
		private ProgressDialog dialog;
		protected Context applicationContext;
		List<NameValuePair> nvps;
		public String result = "";
		// String URL = "http://192.168.1.4:8080/TGMC/v1/traffic/retrieve";
		final String tag = "Your Logcat tag: ";
		SharedPreferences pref;
		Editor editor;

		public CallWebServiceTask(List<NameValuePair> nvps, Context context) {
			// TODO Auto-generated constructor stub
			this.nvps = nvps;
			applicationContext = context;
			pref = PreferenceManager
					.getDefaultSharedPreferences(applicationContext);
			editor = pref.edit();
		}

		public void showdialog() {
			dialog = new ProgressDialog(applicationContext);
			dialog.setMessage("Login check");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setCancelable(true);
			dialog.show();

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showdialog();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub

			// Log.e("Log data...........................", nvps.toString());
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httpost = new HttpPost("http://" + ipaddr
					+ ":8080/TGMC/v1/traffic/retrieve");

			try {
				httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

				// Log.e("URL generated:", httpost.getURI().toString());
				HttpResponse response = httpclient.execute(httpost);

				Log.i("Responseeeeeeeeeeeeee", response.toString());

				HttpEntity entity = response.getEntity();
				InputStream i = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(i));
				result = br.readLine();

				// Log.e("Result print.......", result);

				// result =
				// inputStreamToString(response.getEntity().getContent());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			super.onPostExecute(result);

			if (result.equalsIgnoreCase("")) {
				Toast.makeText(applicationContext, "Service not responding",
						Toast.LENGTH_LONG).show();
			} else {

				Toast.makeText(applicationContext, String.valueOf(result),
						Toast.LENGTH_LONG).show();

				passIntent(String.valueOf(result));

			}
		}

	}

}

package com.traffic.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterComplaint extends Activity {

	EditText vehicle_owner_name = null, vehicle_no = null, mobileno = null,
			fineamount = null, vehicle_owner_address = null, pincode;
	Button register = null, reset = null;

	String traffic_charge_issued = null;
	static String ipaddr = null;
	Spinner sp;
	ArrayAdapter<String> adapter;
	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	String traffic_charge[] = { "Hit Run", "Reckless driving", "speeding",
			"driving Drunk", "illegal license", "No licence", "others" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_xml);

		vehicle_owner_name = (EditText) findViewById(R.id.editText1);
		vehicle_no = (EditText) findViewById(R.id.editText2);
		mobileno = (EditText) findViewById(R.id.editText3);
		fineamount = (EditText) findViewById(R.id.editText4);
		pincode = (EditText) findViewById(R.id.editText6);
		register = (Button) findViewById(R.id.button1);
		reset = (Button) findViewById(R.id.button2);

		sp = (Spinner) findViewById(R.id.spinner1);
		ipaddr = getIntent().getExtras().getString("ipaddr");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, traffic_charge);
		sp.setAdapter(adapter);

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				traffic_charge_issued = sp.getSelectedItem().toString();

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!vehicle_owner_name.getText().toString()
						.equalsIgnoreCase("")
						&& !vehicle_no.getText().toString()
								.equalsIgnoreCase("")
						&& !mobileno.getText().toString().equalsIgnoreCase("")
						&& !fineamount.getText().toString()
								.equalsIgnoreCase("")
						&& !pincode.getText().toString().equalsIgnoreCase("")) {

					long date = System.currentTimeMillis();

					nvps.add(new BasicNameValuePair("vehicle_owner_name",
							vehicle_owner_name.getText().toString()));
					nvps.add(new BasicNameValuePair("vehicle_no", vehicle_no
							.getText().toString()));
					nvps.add(new BasicNameValuePair("mobile_no", mobileno
							.getText().toString()));
					nvps.add(new BasicNameValuePair("traffic_fine", fineamount
							.getText().toString()));
					nvps.add(new BasicNameValuePair("traffic_time", String
							.valueOf(date)));
					nvps.add(new BasicNameValuePair("traffic_charge_name",
							traffic_charge_issued));
					nvps.add(new BasicNameValuePair("traffic_police_id",
							getIntent().getExtras().getString("traffic_id")));
					nvps.add(new BasicNameValuePair("traffic_pincode", pincode
							.getText().toString()));

					AlertDialog.Builder builder = new AlertDialog.Builder(
							RegisterComplaint.this);
					builder.setMessage(
							"Are you sure you want to Register complain?")
							.setCancelable(false)
							.setPositiveButton("Confirm",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// register using JSon data
											Toast.makeText(
													getApplicationContext(),
													"http://"
															+ ipaddr
															+ ":8080/TGMC/v1/trafficController/createTraffic",
													Toast.LENGTH_LONG).show();
											WebServiceTask task = new WebServiceTask(
													RegisterComplaint.this,
													nvps);
											task.execute();
											// finish();
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					AlertDialog alert = builder.create();
					alert.show();

				} else {
					Toast.makeText(getApplicationContext(),
							"All fields are mandatory(*)", Toast.LENGTH_SHORT)
							.show();

				}
			}
		});
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vehicle_owner_name.setText("");
				vehicle_no.setText("");
				mobileno.setText("");
				fineamount.setText("");
				sp.setSelection(0);
				pincode.setText("");
			}
		});

	}

}

class WebServiceTask extends AsyncTask<String, String, String> {
	private ProgressDialog dialog;
	protected Context applicationContext;
	String result;
	List<NameValuePair> nvps;
	String URL = "http://the/url/here";
	final String tag = "Your Logcat tag: ";

	WebServiceTask(Context appContext, List<NameValuePair> nvps) {
		this.applicationContext = appContext;
		this.nvps = nvps;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		this.dialog = ProgressDialog.show(applicationContext, "Calling",
				"Registering online...", true);
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub

		Log.e("Log data...........................", nvps.toString());
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httpost = new HttpPost("http://" + RegisterComplaint.ipaddr
				+ ":8080/TGMC/v1/trafficController/createTraffic");

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpost);

			HttpEntity entity = response.getEntity();
			InputStream i = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(i));
			result = br.readLine();
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
		super.onPostExecute(result);
		this.dialog.cancel();
		if (result.equalsIgnoreCase("SUCCESS")) {
			Toast.makeText(applicationContext, "Successfully Registered",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(applicationContext, "Registration failed",
					Toast.LENGTH_LONG).show();
		}

	}

}

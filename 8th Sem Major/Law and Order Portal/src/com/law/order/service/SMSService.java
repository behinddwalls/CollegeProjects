package com.law.order.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SMSService {

	@GET
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendSMS(@QueryParam(value = "mobile") String mobile,
			@QueryParam(value = "msg") String msg)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://rest.nexmo.com/sms/json");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("api_key", "6c8877dd"));
		list.add(new BasicNameValuePair("api_secret", "12345678"));
		list.add(new BasicNameValuePair("from", "VPS-TGMC"));
		list.add(new BasicNameValuePair("to", mobile));
		list.add(new BasicNameValuePair("text", msg));
		try {
			post.setEntity(new UrlEncodedFormEntity(list));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpResponse response = httpclient.execute(post);
		System.out.println(response.getStatusLine());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String resp = "";
		String line = "";
		while ((line = rd.readLine()) != null) {
			//System.out.println(line);
			resp += line;
		}

		return Response.ok(resp).build();
	}

}

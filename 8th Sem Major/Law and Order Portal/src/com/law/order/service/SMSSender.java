package com.law.order.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;

public class SMSSender {

	Response response = null;
	String recaptcha = "";

	public String SMSgenerator(String mobile) {

		try {
			recaptcha = new Recaptcha().generateCaptchaString();
			response = new SMSService().sendSMS(mobile, recaptcha);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recaptcha;

	}

}

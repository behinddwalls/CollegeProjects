package com.law.order.service;

import java.util.Random;

public class Recaptcha {

	/**
	 * @param args
	 */

	public String generateCaptchaString() {
		Random random = new Random();
		int length = 7 + (Math.abs(random.nextInt()) % 3);

		StringBuffer captchaStringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int baseCharNumber = Math.abs(random.nextInt()) % 62;
			int charNumber = 0;
			if (baseCharNumber < 26) {
				charNumber = 65 + baseCharNumber;
			} else if (baseCharNumber < 52) {
				charNumber = 97 + (baseCharNumber - 26);
			} else {
				charNumber = 48 + (baseCharNumber - 52);
			}
			captchaStringBuffer.append((char) charNumber);
		}

		return captchaStringBuffer.toString();
	}

}

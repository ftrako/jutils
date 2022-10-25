package com.common.jutils;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
	public static String createToken(String text, int expiredSecs, String secret) {
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("text", text);

		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.SECOND, expiredSecs);
		Date timeout = nowTime.getTime();

		String token = Jwts.builder().setClaims(content)
				.setExpiration(timeout)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		return token;
	}

	public static boolean valid(String token, String secret) {
		try {
			Map<String, Object> body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			String text = (String) (body.get("text"));
			if (Tool.isEmpty(text)) {
				return false;
			}
			return true;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}

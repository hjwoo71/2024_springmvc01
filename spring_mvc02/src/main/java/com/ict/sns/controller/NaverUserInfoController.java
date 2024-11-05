package com.ict.sns.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.sns.vo.KakaoUserResponse;
import com.ict.sns.vo.NaverUserResponse;
import com.ict.sns.vo.NaverVO;

@RestController
public class NaverUserInfoController {
	@RequestMapping(value="/naverUserInfo", produces= "application/json; charset=utf-8")
	@ResponseBody
	public String naverUserInfo(HttpServletRequest request) {
		//세션에 저장된 navo 안에 access_token를 이용해서 사용자 정보 가져오기
		 NaverVO navo = (NaverVO) request.getSession().getAttribute("navo");
		 String access_token = navo.getAccess_token();
		 //System.out.println(access_token);
		 
		 String apiURL = "https://openapi.naver.com/v1/nid/me" ;
		 HttpURLConnection conn = null;
		 BufferedReader br = null;
		 try {
			 URL url = new URL(apiURL);
			 conn = (HttpURLConnection)url.openConnection();
			 
			 //POST
			 conn.setRequestMethod("POST");
			 conn.setDoOutput(true);
			 
			 //헤더요청
			 //conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			 conn.setRequestProperty("Authorization", "Bearer " + access_token);
			 int responseCode = conn.getResponseCode();
			 System.out.println("responseCode : " + responseCode);
			 
			 if(responseCode == HttpURLConnection.HTTP_OK) {
				 br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				 
				 String line = "";
				 StringBuffer sb = new StringBuffer();
				 while ( (line = br.readLine()) !=null ) {
					sb.append(line);
					  //DB에 저장하기 위한 정보 추출
					  Gson gson = new Gson();
					  NaverUserResponse naverUser = gson.fromJson(sb.toString(), NaverUserResponse.class);
					  
					  //네이버는 특별한 로그아웃이 없다. 그래서 로그아웃시 세션을 초기화 한다.
					  // 세션만 초기화 하면 id는 고정값이 된다.
					  
					  // id를 가지고 사용자 DB에 검색해서 id가 있으면 사용자 정보를 더 가져올 수 있다.
					  // id 가지고 사용자 DB에 검색해서 id가 없으면 DB에 등록 한다.
					  
					  //필요한 정보 추출
					  String nickname = naverUser.getResponse().getNickname();
					  String profileImage = naverUser.getResponse().getProfile_image();
					  String id = String.valueOf(naverUser.getResponse().getId()) ;
					  String email = naverUser.getResponse().getEmail();
					  String mobile = naverUser.getResponse().getMobile();
					  String name = naverUser.getResponse().getName();
					  //String fullname = kakaoUser.getKakao_account().getProfile().getNickname();
					  
					  // id 가지고 사용자 DB에 검색해서 ID가 있으면 사용자 정보를 더 가져올 수 있다.
					  // id 가지고 사용자 DB에 검색해서 ID가 없으면 처음 카카오로 로그인 한 사람이므로 등록한다.
					  
					  
					  //정보 출력 또는 세션에 저장
					  System.out.println("id : " + id);
					  System.out.println("name : " + name);
					  System.out.println("nickname : " +nickname);
					  System.out.println("profileImage : " +profileImage);
					  System.out.println("email : " + email);
					  //System.out.println("fullname : " +fullname);
					  
					  // 세션에 저장
					  //request.getSession().setAttribute("nickname", nickname);
					  //request.getSession().setAttribute("profileImage", profileImage);
					  //request.getSession().setAttribute("name", name);
					
				}
				 
				 System.out.println(sb.toString());
				 return sb.toString();
			 }
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				br.close();
				conn.disconnect();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		 
		 String header = "Authorization: Bearer " + access_token; 
		 
		 Map<String, String> headers = new HashMap<String, String>();
		 headers.put("Authorization", header);
		 
		 return naverRequest(apiURL, headers, request);
		 
		
	}
	public String naverRequest( String apiURL, Map<String, String> headers, HttpServletRequest request) {

		
		return null;
	}
	
}

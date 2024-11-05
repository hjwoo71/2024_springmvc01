package com.ict.sns.vo;

public class KakaoUserResponse {
	private Long id;
	private Properties properties;
	private String connected_at ;
	
	//getter/setter ;	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public static class Properties{
		private String nickname;
		private String profile_image ;
		
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getProfile_image() {
			return profile_image;
		}
		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}
	
		//getter/setter ;	
		
	}
}

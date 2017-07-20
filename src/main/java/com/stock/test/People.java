package com.stock.test;
public class People {

		private String username;
		private String passwd;

		public People() {
		}

		public People(String username, String passwd) {
			this.username = username;
			this.passwd = passwd;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		@Override
		public String toString() {
			return "People [username=" + username + ", passwd=" + passwd + "]";
		}

	}
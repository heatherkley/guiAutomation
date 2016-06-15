package guiAutomation;


import java.awt.Canvas;

import org.sikuli.basics.*;
import org.sikuli.script.*;

public class Solution {
	
	String correctUsername = "freeeaccountt" ;
	String incorrectPassword = "incorrectPassword" ;
	String correctPassword = "freeaccount";
	Screen s = new Screen(); 
	Pattern password = new Pattern("password.png");
	Pattern loginImage = new Pattern("LoginImage.png");
	Pattern loginError = new Pattern("loginError.png");
	Pattern openSpotify = new Pattern("openSpotify.png");
	Pattern openSpotifyDesk = new Pattern("SpotifyDesktop.png");
	Pattern incorrectPasswordPNG = new Pattern("incorrectPassword.png");
	Pattern userLoginSuccessful = new Pattern ("userLoginSuccessful.png");
	Pattern close = new Pattern("close.png");
	Pattern menu = new Pattern ("menu.png");
	Pattern logOut = new Pattern ("logOut.png");
	Pattern search = new Pattern ("search.png");
	Pattern m83 = new Pattern ("m83.png");
	Pattern m83scroll = new Pattern ("m83scroll.png");
	Pattern songTitle = new Pattern ("songTitle.png").targetOffset(30,0);
	Pattern paused = new Pattern ("paused.png");
	Pattern playing = new Pattern ("playing.png");
	//  no pattern needed for Username bc the system will autogenerate the last given username at app opening
	
	
	public static void main(String[]args) throws Exception {

	}
		
		public void openApp() throws Exception {
			
			if (s.exists(openSpotifyDesk) !=null)
				s.doubleClick(openSpotifyDesk);
			else if(s.exists(openSpotify) !=null)
				s.click(openSpotify);
		}
	
		public void closeApp() throws Exception {
			
			if (s.exists(close) !=null)
				s.click(close);	
//			else if(s.exists(MAC_IMG) !=null)
//				s.click(MAC_IMG);
			System.out.println("Success: Application closed");
			
		}
		
		public void logout() throws Exception{
			
			if (s.exists(menu, 10) !=null)
				s.click(menu);	
//			else if(s.exists(MAC_IMG) !=null)
//			s.click(MAC_IMG);
			
			if (s.exists(logOut, 5) !=null)
				s.doubleClick(logOut);	
//			else if(s.exists(MAC_IMG) !=null)
//			s.click(MAC_IMG);
			
			s.exists(loginImage, 15); //verifies the user is taken back to the login page
		}
		
		public void IncorrectLogin()throws Exception{
			
			System.out.println("spotify opening");	
			s.wait(loginImage, 10); //Waits a max 10 secs to verify that you land on the login page via image
			s.find(password);
			s.type(password, incorrectPassword);
			s.type(Key.TAB, KeyModifier.SHIFT);
			s.type(Key.BACKSPACE);
			s.type(correctUsername);
			s.click("login.png");
		
			if(s.exists(loginError, 15) != null) {
				System.out.println("Login error message appears: password is invalid");
			}else {
				throw new Exception("Login error message should appear: password is invalid");
			}
		}
			
		public void validLogin() throws Exception {

			s.wait(password, 20);
			s.type(password, correctPassword);
			s.type(Key.TAB, KeyModifier.SHIFT);
			s.type(Key.BACKSPACE);
			s.type(correctUsername);
			s.click("login.png");
			s.wait(userLoginSuccessful, 15); //this statement will throw an exception if the correct user did not login
		}
		
		public void searchArtist() throws Exception {
			
			s.exists(search, 10);
			s.type(search, "M83");
			s.exists(m83, 15);
			Settings.MoveMouseDelay = 6;
			s.click(m83);
			s.exists(m83scroll, 15); //verifies the search directed to the artist's page
			s.click(m83scroll);
		}
		
		public void searchAndPlaySong() throws Exception {
			s.exists(search, 20);
			s.type(search, "don't you evah" + Key.ENTER);
			
			Match r = s.exists(songTitle, 15);
			s.doubleClick(r, 3);
			
			s.exists(playing, 5); //this will verify that double clicking the song title will play the song
			Settings.MoveMouseDelay = 6;
			s.click(playing);
			s.exists(paused);
			
		}
		
}
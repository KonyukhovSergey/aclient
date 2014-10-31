package ru.serjik.gameclient;

import ru.serjik.ui.OneActivity;
import android.os.Bundle;

public class ActivityGame extends OneActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		show(new FragmentMain());
	}
	
	

//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_game);
//		
//		fragmentLogin = new FragmentLogin(fragmentLoginListener);
//		fragmentLogin.show((ViewGroup) findViewById(R.id.layout_root));
//	}
//
//	private FragmentLoginListener fragmentLoginListener = new FragmentLoginListener()
//	{
//		@Override
//		public void onLoginDataDone(String user, String pass)
//		{
//			Log.v("ActivityGame", "user: " + user + " pass: " + pass);
//		}
//
//		@Override
//		public void onLoginCancel()
//		{
//			Log.v("ActivityGame", "cancel");
//			//finish();
//		}
//	};
//
//	@Override
//	public void onBackPressed()
//	{
//		fragmentLogin.onBackPressed();
//		super.onBackPressed();
//	}

}

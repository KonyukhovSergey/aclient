package ru.serjik.gameclient;

import ru.serjik.gameclient.FragmentLogin.FragmentLoginListener;
import android.R.fraction;
import android.app.Activity;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class ActivityGame extends Activity
{
	private FragmentLogin fragmentLogin ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		fragmentLogin = new FragmentLogin(fragmentLoginListener);
		fragmentLogin.show((ViewGroup) findViewById(R.id.layout_root));
	}

	private FragmentLoginListener fragmentLoginListener = new FragmentLoginListener()
	{
		@Override
		public void onLoginDataDone(String user, String pass)
		{
			Log.v("ActivityGame", "user: " + user + " pass: " + pass);
		}

		@Override
		public void onLoginCancel()
		{
			Log.v("ActivityGame", "cancel");
			finish();
		}
	};

	@Override
	public void onBackPressed()
	{
		fragmentLogin.onBackPressed();
		super.onBackPressed();
	}

}

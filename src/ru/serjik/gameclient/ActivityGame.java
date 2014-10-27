package ru.serjik.gameclient;

import ru.serjik.gameclient.FragmentLogin.FragmentLoginListener;
import android.app.Activity;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityGame extends Activity
{
	private FragmentLogin fragmentLogin = new FragmentLogin();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
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
		}
	};

	@Override
	public void onBackPressed()
	{
		fragmentLogin.onBackPressed();
		super.onBackPressed();
	}

	@Override
	protected void onResume()
	{
		fragmentLogin.show(this, fragmentLoginListener);
		super.onResume();
	}
}

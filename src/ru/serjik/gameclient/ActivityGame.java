package ru.serjik.gameclient;

import ru.serjik.ui.OneActivity;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

public class ActivityGame extends OneActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		permitNetwork();

		show(new FragmentMain());
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void permitNetwork()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
		StrictMode.setThreadPolicy(policy);
	}
}

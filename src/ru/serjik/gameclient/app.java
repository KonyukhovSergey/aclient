package ru.serjik.gameclient;

import android.app.Application;

public class app extends Application
{
	public static cfg cfg = null;

	@Override
	public void onCreate()
	{
		super.onCreate();
		cfg = new cfg(getBaseContext(), "snow");
	}
}

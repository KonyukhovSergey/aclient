package ru.serjik.gameclient;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import ru.serjik.gameclient.FragmentLogin.FragmentLoginListener;
import ru.serjik.ui.OneActivity;
import ru.serjik.ui.OneFragment;


public class FragmentMain extends OneFragment implements FragmentLoginListener
{
	@Override
	public void onStart()
	{
		Toast.makeText(view.getContext(), "main onStart", Toast.LENGTH_LONG).show();
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				OneActivity.manager().show(new FragmentLogin(FragmentMain.this));
			}
		});
		
	}

	@Override
	public boolean canBack()
	{
		return true;
	}

	@Override
	protected int latoutResourse()
	{
		return R.layout.activity_game;
	}

	@Override
	public void onLoginDataDone(String user, String pass)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoginCancel()
	{
		// TODO Auto-generated method stub
		
	}
}
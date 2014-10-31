package ru.serjik.gameclient;

import ru.serjik.gameclient.FragmentLogin.FragmentLoginListener;
import ru.serjik.nionet.ClientData;
import ru.serjik.nionet.NioNetClientListener;
import ru.serjik.ui.OneFragment;

public class FragmentMain extends OneFragment implements FragmentLoginListener, NioNetClientListener
{
	@Override
	public void onCreate()
	{

	}

	@Override
	public void onStart()
	{
		new FragmentLogin(FragmentMain.this).show();
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

	}

	@Override
	public void onLoginCancel()
	{
		close();
	}

	@Override
	public void onMessage(ClientData client, String message)
	{

		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnect()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnect()
	{
		// TODO Auto-generated method stub
		
	}
}
package ru.serjik.gameclient;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import ru.serjik.gameclient.Autorizator.AutorizatorListener;
import ru.serjik.gameclient.FragmentLogin.FragmentLoginListener;
import ru.serjik.nionet.ClientData;
import ru.serjik.nionet.NioNetClient;
import ru.serjik.nionet.NioNetClientListener;
import ru.serjik.ui.OneFragment;

public class FragmentMain extends OneFragment implements FragmentLoginListener, NioNetClientListener, Runnable,
		AutorizatorListener
{
	private NioNetClient client;
	private Autorizator autorizator;

	@Override
	public void onClose()
	{
		Log.v(getClass().getSimpleName(), "onDisconnect");
		client.close();
	}

	@Override
	public void onStart()
	{
		client = new NioNetClient("serjik.noip.me", 11001, this);
		view.postDelayed(this, 250);
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
		autorizator.send(user, pass);
	}

	@Override
	public void onLoginCancel()
	{
		close();
	}

	@Override
	public void onMessage(ClientData client, String message)
	{
		if (client.tag == null)
		{
			autorizator = new Autorizator(client, this);
			client.tag = autorizator;
		}

		client.tag.onMessage(client, message);
	}

	@Override
	public void onConnect()
	{
		Log.v(getClass().getSimpleName(), "onConnect");
	}

	@Override
	public void onDisconnect()
	{
		Log.v(getClass().getSimpleName(), "onDisconnect");
		view.removeCallbacks(this);
		close();
	}

	@Override
	public void run()
	{
		if (client.state != NioNetClient.STATE_DISCONNECTED)
		{
			client.tick();
			view.postDelayed(this, 20);
		}
	}

	@Override
	public void onLoginPassRequsted()
	{
		new FragmentLogin(this).show();
	}

	@Override
	public void onCaptchaRequested(String captcha)
	{
		autorizator.send("captcha");
	}

	@Override
	public void onAutorizationError(String reason)
	{
		Toast.makeText(view.getContext(), reason, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onAutorizationSuccess(ClientData client)
	{
		new MovementController(client, (ViewGroup) view);
		new Avatar(client, (TextView) findViewById(R.id.text_info));
	}

}
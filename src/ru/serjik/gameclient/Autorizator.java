package ru.serjik.gameclient;

import ru.serjik.nionet.ClientData;
import ru.serjik.nionet.MessageListener;

public class Autorizator implements MessageListener
{
	private static final String REGME = "regme:";

	private static final String WELCOME = "welcome";

	private ClientData client;

	private int state = 0;
	private String salt;
	private String passh;

	private AutorizatorListener autorizatorListener;

	public Autorizator(ClientData client, AutorizatorListener autorizatorListener)
	{
		this.client = client;
		this.autorizatorListener = autorizatorListener;
		client.tag = this;
	}

	@Override
	public void onMessage(ClientData client, String message)
	{
		switch (state)
		{
		case 0:
			salt = message;
			state = 1;
			autorizatorListener.onLoginPassRequsted();
			break;
		case 1:
			if (message.startsWith(REGME))
			{
				autorizatorListener.onCaptchaRequested(message.substring(REGME.length()));
				state = 2;
			}
			else if (message.equals(WELCOME))
			{
				state = 3;
				autorizatorListener.onAutorizationSuccess(client);
			}
			else
			{
				autorizatorListener.onAutorizationError(message);
				client.close();
				return;
			}
			break;
		case 2:
			if (message.equals(WELCOME))
			{
				state = 3;
				autorizatorListener.onAutorizationSuccess(client);
			}
			else
			{
				autorizatorListener.onAutorizationError(message);
				client.close();
				return;
			}

			break;
		}
	}

	public void send(String login, String password)
	{
		passh = md5.get(password);
		client.send(login + ";" + md5.get(passh + salt));
	}

	public void send(String captcha)
	{
		client.send(passh + ";" + captcha);
	}

	public void cancel()
	{
		client.close();
	}

	public interface AutorizatorListener
	{
		void onLoginPassRequsted();

		void onCaptchaRequested(String captcha);

		void onAutorizationError(String reason);

		void onAutorizationSuccess(ClientData client);
	}
}

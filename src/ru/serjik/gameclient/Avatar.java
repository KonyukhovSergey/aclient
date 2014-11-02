package ru.serjik.gameclient;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;

import ru.serjik.nionet.ClientData;
import ru.serjik.nionet.MessageListener;
import ru.serjik.ui.OneActivity;

public class Avatar implements MessageListener
{
	public List<GameObject> objects = new ArrayList<GameObject>();

	private TextView info;

	public Avatar(ClientData client, TextView info)
	{
		client.tag = this;
		this.info = info;
	}

	@Override
	public void onMessage(ClientData client, String message)
	{
		if (message.startsWith("/add;"))
		{
			add(message.split(";"));
		}
		else if (message.startsWith("/del;"))
		{
			del(message.split(";"));
		}
		else if (message.startsWith("/ctl;"))
		{
			ctl(message.split(";"));
		}
		else
		{
			Toast.makeText(OneActivity.rootViewGroup().getContext(), message, Toast.LENGTH_SHORT).show();
		}
		updateInfo();
	}

	private void ctl(String[] values)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			if (objects.get(i).id().equals(values[1]))
			{
				((Player) objects.get(i)).control(values[2]);
				break;
			}
		}
	}

	private void del(String[] values)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			if (objects.get(i).id().equals(values[1]))
			{
				objects.remove(i);
				break;
			}
		}
	}

	private void add(String[] values)
	{
		if (values[1].equals("player"))
		{
			objects.add(new Player(values));
		}
		else if (values[1].equals("item"))
		{
			objects.add(new GameItem(values));
		}
	}

	public void updateInfo()
	{
		StringBuilder sb = new StringBuilder();

		for (GameObject object : objects)
		{
			sb.append(object.toString() + "\n");
		}

		info.setText(sb.toString());
	}

}

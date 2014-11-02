package ru.serjik.gameclient;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import ru.serjik.nionet.ClientData;
import ru.serjik.ui.OneActivity;

public class MovementController implements OnClickListener
{
	private ClientData client;

	public MovementController(ClientData client, ViewGroup view)
	{
		this.client = client;
		view.findViewById(R.id.button_move_forward).setOnClickListener(this);
		view.findViewById(R.id.button_move_backward).setOnClickListener(this);
		view.findViewById(R.id.button_rotate_left).setOnClickListener(this);
		view.findViewById(R.id.button_rotate_right).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button_move_forward:
			client.send("/ctl;mf");
			break;

		case R.id.button_move_backward:
			client.send("/ctl;mb");
			break;
			
		case R.id.button_rotate_left:
			client.send("/ctl;rl");
			break;
			
		case R.id.button_rotate_right:
			client.send("/ctl;rr");
			break;
		}

	}

}

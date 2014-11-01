package ru.serjik.ui;

import android.util.Log;
import android.view.View;

public abstract class OneFragment
{
	protected View view;

	public void onCreate()
	{
	}

	public void onStart()
	{
	}

	public void onClose()
	{
		Log.v("eerer", "onclode");
	}

	public void show()
	{
		OneActivity.manager().show(this);
	}

	public void close()
	{
		OneActivity.manager().close(this);
	}

	public abstract boolean canBack();

	protected abstract int latoutResourse();

	public View view()
	{
		if (view == null)
		{
			view = OneActivity.manager().getLayoutInflater()
					.inflate(latoutResourse(), OneActivity.rootViewGroup(), false);
		}
		return view;
	}

	public View findViewById(int id)
	{
		return view.findViewById(id);
	}
}

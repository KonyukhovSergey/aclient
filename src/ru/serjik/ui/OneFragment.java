package ru.serjik.ui;

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
	}

	public void show()
	{
		OneActivity.manager().show(this);
	}

	public void close()
	{
		onClose();
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

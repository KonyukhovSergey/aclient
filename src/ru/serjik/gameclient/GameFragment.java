package ru.serjik.gameclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class GameFragment
{
	private View view;

	protected void onCreate()
	{
	}

	protected void onClose()
	{
	}

	protected abstract int getLayoutResouce();

	public void show(ViewGroup root)
	{
		if (view == null)
		{
			LayoutInflater inflater = (LayoutInflater) root.getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(getLayoutResouce(), root, false);

			onCreate();
		}
		root.addView(view);
	}

	public void hide()
	{
		if (view.getParent() != null)
		{
			((ViewGroup) view.getParent()).removeView(view);
			onClose();
		}
	}

	public void onBackPressed()
	{
		hide();
	}

	public View findViewById(int id)
	{
		return view.findViewById(id);
	}
}

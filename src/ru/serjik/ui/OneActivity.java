package ru.serjik.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class OneActivity extends Activity
{
	private static OneActivity oneActivity;
	private static RelativeLayout rootViewGroup;

	private List<OneFragment> fragments = new ArrayList<OneFragment>();

	public void show(OneFragment fragment)
	{
		fragment.onCreate();
		fragments.add(fragment);
		rootViewGroup.addView(fragment.view());
		fragment.onStart();
	}

	public void close(OneFragment fragment)
	{
		fragments.remove(fragment);
		rootViewGroup.removeView(fragment.view());
		if (fragments.size() == 0)
		{
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		oneActivity = this;
		rootViewGroup = new RelativeLayout(this);
		rootViewGroup.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setContentView(rootViewGroup);
	}

	@Override
	public void onBackPressed()
	{
		if (fragments.size() > 0)
		{
			if (fragments.get(fragments.size() - 1).canBack())
			{
				close(fragments.get(fragments.size() - 1));
				return;
			}
		}
		super.onBackPressed();
	}

	public static OneActivity manager()
	{
		return oneActivity;
	}

	public static ViewGroup rootViewGroup()
	{
		return rootViewGroup;
	}
}

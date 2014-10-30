package ru.serjik.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class OneActivity extends Activity
{
	private static OneActivity oneActivity;
	private static RelativeLayout rootViewGroup;
	private List<OneFragment> fragments = new ArrayList<OneFragment>();
	
	public void add(OneFragment fragment){
		fragments.add(fragment);
	}
	
	public void remove(OneFragment fragment){
		
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
			if (fragments.peek().onBack())
			{
				return;
			}
		}
		super.onBackPressed();
	}
}

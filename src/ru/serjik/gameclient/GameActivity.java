package ru.serjik.gameclient;

import java.util.Stack;

import android.app.Activity;

public class GameActivity extends Activity
{
	private Stack<GameFragment> fragments = new Stack<GameFragment>();
	
	protected void onGameFragmentResult(GameFragment gameFragment, int result)
	{
		
	}

}

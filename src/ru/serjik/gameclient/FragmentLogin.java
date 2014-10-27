package ru.serjik.gameclient;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FragmentLogin extends Fragment
{
	private static final String REMEMBER_ME = "remember_me";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private View view;

	private EditText editLogin;
	private EditText editPassword;
	private CheckBox checkBoxRememberMe;
	private FragmentLoginListener fragmentLoginListener;

	public void setFragmentLoginListener(FragmentLoginListener fragmentLoginListener)
	{
		this.fragmentLoginListener = fragmentLoginListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (view == null)
		{
			view = inflater.inflate(R.layout.fragment_login, null);

			editLogin = (EditText) view.findViewById(R.id.edit_login);
			editPassword = (EditText) view.findViewById(R.id.edit_password);
			editPassword.setOnEditorActionListener(onEditorActionListener);
			checkBoxRememberMe = (CheckBox) view.findViewById(R.id.check_box_remember_me);
		}

		return view;
	}

	private OnEditorActionListener onEditorActionListener = new OnEditorActionListener()
	{
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
		{
			if (v == editPassword)
			{
				if (actionId == EditorInfo.IME_ACTION_DONE)
				{
					fragmentLoginListener.onLoginDataDone(editLogin.getText().toString(), editPassword.getText()
							.toString());

					getFragmentManager().popBackStack();
				}
			}
			return false;
		}
	};

	@Override
	public void onResume()
	{
		Log.v("fragment login", "onResume");

		editLogin.setText(app.cfg.get(LOGIN));
		editPassword.setText(app.cfg.get(PASSWORD));
		checkBoxRememberMe.setChecked(app.cfg.get(REMEMBER_ME).equals("true") ? true : false);

		super.onResume();
	}

	@Override
	public void onPause()
	{
		Log.v("fragment login", "onPause");

		app.cfg.set(REMEMBER_ME, checkBoxRememberMe.isChecked() ? "true" : "false");

		if (checkBoxRememberMe.isChecked())
		{
			app.cfg.set(LOGIN, editLogin.getText().toString());
			app.cfg.set(PASSWORD, editPassword.getText().toString());
		}
		else
		{
			app.cfg.set(LOGIN, "");
			app.cfg.set(PASSWORD, "");
		}

		super.onPause();
	}

	public void onBackPressed()
	{
		Log.v("fragment login", "onBackPressed");
		fragmentLoginListener.onLoginCancel();
	}

	public interface FragmentLoginListener
	{
		void onLoginDataDone(String user, String pass);

		void onLoginCancel();
	}

	public void show(Activity activity, FragmentLoginListener fragmentLoginListener)
	{
		this.fragmentLoginListener = fragmentLoginListener;

		FragmentTransaction fTrans = activity.getFragmentManager().beginTransaction();
		fTrans.add(R.id.fragment_login, this);
		fTrans.addToBackStack("login");

		fTrans.commit();
	}

}

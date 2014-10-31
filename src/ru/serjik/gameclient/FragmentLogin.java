package ru.serjik.gameclient;

import ru.serjik.ui.OneFragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FragmentLogin extends OneFragment implements OnEditorActionListener, OnClickListener
{
	private static final String REMEMBER_ME = "remember_me";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";

	private EditText editLogin;
	private EditText editPassword;
	private CheckBox checkBoxRememberMe;
	private FragmentLoginListener fragmentLoginListener;

	public FragmentLogin(FragmentLoginListener fragmentLoginListener)
	{
		this.fragmentLoginListener = fragmentLoginListener;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	{
		if (v == editPassword)
		{
			if (actionId == EditorInfo.IME_ACTION_DONE)
			{
				login();
			}
		}
		return false;
	}

	@Override
	public void onStart()
	{
		editLogin = (EditText) findViewById(R.id.edit_login);
		editPassword = (EditText) findViewById(R.id.edit_password);
		editPassword.setOnEditorActionListener(this);
		checkBoxRememberMe = (CheckBox) findViewById(R.id.check_box_remember_me);

		findViewById(R.id.button_cancel).setOnClickListener(this);
		findViewById(R.id.button_login).setOnClickListener(this);

		editLogin.setText(app.cfg.get(LOGIN));
		editPassword.setText(app.cfg.get(PASSWORD));
		checkBoxRememberMe.setChecked(app.cfg.get(REMEMBER_ME).equals("true") ? true : false);
	}

	@Override
	public void onClose()
	{
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
	}

	public interface FragmentLoginListener
	{
		void onLoginDataDone(String user, String pass);

		void onLoginCancel();
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button_cancel:
			cancel();
			break;

		case R.id.button_login:
			login();
			break;
		}
	}

	private void login()
	{
		fragmentLoginListener.onLoginDataDone(editLogin.getText().toString(), editPassword.getText().toString());
		close();
	}

	private void cancel()
	{
		fragmentLoginListener.onLoginCancel();
		close();
	}

	@Override
	public boolean canBack()
	{
		fragmentLoginListener.onLoginCancel();
		return true;
	}

	@Override
	protected int latoutResourse()
	{
		return R.layout.fragment_login;
	}
}

package com.pixplicity.easyprefs.sample;

import com.pixplicity.easyprefs.library.Prefs;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String SAVED_TEXT = "saved_text";
    Button mSaveBT, mCloseBT;
    TextView mSavedText;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSaveBT = (Button) findViewById(R.id.bt_save);
        mCloseBT = (Button) findViewById(R.id.bt_force_close);
        mSavedText = (TextView) findViewById(R.id.tv_saved_text);
        mEditText = (EditText) findViewById(R.id.editText);
        mSaveBT.setOnClickListener(this);
        mCloseBT.setOnClickListener(this);
        // get the saved String from the preference by key, and give a default value
        // if Prefs does not contain the key.
        String s = Prefs.getString(SAVED_TEXT, getString(R.string.not_found));
        updateText(s);
    }

    private void updateText(String s) {
        String text = String.format(getString(R.string.saved_text), s);
        mSavedText.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                String text = mEditText.getText().toString();
                if (text != null && text.length() > 0) {
                    // one liner to save the String.
                    Prefs.putString(SAVED_TEXT, text);
                    updateText(text);
                } else {
                    Toast.makeText(this, "trying to save a text with lenght 0", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_force_close:
                finish();
                break;
            default:
                throw new IllegalArgumentException(
                        "Did you add a new button forget to listen to view ID in the onclick?");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        final String text = mEditText.getText().toString();
        if (text != null && text.length() > 0) {
            outState.putString(SAVED_TEXT, text);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state != null && state.containsKey(SAVED_TEXT)) {
            final String text = state.getString(SAVED_TEXT) + " : from instance state";
            updateText(text);
        }
    }
}
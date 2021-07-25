package com.pixplicity.easyprefs.sample;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pixplicity.easyprefs.library.Prefs;

public class MainActivity extends AppCompatActivity {

    public static final String SAVED_TEXT = "saved_text";
    public static final String SAVED_NUMBER = "saved_number";
    private static final String FROM_INSTANCE_STATE = " : from instance state";

    TextView mSavedText;
    TextView mSavedNumber;
    EditText mTextET;
    EditText mNumberET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSavedText = findViewById(R.id.tv_saved_text);
        mSavedNumber = findViewById(R.id.tv_saved_number);
        mTextET = findViewById(R.id.et_text);
        mNumberET = findViewById(R.id.et_number);

        // get the saved String from the preference by key, and give a default value
        // if Prefs does not contain the key.
        String s = Prefs.getString(SAVED_TEXT, getString(R.string.not_found));
        double d = Prefs.getDouble(SAVED_NUMBER, -1.0);
        updateText(s);
        updateNumber(d, false);

        findViewById(R.id.bt_save_text).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = mTextET.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    // one liner to save the String.
                    Prefs.putString(SAVED_TEXT, text);
                    updateText(text);
                } else {
                    Toast.makeText(MainActivity.this, "trying to save a text with lenght 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.bt_save_number).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double d = Double.parseDouble(mNumberET.getText().toString());
                Prefs.putDouble(SAVED_NUMBER, d);
                updateNumber(d, false);
            }
        });

        findViewById(R.id.bt_force_close).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateText(String s) {
        String text = String.format(getString(R.string.saved_text), s);
        mSavedText.setText(text);
    }

    private void updateNumber(double d, boolean fromInstanceState) {
        String text = String.format(getString(R.string.saved_number), String.valueOf(d), fromInstanceState ? FROM_INSTANCE_STATE : "");
        mSavedNumber.setText(text);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        final String text = mTextET.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            outState.putString(SAVED_TEXT, text);
        }
        double d = Double.parseDouble(mNumberET.getText().toString());
        outState.putDouble(SAVED_NUMBER, d);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle state) {
        super.onRestoreInstanceState(state);
        if (state.containsKey(SAVED_TEXT)) {
            final String text = state.getString(SAVED_TEXT) + FROM_INSTANCE_STATE;
            updateText(text);
        }

        if (state.containsKey(SAVED_NUMBER)) {
            double d = state.getDouble(SAVED_NUMBER);
            updateNumber(d, true);
        }
    }

}

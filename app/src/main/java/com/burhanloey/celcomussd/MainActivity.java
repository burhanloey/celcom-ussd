package com.burhanloey.celcomussd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.ACTION_CALL;
import static android.text.InputType.TYPE_CLASS_NUMBER;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        findViewById(R.id.button_check_balance)
                .setOnClickListener(view -> call("124"));

        findViewById(R.id.button_buy)
                .setOnClickListener(view -> call("118"));

        findViewById(R.id.button_reload)
                .setOnClickListener(view -> promptPINCode());
    }

    private void promptPINCode() {
        EditText input = new EditText(this);
        input.setInputType(TYPE_CLASS_NUMBER);
        input.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(16) });

        new AlertDialog.Builder(this)
                .setTitle(R.string.enter_pin_code)
                .setView(input)
                .setPositiveButton(R.string.reload, (dialog, id) -> call("122*" + input.getText()))
                .setNegativeButton(R.string.cancel, (dialog, id) -> dialog.cancel())
                .show();
    }

    private void call(String code) {
        Intent intent = new Intent(ACTION_CALL, ussd(code));
        startActivity(intent);
    }

    private Uri ussd(String code) {
        return Uri.parse("tel:*" + code + Uri.encode("#"));
    }
}

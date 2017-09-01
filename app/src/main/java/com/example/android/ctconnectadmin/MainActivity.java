package com.example.android.ctconnectadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_PHOTO_PICKER = 2;
    private EditText nameEditText;
    private Button mSendButton;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("names");
        nameEditText = (EditText) findViewById(R.id.headEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataItem items = new DataItem(nameEditText.getText().toString());
                mDatabaseReference.push().setValue(items);
                nameEditText.setText("");
                Toast.makeText(MainActivity.this, "Information saved...",Toast.LENGTH_SHORT).show();
                nameEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                nameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter((DEFAULT_MSG_LENGTH_LIMIT))});

            }
        });

    }
}

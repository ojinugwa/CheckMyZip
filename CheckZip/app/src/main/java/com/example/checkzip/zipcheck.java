
package com.example.checkzip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class zipcheck extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button logout;

    public static TextView age;
    private EditText zipcodeText;
    Button fetch;

    String zipcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipcheck);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.signoutBtn);


        age = (TextView)findViewById(R.id.testAge);
        zipcodeText= (EditText)findViewById(R.id.zipcodeTxt);
        fetch = (Button)findViewById(R.id.fetchdataBtn);
        zipcode = zipcodeText.getText().toString().trim();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(zipcheck.this, MainActivity.class));
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipcode = zipcodeText.getText().toString().trim();
                fetchData process = new fetchData();
                process.setZipcode(zipcode);
                process.execute();
            }
        });
    }
}

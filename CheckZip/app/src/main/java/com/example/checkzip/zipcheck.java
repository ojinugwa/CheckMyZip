
package com.example.checkzip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class zipcheck extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipcheck);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.signoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(zipcheck.this, MainActivity.class));
            }
        });

    }
}

package cevichito.omarcode.com.cevichito;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cevichito.omarcode.com.cevichito.preferences.Session;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btnSignUp, _btnSignIn;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();

        // Botones
        _btnSignUp.setOnClickListener(this);
        _btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                //Toast.makeText(this, "Iniciar Sesión", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(this, Registro.class));
                break;
        }
    }

    private void setUp() {
         session = Session.get(this);
        _btnSignIn = (Button)findViewById(R.id.btnSignIn);
        _btnSignUp = (Button)findViewById(R.id.btnSignUp);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(session.isLoggedIn()) {
            Intent homeIntent = new Intent(MainActivity.this, Home.class);
            //Common.currentUser = user;
            startActivity(homeIntent);
            finish();
        }
    }
}

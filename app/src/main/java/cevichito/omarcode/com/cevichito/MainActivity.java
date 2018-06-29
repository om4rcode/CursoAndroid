package cevichito.omarcode.com.cevichito;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btnSignUp, _btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();

        // Buttons
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
        _btnSignIn = (Button)findViewById(R.id.btnSignIn);
        _btnSignUp = (Button)findViewById(R.id.btnSignUp);
    }
}

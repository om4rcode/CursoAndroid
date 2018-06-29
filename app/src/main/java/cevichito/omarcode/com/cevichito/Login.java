package cevichito.omarcode.com.cevichito;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cevichito.omarcode.com.cevichito.Common.Common;
import cevichito.omarcode.com.cevichito.Modelo.User;
import cevichito.omarcode.com.cevichito.preferences.Session;

public class Login extends AppCompatActivity {

    Dialog dialog;
    EditText _etNumero, _etPassword;
    String strNumero, strPassword;
    Button _btnLogin;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUp();

        // Iniciamos Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strNumero = _etNumero.getText().toString();
                strPassword = _etPassword.getText().toString();

                _etPassword.setError(null);
                _etNumero.setError(null);

                if (TextUtils.isEmpty(strNumero)) {
                    _etNumero.setError("Ingrese número !");
                    _etNumero.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(strPassword)) {
                    _etPassword.setError("Ingrese password !");
                    _etPassword.requestFocus();
                    return;
                }

                if(_etNumero.length() < 9) {
                    _etNumero.setError("Ingrese su número completo ");
                    _etNumero.requestFocus();
                    return;
                }

                if (_etPassword.length() < 6) {
                    _etPassword.setError("Mínimo 6 caracteres por favor !");
                    _etPassword.requestFocus();
                    return;
                }

                openDialog();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Verificamos si el usuario existe en la db.
                        if (dataSnapshot.child(strNumero).exists()) {
                            //obtenemos informacion del usuario.
                            hideDialog();
                            User user = dataSnapshot.child(strNumero).getValue(User.class);

                            if (user != null && user.getPassword().equals(strPassword)) {
                                //Toast.makeText(Login.this, "Login con éxito !!!", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(Login.this, Home.class);
                                //Common.currentUser = user;
                                session.saveUser(user);
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Contraseña incorrecta !!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            hideDialog();
                            Toast.makeText(Login.this, "No existe el usuario en la BD", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

    }

    private void setUp() {
        session = Session.get(getApplicationContext());
        _btnLogin = (Button) findViewById(R.id.btnLogin);
        _etNumero = (EditText) findViewById(R.id.etNumero);
        _etPassword = (EditText) findViewById(R.id.etPassword);

    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.progress_dialog_layout, null);
        builder.setView(view);
        //builder.setCancelable(false);
        dialog = builder.create();
    }

    private void openDialog() {
        createDialog();
        dialog.show();
    }

    private void hideDialog() {
        dialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(session.isLoggedIn()) {
            Intent homeIntent = new Intent(Login.this, Home.class);
            //Common.currentUser = user;
            startActivity(homeIntent);
            finish();
        }
    }

}


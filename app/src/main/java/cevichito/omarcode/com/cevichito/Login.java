package cevichito.omarcode.com.cevichito;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity {

    Dialog dialog;
    EditText _etNumero, _etPassword;
    String strNumero, strPassword;
    Button _btnLogin;

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

                if(!TextUtils.isEmpty(strNumero) && !TextUtils.isEmpty(strPassword)) {
                    openDialog();
                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Verificamos si el usuario no existe en la db.
                            if(dataSnapshot.child(strNumero).exists()) {
                                //obtenemos informacion del usuario.
                                hideDialog();
                                User user = dataSnapshot.child(strNumero).getValue(User.class);
                                if (user.getPassword().equals(strPassword)) {
                                    //Toast.makeText(Login.this, "Login con éxito !!!", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(Login.this, Home.class);
                                    Common.currentUser = user;
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

                if(TextUtils.isEmpty(strNumero)) {
                    _etNumero.setError("Ingrese número");
                }
                if(TextUtils.isEmpty(strPassword)) {
                    _etPassword.setError("Ingrese password");
                }

            }
        });

    }

    private void setUp() {
        _btnLogin = (Button)findViewById(R.id.btnLogin);
        _etNumero = (EditText) findViewById(R.id.etNumero);
        _etPassword = (EditText) findViewById(R.id.etPassword);
    }

    private void createDialog(){
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

}

package cevichito.omarcode.com.cevichito;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cevichito.omarcode.com.cevichito.Modelo.User;

public class Registro extends AppCompatActivity {

    Dialog dialog;
    EditText _etNumero, _etName, _etPassword;
    String strNumero, strName, strPassword;
    Button _btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setUp();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        _btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strNumero = _etNumero.getText().toString();
                strName = _etName.getText().toString();
                strPassword = _etPassword.getText().toString();
                openDialog();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(strNumero).exists()) {
                            hideDialog();
                            Toast.makeText(Registro.this, "Este número ya existe!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            hideDialog();
                            User user = new User(strName, strPassword);
                            table_user.child(strNumero).setValue(user);
                            Toast.makeText(Registro.this, "Usuario registrado!!!", Toast.LENGTH_SHORT).show();
                            finish();
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
        _etNumero = findViewById(R.id.etNumero);
        _etPassword = findViewById(R.id.etPassword);
        _etName = findViewById(R.id.etName);
        _btnRegistrar = findViewById(R.id.btnRegistrar);
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

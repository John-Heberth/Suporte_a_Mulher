package com.john.tcc.aplicativotcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   private Button bt_login;
   private  EditText et_email, et_senha;
   private TextView txt_cadastro;
   DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        et_email = (EditText) findViewById(R.id.etEmail);
        et_senha = (EditText) findViewById(R.id.etSenha);
        bt_login = (Button) findViewById(R.id.btnlogin);
        txt_cadastro = (TextView) findViewById(R.id.tvCadastro);

        txt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = et_email.getText().toString();
                String senha = et_senha.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(MainActivity.this, "Email não inserido", Toast.LENGTH_SHORT).show();
                    et_email.requestFocus();
                } else if (senha.equals("")) {
                    Toast.makeText(MainActivity.this, "Senha não inserida", Toast.LENGTH_SHORT).show();
                    et_senha.requestFocus();
                } else {
                    //tudo ok!
                    String res = db.ValidarLogin(email, senha);

                    if (res.equals("OK")) {
                        Toast.makeText(MainActivity.this, "Login Confirmado!", Toast.LENGTH_SHORT).show();
                        (new Handler()).postDelayed(new Runnable() {

                            public void run() {
                                startActivity(new Intent(MainActivity.this.getBaseContext(), InicialActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(MainActivity.this, "Login ou Senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
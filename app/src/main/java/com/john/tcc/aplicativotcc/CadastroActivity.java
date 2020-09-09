package com.john.tcc.aplicativotcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroActivity extends AppCompatActivity {

    private Button Cadastrar;
    private DBHelper db;
    private EditText EmailCad;
    private  EditText MensagemCad;
    private  EditText NomeCad;
    private EditText DataCad;
    private EditText TelefoneCad;
    private  EditText SenhaCad1;
    private  EditText SenhaCad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        db = new DBHelper((Context)this);


        EmailCad = (EditText)findViewById(R.id.et_reg_email);
        SenhaCad1 = (EditText)findViewById(R.id.et_reg_senha1);
        SenhaCad2 = (EditText)findViewById(R.id.et_reg_senha2);
        NomeCad = (EditText)findViewById(R.id.et_reg_nome);
        TelefoneCad = (EditText)findViewById(R.id.et_reg_telefone);
        DataCad = (EditText)findViewById(R.id.et_reg_data);
        MensagemCad = (EditText)findViewById(R.id.et_reg_mensagem);
        Cadastrar = (Button) findViewById(R.id.bt_cadastrar);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(TelefoneCad, smf);
        TelefoneCad.addTextChangedListener(mtw);

        SimpleMaskFormatter smd = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtd = new MaskTextWatcher(DataCad, smd);
        DataCad.addTextChangedListener(mtd);


        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = EmailCad.getText().toString();
                String Senha1 = SenhaCad1.getText().toString();
                String Senha2 = SenhaCad2.getText().toString();
                String Nome = NomeCad.getText().toString();
                String Telefone = TelefoneCad.getText().toString();
                String Data = DataCad.getText().toString();
                String Mensagem = MensagemCad.getText().toString();




                if (Nome.equals("")) {
                    Toast.makeText((Context) CadastroActivity.this, "Nome não inserido", Toast.LENGTH_SHORT).show();
                    NomeCad.requestFocus();

                } else if (Telefone.equals("")) {
                    Toast.makeText((Context)  CadastroActivity.this, "Telefone não inserido", Toast.LENGTH_SHORT).show();
                    TelefoneCad.requestFocus();

                } else if (Data.equals("")) {
                    Toast.makeText((Context)  CadastroActivity.this, "Data não inserida", Toast.LENGTH_SHORT).show();
                    DataCad.requestFocus();

                } else if (Email.equals("")) {
                    Toast.makeText((Context)  CadastroActivity.this, "Email não inserido", Toast.LENGTH_SHORT).show();
                    EmailCad.requestFocus();

                } else if (Senha1.equals("") || Senha2.equals("")) {
                    Toast.makeText((Context)  CadastroActivity.this, "Senhas não inserridas", Toast.LENGTH_SHORT).show();
                    SenhaCad1.requestFocus();

                } else if (!Senha1.equals(Senha2)) {
                    Toast.makeText((Context)  CadastroActivity.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                    SenhaCad2.requestFocus();

                } else if (Mensagem.equals("")) {
                    Toast.makeText((Context)  CadastroActivity.this, "Escreva uma mensagem", Toast.LENGTH_SHORT).show();
                    MensagemCad.requestFocus();

                } else {
                    long res =db.CriarUtilizador(Email,Senha1,Nome,Telefone,Data,Mensagem);
                    if (res>0) {
                        Toast.makeText( CadastroActivity.this, "Cadastro Concluído!", Toast.LENGTH_SHORT).show();
                        (new Handler()).postDelayed(new Runnable() {

                            public void run() {
                                CadastroActivity.this.startActivity(new Intent( CadastroActivity.this.getBaseContext(), MainActivity.class));
                                CadastroActivity.this.finish();
                            }
                        }, 1000);
                    }else {
                        Toast.makeText( CadastroActivity.this, "Cadastro Existente!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
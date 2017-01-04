package com.igormelo.teste;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    TextView textView;
    Button button;
    AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica();
            }
        });

    }
    private void verifica(){
        if(editText.getText().toString().equals("igor") && editText2.getText().toString().equals("dani")){
            Toast.makeText(this, "Login correto", Toast.LENGTH_SHORT).show();
            button.setEnabled(false);
            editText.setEnabled(false);
            editText2.setEnabled(false);
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);

        } else if (editText.getText().toString().equals("") || editText2.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("login/senha em branco");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta = builder.create();
            alerta.show();
        } else {
            editText.setText("");
            editText2.setText("");
            Toast.makeText(this, "Login/Senha errados", Toast.LENGTH_SHORT).show();
        }
    }
}

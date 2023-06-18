package com.example.projeto002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaginaCliente extends AppCompatActivity {


    //declara a existência dos editText
    private EditText nomeCliente;
    private EditText cpfCliente;
    private EditText emailCliente;

    private Button botaoVoltar;
    private ClienteDAO daocliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_cliente);

        //vincula os editText da xml com os criados nessa classe, isso puxa os valores pra cá
        nomeCliente = findViewById(R.id.editNomeCliente);
        cpfCliente = findViewById(R.id.editCPFCliente);
        emailCliente = findViewById(R.id.editEmailCliente);
        botaoVoltar = findViewById(R.id.buttonVoltarDoCliente);
        daocliente = new ClienteDAO(this); //instancio o daocliente criado lá em cima

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaCliente.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void salvarCliente (View view) {  //esse salva está no "onClick" do botão "Salvar" da PaginaCliente
        Cliente a = new Cliente(); //cria um objeto "a" que é um Cliente, vinculado à classe java Cliente, onde estão as propriedades
        a.setNome(nomeCliente.getText().toString()); //aqui estão ligadas as propriedades e aos Getter & Setters
        a.setCpf(cpfCliente.getText().toString());
        a.setEmail(emailCliente.getText().toString());
        long id = daocliente.inserir(a); //aqui, eu salvo o cliente recém criado, e já atribui um id (automático e autoincrement)

        Toast.makeText(this, "Cliente salvo. "+id, Toast.LENGTH_SHORT).show(); //só um toastzinho pra aparecer e dar o "ok" quando salva

        nomeCliente.setText("");  //depois de salvas as informações, limpa o formulário
        cpfCliente.setText("");
        emailCliente.setText("");
    }

}
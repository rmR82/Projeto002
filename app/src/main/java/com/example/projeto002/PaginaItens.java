package com.example.projeto002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PaginaItens extends AppCompatActivity {
    //declara a existência dos editText
    private EditText nomeItens;
    private EditText quantidadeItens;
    private EditText valorUnitario;
    private ItensDAO daoitens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_itens);

        //vincula os editText da xml com os criados nessa classe, isso puxa os valores pra cá
        nomeItens = findViewById(R.id.editNomeItens);
        quantidadeItens = findViewById(R.id.editValorUnitario);
        valorUnitario = findViewById(R.id.editQuantidadeItens);
        daoitens = new ItensDAO(this); //instancio o daoitens criado lá em cima
    }
    public void salvarItens (View view) {  //esse salva está no "onClick" do botão "Salvar" da PaginaItens
        Itens b = new Itens(); //cria um objeto "b" que é um Itens, vinculado à classe java Itens, onde estão as propriedades
        b.setNome(nomeItens.getText().toString()); //aqui estão ligadas as propriedades e aos Getter & Setters
        b.setValor(valorUnitario.getText().toString());
        b.setQuantidade(quantidadeItens.getText().toString());
        long id = daoitens.inserir(b); //aqui, eu salvo o item recém criado, e já atribui um id (automático e autoincrement)

        Toast.makeText(this, "Item salvo. "+id, Toast.LENGTH_SHORT); //só um toastzinho pra aparecer e dar o "ok" quando salva
    }

    //trabalhando "valorunitário" e "quantidade", devem virar variáveis do tipo float, pra eu ter um "valorfinal", também do tipo float
    // seria valorFinal = valorUnitario*quantidade
    /*
    public class Conta{
        public static void main(String args[]){
         valor1.parse(editValorUnitario.getText().toString());
         quantidade.parse(editQuantidadeItens.getText().toString());
         valor2 = valor1*quantidade;

        }
    }
*/

}
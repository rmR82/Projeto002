package com.example.projeto002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaginaItens extends AppCompatActivity {
    //declara a existência dos editText
    private EditText nomeItens;
    private EditText quantidadeItens;
    private EditText valorUnitario;
    private Button botaoVoltar;
    private ItensDAO daoitens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_itens);

        //vincula os editText da xml com os criados nessa classe, isso puxa os valores pra cá
        nomeItens = findViewById(R.id.editNomeItens);
        quantidadeItens = findViewById(R.id.editValorUnitario);
        valorUnitario = findViewById(R.id.editQuantidadeItens);
        botaoVoltar = findViewById(R.id.buttonVoltarDoItens);
        daoitens = new ItensDAO(this); //instancio o daoitens criado lá em cima

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaItens.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void salvarItens (View view) {  //esse salva está no "onClick" do botão "Salvar" da PaginaItens
        //primeira parte: trabalhando "valorunitário" e "quantidade", devem virar variáveis do tipo double, pra eu ter um "valorfinal", também do tipo double
        //seria valorFinal = valorUnitario*quantidade

        double v1, v2, v3; //cria variáveis do tipo double

        v1=Double.valueOf(valorUnitario.getText().toString()); //as variáveis recebem os valores digitados nas edit
        v2=Double.valueOf(quantidadeItens.getText().toString());


        String valorFinal; //cria a variável texto pra ser salva no BD depois

        String sv1 = valorUnitario.getText().toString(); //cria variável pra recebr o valor das edit
        String sv2 = quantidadeItens.getText().toString();

        if (sv1.isEmpty() || sv2.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
        }
        else {
            v3 = v1 * v2;  //faz a conta
            valorFinal = String.valueOf(v3); //pega o v3, que é double, e transforma em String, pra aparecer na PaginaCarrinho


            Itens b = new Itens(); //cria um objeto "b" que é um Itens, vinculado à classe java Itens, onde estão as propriedades
            b.setNome(nomeItens.getText().toString()); //aqui estão ligadas as propriedades e aos Getter & Setters
            b.setValor(valorUnitario.getText().toString());
            b.setQuantidade(quantidadeItens.getText().toString());
            b.setValorFinal(valorFinal); //pra pegar o valorFinal calculado acima
            long id = daoitens.inserir(b); //aqui, eu salvo o item recém criado, e já atribui um id (automático e autoincrement)

            Toast.makeText(this, "Item salvo. " + id, Toast.LENGTH_SHORT).show(); //só um toastzinho pra aparecer e dar o "ok" quando salva

            nomeItens.setText(""); //depois de salvas as informações, limpa o formulário
            valorUnitario.setText("");
            quantidadeItens.setText("");
        }
    }

}
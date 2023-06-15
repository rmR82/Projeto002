package com.example.projeto002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PaginaCarrinho extends AppCompatActivity {
    private ListView listacliente, listaitens;
    private ClienteDAO daocliente;
    private ItensDAO daoitens;
    private List<Cliente> listadocliente;
    private List<Itens> listadositens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_carrinho);

        listaitens = findViewById(R.id.listaResultadoItens); //vincula a lista com a listview da página xml
        listacliente = findViewById(R.id.listaResultadoCliente); //eses 2 são objetos
        daocliente = new ClienteDAO(this);
        daoitens = new ItensDAO(this);
        listadocliente = daocliente.obterCliente();  //puxa esse método da classe ClienteDAO
        listadositens = daoitens.obterItens();     //puxa esse método da classe ItensDAO
        //preciso de um adaptador pra colocar os "listadocliente" e "listadositens" dentro dos objetos genéricos "listacliente" e "listaitens" respectivamente
        ArrayAdapter<Cliente> adaptador01 = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, listadocliente); //pede context, layout e a lista usada
        ArrayAdapter<Itens> adaptador02 = new ArrayAdapter<Itens>(this, android.R.layout.simple_list_item_1, listadositens);
        listaitens.setAdapter(adaptador02); //criados os adaptadores acima, só colocá-los dentro das listas
        listacliente.setAdapter(adaptador01);

    }


    /*
    pra permanência dos dados ao mudar de tela
    ao voltar pra essa tela, atualiza os dados
    */
    @Override
    public void onResume(){
        super.onResume();
        listadocliente = daocliente.obterCliente();  //busca denovo
        listadositens = daoitens.obterItens();
        listacliente.invalidateViews();  //invalida das listas anteriores
        listaitens.invalidateViews();
    }
}
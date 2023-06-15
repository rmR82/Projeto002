package com.example.projeto002;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexao extends SQLiteOpenHelper {  //puxa o BD do SQLite

    //crio o Banco de Dados, com o nome "banco.db" e número da versão, que é 1
    private static final String name = "banco.db"; //o nome. Esse DB vai ter 2 tabelas, lá embaixo, uma pra Cliente e uma pra Itens
    private static final int version = 1; //a versão


    //ativando o BD
    public Conexao (Context context) {
        super(context, name, null, version);
    }

    @Override
    //dentro do banco BD, crio 2 tabelas, uma pro cliente e uma pros itens


    public void onCreate(SQLiteDatabase db){
    //pro Cliente
        db.execSQL("create table tabelacliente (id integer primary key autoincrement, " +
                "nome varchar (50), cpf varchar (11), email varchar (50))");

    //pros Itens
        db.execSQL("create table tabelaitens (id integer primary key autoincrement," +
                "nomeitem varchar (50), valorUnitario double, quantidade double, valorFinal double)");
    }

    //aqui é a parte da atualização do BD, que não vai ser usado (acho eu)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

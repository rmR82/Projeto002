package com.example.projeto002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItensDAO {   //responsável por acessar o BD, inserir dados
    //DAO é Data Access Object, por isso é o acesso ao BD
    private Conexao conexao01;
    private SQLiteDatabase banco;

    //o construtor
    public ItensDAO(Context context){
        conexao01 = new Conexao(context);
        banco = conexao01.getWritableDatabase(); //inicia o banco de dados
    }

    // método pro itens
    public long inserir (Itens itens){  //aqui, mapeia o BD e coloca valores dentro de cada campo
        //é long pq o método inserir devolve o id, que é um long
        ContentValues values = new ContentValues();
        values.put("nome", itens.getNome());
        values.put("valor", itens.getValor());
        values.put("quantidade", itens.getQuantidade());
        return banco.insert("tabelaitens", null, values); //informo o nome da tabela, que não tem valores vazios e os valores são a variável "values"
    }

    //precisa do método pra consultar, pra poder mostrar na PaginaCarrinho
    public List<Itens> obterItens(){ //retorna os Itens cadastrados
        List<Itens> itens = new ArrayList<>();
        Cursor cursor = banco.query("tabelaitens", new String[] {"id", "nomeItem", "valorUnitario", "quantidade", "valorFinal"}, //ponteiro que acha na tabela
                null, null, null, null, null);    //parâmetros pra busca, deixar tudo nulo, pra listar normalmente
        //fica um select*from
        while(cursor.moveToNext()){ //move pro próximo item, essa lista é diferente da Cliente, aqui terá vários itens listados no fim
            Itens b = new Itens();
            b.setId((cursor.getInt(0))); //aponta pra primeira coluna da tabela, sempre é 0
            b.setNome(cursor.getString(1));
            b.setValor(cursor.getString(2));
            b.setQuantidade(cursor.getString(3));
            b.setValorFinal(cursor.getString(4));
            itens.add(b); //adiciona o item pra lista
        }
        return itens;  //depois de criada a lista, retorna ela pra PaginaCarrinho
    }
}

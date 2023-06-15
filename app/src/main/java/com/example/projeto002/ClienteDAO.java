package com.example.projeto002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {   //responsável por acessar o BD, inserir dados
                            //DAO é Data Access Object, por isso é o acesso ao BD
    private Conexao conexao01;
    private SQLiteDatabase banco;

    //o construtor
    public ClienteDAO(Context context){
        conexao01 = new Conexao(context);
        banco = conexao01.getWritableDatabase(); //inicia o banco de dados
    }

    // método pro cliente
    public long inserir (Cliente cliente){  //aqui, mapeia o BD e coloca valores dentro de cada campo
                                            //é long pq o método inserir devolve o id, que é um long
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("cpf", cliente.getCpf());
        values.put("email", cliente.getEmail());
        return banco.insert("tabelacliente", null, values); //informo o nome da tabela, que não tem valores vazios e os valores são a variável "values"
    }

    //precisa do método pra consultar, pra poder mostrar na PaginaCarrinho
    public List<Cliente> obterCliente(){ //retorna o Cliente cadastrado
        List<Cliente> cliente = new ArrayList<>();
        Cursor cursor = banco.query("tabelacliente", new String[] {"id", "nome", "cpf", "email"}, //ponteiro que acha na tabela
        null, null, null, null, null);    //parâmetros pra busca, deixar tudo nulo, pra listar normalmente
                                                                           //fica um select*from
        while(cursor.moveToNext()){ //move pro próximo item, mas nesse caso, eu só quero 1 Cliente; talvez tenha que arrumar?
            Cliente a = new Cliente();
            a.setId((cursor.getInt(0))); //aponta pra primeira coluna da tabela, sempre é 0
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setEmail(cursor.getString(3));
            cliente.add(a); //adiciona o cliente pra lista
        }
        return cliente;  //depois de criada a lista, que no caso só deve ter 1 cliente, retorna ele, pra aparecer na PaginaCarrinho
    }
}

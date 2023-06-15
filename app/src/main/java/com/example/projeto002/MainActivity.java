package com.example.projeto002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projeto002.PaginaCliente;

public class MainActivity extends AppCompatActivity {

    Button botaoCliente, botaoItens, botaoCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoCliente = findViewById(R.id.button01);
        botaoItens = findViewById(R.id.button02);
        botaoCarrinho = findViewById(R.id.button03);

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaginaCliente.class);
                startActivity(intent);
            }
        });

        botaoItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaginaItens.class);
                startActivity(intent);
            }
        });

        botaoCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaginaCarrinho.class);
                startActivity(intent);
            }
        });

    }
}
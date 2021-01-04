package com.example.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val btCadastrar = findViewById<AppCompatButton>(R.id.btCadastrar)
        val etNome = findViewById<EditText>(R.id.etNome)
        val etSobrenome = findViewById<EditText>(R.id.etSobrenome)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val spGenero = findViewById<Spinner>(R.id.spGenero)

        //Criando uma lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Feminino", "Masculino", "Outros")

        //Criando um adaptador para a lista e pelo Spinner
        val generoAdapter = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_spinner_dropdown_item, //layout do Spinner
            listaGenero   //lista que criamos
        )

        //Linkar o adaptador ao Spinner
        spGenero.adapter = generoAdapter

        //Executando o clique do botão cadastrar
        btCadastrar.setOnClickListener {
            //Capturando os valores dos edit text
            val nome = etNome.text.toString().trim()
            val sobrenome = etSobrenome.text.toString().trim()
            val email = etEmail.text.toString().trim().toLowerCase()
            val senha = etSenha.text.toString().trim()
            val genero = spGenero.selectedItem.toString()

            //Validação dos dados
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]){
                //Apresentando uma mensagem de erro ao usuário
                Toast.makeText(this,"Preencha todos os campos!", Toast.LENGTH_LONG).show()
            } else {
                //Se todos os campos forem preenchidos

                //Criando ou acessando o arquivo Shared Preferences
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE) //no nome não pode colocar maiuscula e nem caracteres

                //Editar o arquivo Shared Preferences
                val editPrefers = sharedPrefs.edit()

                //Preparando os dados a serem salvos no arquivo
                editPrefers.putString("NOME", nome)
                editPrefers.putString("SOBRENOME", sobrenome)
                editPrefers.putString("EMAIL", email)
                editPrefers.putString("SENHA", senha)
                editPrefers.putString("GENERO", genero)

                //Salvando os dados no arquivo Shared Preferences
                editPrefers.apply()

                //Abrindo a Main Activity
                val mIntent = Intent(this, MainActivity::class.java)

                //Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email) //Passar as informações pra outra tela
                startActivity(mIntent)

                //Tirando todas as telas do empilhamento
                finishAffinity()
            }
        }
    }
}
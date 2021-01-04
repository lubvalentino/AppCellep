package com.example.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btEntrar = findViewById<AppCompatButton>(R.id.btEntrar)
        val btCadastrar = findViewById<AppCompatButton>(R.id.btLoginCadastrar)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginSenha = findViewById<EditText>(R.id.etLoginSenha)

        //Executando o clique do botão entrar
        btEntrar.setOnClickListener {

            //Capturar dados digitados pelo usuário
            //.trim - retira todos os espaços, inicial e final
            //.toLowerCase - muda para todas letras minusculas
            val email = etLoginEmail.text.toString().trim().toLowerCase()
            val senha = etLoginSenha.text.toString().trim()

            //Validação dos campos
            if (email.isEmpty()){
                etLoginEmail.error = "Campo obrigatório!"
                //Pedir o foco para esse componente
                etLoginEmail.requestFocus()
            } else if (senha.isEmpty()){
                etLoginSenha.error = "Campo obrigatório!"
                etLoginSenha.requestFocus()
            } else {
                //Acessando o arquivo de Shared Preferences
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //Recuperando dados no arquivo Shared Preferences
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //Verificando o e-mail e senha que o usuário colocou
                if (email == emailPrefs && senha == senhaPrefs){
                    Toast.makeText(this, "Usuário logado com sucesso", Toast.LENGTH_LONG).show()

                    //Abrindo a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)

                    //Passando informações da Intent
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                } else {
                    Toast.makeText(this, "E-mail ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Executando o clique do botão cadastrar
        btCadastrar.setOnClickListener {
         //Abrindo tela de cadastro
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}
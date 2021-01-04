package com.example.estacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //Habilitar a execução de códigos javascript
        val wbvSite = findViewById<WebView>(R.id.wbvCellep)
        wbvSite.settings.javaScriptEnabled = true  //habilitar o java

        //Carregando um endereço Web
        wbvSite.loadUrl("http://br.cellep.com/estacaohack")

        //Definindo o WebView como cliente web padrão
        wbvSite.webViewClient = WebViewClient()
    }
}
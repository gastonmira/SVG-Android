package com.example.gastonmira.svgandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mTextToPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) this.findViewById(R.id.webView);
        //Para ejecutar JS en el webView
        webView.getSettings().setJavaScriptEnabled(true);
        //Para poder hacer zoom en el webView
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        //Para que no muestre controles de zoom
        webView.getSettings().setDisplayZoomControls(false);
        //Instancia para que el webView pueda manejar todas las funciones de Chrome
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        //Agregamos la interfase llamada "Android" entre JS y Java.
        webView.addJavascriptInterface(new InterfaseJavaJS(this), "Android");
        mTextToPass = "Texto enviado desde Java";
        //Cargamos el file que esta en la carpeta assets
        webView.loadUrl("file:///android_asset/index.html");
    }


    public class InterfaseJavaJS {
        Context context;

        InterfaseJavaJS(Context c) {
            context = c;
        }

        /**
         * Show Toast Message
         *
         * @param msg
         */

        @JavascriptInterface
        public void showToast(String msg) {
            Toast.makeText(context, "El id que se clickeo es: " + msg, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public String getStringFromJava() {
            return mTextToPass;
        }

    }


}

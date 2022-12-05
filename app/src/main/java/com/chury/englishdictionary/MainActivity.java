package com.chury.englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText editText;
    InputMethodManager inputManager;

    private static String siteDictionary = "https://dictionary.cambridge.org/dictionary/english/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        editText = findViewById(R.id.editText);

        InputMethodManager inputManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordToFind = editText.getText().toString();
                String urlStr;

                if(wordToFind.equals("") == false){
                    urlStr = siteDictionary + wordToFind;
                    getWebView(urlStr);
//                    editText.setText("");
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sites,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.dicNaver:
                showToast("Naver dictionary");
                siteDictionary = "https://en.dict.naver.com/#/search?query=";
                return true;
            case R.id.dicMacmillan:
                showToast("Macmillan dictionary");
                siteDictionary = "https://www.macmillandictionary.com/dictionary/british/";
                return true;
            case R.id.dicCambridge:
                showToast("Cambridge dictionary");
                siteDictionary = "https://dictionary.cambridge.org/dictionary/english/";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getWebView(String urlStr){
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(urlStr);
    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
}
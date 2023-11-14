package com.example.simplecppandroidwebapp;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebSettings;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplecppandroidwebapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    // Used to load the 'simplecppandroidwebapp' library on application startup.
    static {
        System.loadLibrary("simplecppandroidwebapp");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // Enable JavaScript
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new JsObject(), "Android");

        myWebView.loadUrl("file:///android_asset/index.html");
    }

     class JsObject
     {
        @JavascriptInterface
        public String hellowText()
        {
            return stringFromJNI();
        }
    }

    /**
     * A native method that is implemented by the 'simplecppandroidwebapp' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}

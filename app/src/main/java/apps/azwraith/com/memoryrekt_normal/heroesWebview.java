package apps.azwraith.com.memoryrekt_normal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class heroesWebview extends AppCompatActivity {


    private WebView wbm;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_webview);
        wbm = (WebView) findViewById(R.id.webView);
        wbm.setWebChromeClient(new MyWebViewClient());
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        WebSettings webSettings = wbm.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wbm.loadUrl("http://dota2.gamepedia/Earthshaker");
        progress.setProgress(0);
    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            heroesWebview.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    public void setValue(int progress) {
        this.progress.setProgress(progress);
        if (progress>=100) {
            this.progress.setVisibility(View.GONE);
        }
    }


}

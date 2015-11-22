package apps.azwraith.com.memoryrekt_normal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class heroesWebview extends AppCompatActivity

{

    private WebView wbm;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_webview);
        wbm = (WebView) findViewById(R.id.webView);
        wbm.setWebChromeClient(new MyWebViewClient());
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        WebSettings webSettings = wbm.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String hero_nextrct = intent.getExtras().getString("hero_name");
        String url_edit = "http://wiki.teamliquid.net/dota2/" + hero_nextrct;
        wbm.loadUrl(url_edit);
        progress.setProgress(0);
    }

    private class MyWebViewClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            heroesWebview.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    public void setValue(int progress)
    {

        this.progress.setProgress(progress);

        if (progress>=100)
        {
            this.progress.setVisibility(View.GONE);
        }

    }


}

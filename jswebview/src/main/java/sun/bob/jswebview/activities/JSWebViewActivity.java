package sun.bob.jswebview.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import sun.bob.jswebview.R;
import sun.bob.jswebview.utils.Constants;
import sun.bob.jswebview.utils.UriUtil;
import sun.bob.jswebview.widget.JSWebView;

/**
 * Created by bob.sun on 15/7/28.
 */
public class JSWebViewActivity extends AppCompatActivity {
    JSWebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_jswebviewactvity);
        webView = (JSWebView) findViewById(R.id.id_jswebview);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case Constants.REQUEST_CODE_PICK_IMAGE:
                Uri imageUri = data.getData();
                String imagePath = UriUtil.getPath(this, imageUri);
                Log.e("ImagePath",imagePath);
                break;
            default:
                break;
        }
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
    }
}

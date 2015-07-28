package sun.bob.jswebviewtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;

import sun.bob.jswebview.activities.ImagePreviewActivity;
import sun.bob.jswebview.activities.JSWebViewActivity;
import sun.bob.jswebview.jsinterface.CallBackFunction;
import sun.bob.jswebview.utils.Constants;
import sun.bob.jswebview.utils.UriUtil;
import sun.bob.jswebview.widget.JSWebView;

public class MainActivity extends JSWebViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl("file:///android_asset/index.html");
        addBaseHandler("chooseImage", new CallBackFunction() {
            @Override
            public void run(String... args) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

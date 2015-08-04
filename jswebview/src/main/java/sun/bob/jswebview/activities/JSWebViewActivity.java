package sun.bob.jswebview.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import sun.bob.jswebview.R;
import sun.bob.jswebview.jsinterface.CallBackFunction;
import sun.bob.jswebview.utils.Constants;
import sun.bob.jswebview.utils.UriUtil;
import sun.bob.jswebview.widget.JSWebView;

/**
 * Created by bob.sun on 15/7/28.
 */
public class JSWebViewActivity extends AppCompatActivity {
    JSWebView webView;
    String currentImage;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_jswebviewactvity);
        webView = (JSWebView) findViewById(R.id.id_jswebview);
    }

    private void initBaseFunctions(){
        addBaseHandler("chooseImage", new CallBackFunction() {
            @Override
            public void run(String... args) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
            }
        });
        addBaseHandler("previewImage", new CallBackFunction() {
            @Override
            public void run(String... args) {
                Intent intent = new Intent(JSWebViewActivity.this, ImagePreviewActivity.class);
                intent.putExtra("image_file_path", args[0]);
                startActivity(intent);
            }
        });
        addBaseHandler("closeWindow", new CallBackFunction() {
            @Override
            public void run(String... args) {
//                Handler handler = new Handler();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
                JSWebViewActivity.this.finish();
//                    }
//                });
            }
        });
        addBaseHandler("setWindowTitle", new CallBackFunction() {
            @Override
            public void run(String... args) {
                if (args[0] == null){
                    Toast.makeText(JSWebViewActivity.this, "Title argument is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String title = args[0];
                JSWebViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSWebViewActivity.this.getSupportActionBar().setTitle(title);
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case Constants.REQUEST_CODE_PICK_IMAGE:
                if (data == null)
                    break;
                Uri imageUri = data.getData();
                String imagePath = UriUtil.getPath(this, imageUri);
                Log.e("ImagePath", imagePath);
                //Trigger JavaScript event and send the result back;
                webView.loadUrl("javascript:jswebview.nativeCallBack('onChooseImageDone', '"+imagePath+"')");
                break;
            default:
                break;
        }
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
        initBaseFunctions();
    }

    public void addBaseHandler(String name, CallBackFunction function){
        webView.addBaseHandler(name, function);
    }
}

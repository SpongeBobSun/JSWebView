package sun.bob.jswebview.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

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
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_jswebviewactvity);
        webView = (JSWebView) findViewById(R.id.id_jswebview);
    }

    private void initBaseFunctions(){
        addBaseHandler("chooseImage", new CallBackFunction() {
            @Override
            public void run(String args) {
                Intent intent = new Intent(JSWebViewActivity.this, ImageChooseActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
            }
        });
        addBaseHandler("previewImage", new CallBackFunction() {
            @Override
            public void run(String args) {
                Intent intent = new Intent(JSWebViewActivity.this, ImagePreviewActivity.class);
                try {
                    JSONObject arg = new JSONObject(args);
                    JSONArray lists = arg.getJSONArray("image_list");
                    ArrayList filePathList = new ArrayList();
                    for (int i = 0; i < lists.length(); i++) {
                        filePathList.add(lists.get(i));
                    }
                    intent.putStringArrayListExtra("image_list",filePathList);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        addBaseHandler("showImage", new CallBackFunction() {
            @Override
            public void run(String jsonArgs) {
                Intent intent = new Intent(JSWebViewActivity.this, ImagePreviewActivity.class);
                try {
                    JSONObject arg = new JSONObject(jsonArgs);
                    JSONArray lists = arg.getJSONArray("image_list");
                    ArrayList filePathList = new ArrayList();
                    for (int i = 0; i < lists.length(); i++) {
                        filePathList.add(lists.get(i));
                    }
                    intent.putStringArrayListExtra("image_list",filePathList);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        addBaseHandler("closeWindow", new CallBackFunction() {
            @Override
            public void run(String args) {
                JSWebViewActivity.this.finish();
            }
        });
        addBaseHandler("setWindowTitle", new CallBackFunction() {
            @Override
            public void run(String args) {
                if (args == null){
                    Toast.makeText(JSWebViewActivity.this, "Title argument is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String title = args;
                JSWebViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSWebViewActivity.this.getSupportActionBar().setTitle(title);
                    }
                });
            }
        });

        addBaseHandler("makeToast", new CallBackFunction() {
            @Override
            public void run(String jsonArgs) {
                if (jsonArgs == null){
                    Toast.makeText(JSWebViewActivity.this, "Toast is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonArgs);
                    Toast.makeText(JSWebViewActivity.this,jsonObject.getString("text"),jsonObject.getInt("duration") == Constants.TOAST_DURATION_LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_CANCELED)
            return;
        switch (requestCode){
            case Constants.REQUEST_CODE_PICK_IMAGE:
                if (data == null)
                    break;
                ArrayList images = data.getStringArrayListExtra("images");
                JSONObject arg = new JSONObject();
                JSONArray args = new JSONArray();
                Iterator iterator = images.iterator();
                while(iterator.hasNext()){
                    args.put(iterator.next());
                }
                try {
                    arg.put("image_list",args);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Trigger JavaScript event and send the result back;
                webView.loadUrl("javascript:jswebview.nativeCallBack('onChooseImageDone', '" + arg.toString() + "')");
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

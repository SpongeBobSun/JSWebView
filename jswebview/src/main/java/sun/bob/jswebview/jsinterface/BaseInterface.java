package sun.bob.jswebview.jsinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import java.util.HashMap;

import sun.bob.jswebview.activities.ImagePreviewActivity;
import sun.bob.jswebview.utils.Constants;

/**
 * Created by bob.sun on 15/7/28.
 */
public class BaseInterface {
    private Context context;
    private HashMap<String, CallBackFunction> functions;
    public BaseInterface(Context context){
        this.context = context;
        functions = new HashMap<>();
    }
//    @JavascriptInterface
//    public void chooseImage() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
//        ((Activity) context).startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
//    }
//    @JavascriptInterface
//    public void previewImage(String filePath){
//        Intent intent = new Intent((Activity) context, ImagePreviewActivity.class);
//        intent.putExtra("image_file_path", filePath);
//        ((Activity) context).startActivity(intent);
//    }

    public BaseInterface addHandler(String name, CallBackFunction function){
        if (function == null){
            throw new NullPointerException("Argument function can not be NULL!");
        }
        functions.put(name, function);
        return this;
    }

    public BaseInterface removeHandler(String name){
        functions.remove(name);
        return this;
    }

    public CallBackFunction getHandler(String name){
        return functions.get(name);
    }

    @JavascriptInterface
    public void call(String function, String[] args) {
        functions.get(function).run(args);
    }
}

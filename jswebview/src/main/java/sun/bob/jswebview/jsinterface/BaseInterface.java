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

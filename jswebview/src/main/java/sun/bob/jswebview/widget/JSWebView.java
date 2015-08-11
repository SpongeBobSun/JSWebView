package sun.bob.jswebview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import sun.bob.jswebview.client.BaseChromeClient;
import sun.bob.jswebview.jsinterface.BaseInterface;
import sun.bob.jswebview.jsinterface.CallBackFunction;

/**
 * Created by bob.sun on 15/7/27.
 */
public class JSWebView extends WebView {
    private BaseInterface baseInterface;
    public JSWebView(Context context) {
        super(context);
        init();
    }

    public JSWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebChromeClient(new BaseChromeClient(getContext()));
    }

    public void addJSInterface(Object object, String nameSpace){
        this.addJavascriptInterface(object, nameSpace);
    }

    @Override
    public void loadUrl(String url){
        super.loadUrl(url);
        if (baseInterface == null){
            baseInterface = new BaseInterface(getContext());
            addJavascriptInterface(baseInterface,"Base");
        }
    }

    public void addBaseHandler(String name, CallBackFunction function){
        baseInterface.addHandler(name, function);
    }


}

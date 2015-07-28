package sun.bob.jswebview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import sun.bob.jswebview.jsinterface.ABSBaseInterface;
import sun.bob.jswebview.jsinterface.BaseInterface;

/**
 * Created by bob.sun on 15/7/27.
 */
public class JSWebView extends WebView {
    private boolean baseLoaded = false;
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
    }

    public void addJSInterface(ABSBaseInterface object, String nameSpace){
        this.addJavascriptInterface(object, nameSpace);
    }

    @Override
    public void loadUrl(String url){
        super.loadUrl(url);
        if (!baseLoaded){
            this.addJSInterface(new BaseInterface(getContext()),"Base");
        }
    }


}

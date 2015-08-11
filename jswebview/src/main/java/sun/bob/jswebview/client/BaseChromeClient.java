package sun.bob.jswebview.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import sun.bob.jswebview.utils.JSAlertDialog;
import sun.bob.jswebview.utils.JSConfirmDialog;

/**
 * Created by bob.sun on 15/8/4.
 */
public class BaseChromeClient extends AbsJSChromeClient {
    private Context mContext;
    public BaseChromeClient(Context context){
        this.mContext = context;
    }
    @Override
    public void onProgressChanged(WebView view, int newProgress) {

    }

    @Override
    public void onReceivedTitle(WebView view, String title) {

    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {

    }

    @Override
    public void onCloseWindow(WebView window) {

    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        new JSAlertDialog(mContext, result).setMessage(message).show();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        new JSConfirmDialog(mContext,result).setMessage(message).show();
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return false;
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        return false;
    }
}

package sun.bob.jswebview.client;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by bob.sun on 15/7/27.
 */
public abstract class AbsJSChromeClient extends WebChromeClient{
    public abstract void onProgressChanged(WebView view, int newProgress);
    public abstract void onReceivedTitle(WebView view, String title);
    public abstract void onReceivedIcon(WebView view, Bitmap icon);
    public abstract void onCloseWindow(WebView window);
    public abstract boolean onJsAlert(WebView view, String url, String message, JsResult result);
    public abstract boolean onJsConfirm(WebView view, String url, String message, JsResult result);
    public abstract boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result);
    public abstract boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result);
    public abstract boolean onConsoleMessage(ConsoleMessage consoleMessage);
    public abstract boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams);
}

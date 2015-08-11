package sun.bob.jswebview.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.JsResult;
import android.widget.TextView;

import sun.bob.jswebview.R;

/**
 * Created by bob.sun on 15/8/11.
 */
public class JSConfirmDialog {
    private Context mContext;
    private JsResult jsResult;
    private View view;
    private OnMessageChangeHandler messageHandler;
    private String title;
    private OnChooseButtonListener posititveListener;
    private OnChooseButtonListener negativeListener;
    public JSConfirmDialog(Context context, JsResult jsResult){
        mContext = context;
        this.jsResult = jsResult;
        view = View.inflate(mContext, R.layout.layout_jsalert_dialog,null);
    }
    public JSConfirmDialog setDialogView(View v){
        this.view = v;
        return this;
    }

    public JSConfirmDialog setOnMessageChangeHandler(OnMessageChangeHandler handler){
        this.messageHandler = handler;
        return this;
    }
    public JSConfirmDialog setMessage(final String message){
        if (messageHandler == null) {
            messageHandler = new OnMessageChangeHandler() {
                @Override
                public void changeMessage() {
                    ((TextView) view.findViewById(R.id.id_jsalert_message)).setText(message);
                }
            };
        }
        messageHandler.changeMessage();

        return this;
    }
    public JSConfirmDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public void setPosititveListener(OnChooseButtonListener posititveListener) {
        this.posititveListener = posititveListener;
    }

    public void setNegativeListener(OnChooseButtonListener negativeListener) {
        this.negativeListener = negativeListener;
    }

    public void show(){
        new AlertDialog.Builder(mContext).setView(view).setTitle(title == null ? "提示" : title)
                .setPositiveButton("确定 ", posititveListener == null ? new OnChooseButtonListener() {
                    @Override
                    void onButtonClicked(DialogInterface dialog, int which) {

                    }
                } : posititveListener)
                .setNegativeButton("取消", negativeListener == null ? new OnChooseButtonListener() {
                    @Override
                    void onButtonClicked(DialogInterface dialog, int which) {

                    }
                } : negativeListener).show();
    }

    interface OnMessageChangeHandler {
        void changeMessage();
    }

    abstract class OnChooseButtonListener implements DialogInterface.OnClickListener{
        abstract void onButtonClicked(DialogInterface dialog, int which);
        @Override
        public void onClick(DialogInterface dialog, int which){
            onButtonClicked(dialog, which);
            dialog.dismiss();
            if (which == DialogInterface.BUTTON_POSITIVE){
                jsResult.confirm();
            } else {
                jsResult.cancel();
            }
        }
    }
}

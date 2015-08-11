package sun.bob.jswebview.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.JsResult;
import android.widget.TextView;

import sun.bob.jswebview.R;

/**
 * Created by bob.sun on 15/8/11.
 */
public class JSAlertDialog {
    private Context mContext;
    private JsResult jsResult;
    private View view;
    private OnMessageChangeHandler messageHandler;
    private String title;
    public JSAlertDialog(Context context, JsResult jsResult){
        mContext = context;
        this.jsResult = jsResult;
        view = View.inflate(mContext, R.layout.layout_jsalert_dialog,null);
    }
    public JSAlertDialog setDialogView(View v){
        this.view = v;
        return this;
    }

    public JSAlertDialog setOnMessageChangeHandler(OnMessageChangeHandler handler){
        this.messageHandler = handler;
        return this;
    }
    public JSAlertDialog setMessage(final String message){
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
    public JSAlertDialog setTitle(String title){
        this.title = title;
        return this;
    }
    public void show(){
        new AlertDialog.Builder(mContext).setView(view).setTitle(title == null ? "提示" : title).setPositiveButton("确定 ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                jsResult.confirm();
            }
        }).show();
    }

    interface OnMessageChangeHandler {
        void changeMessage();
    }
}

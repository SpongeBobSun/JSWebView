package sun.bob.jswebview.jsinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import sun.bob.jswebview.activities.ImagePreviewActivity;
import sun.bob.jswebview.utils.Constants;

/**
 * Created by bob.sun on 15/7/28.
 */
public class BaseInterface implements ABSBaseInterface {
    private Context context;
    public BaseInterface(Context context){
        this.context = context;
    }
    @JavascriptInterface
    public void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, Constants.REQUEST_CODE_PICK_IMAGE);
    }
    @JavascriptInterface
    public void previewImage(String filePath){
        Intent intent = new Intent((Activity) context, ImagePreviewActivity.class);
        intent.putExtra("image_file_path",filePath);
        ((Activity) context).startActivity(intent);
    }

}

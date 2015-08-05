package sun.bob.jswebview.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dpl.bobsun.dummypicloader.DummyPicLoader;
import sun.bob.jswebview.R;
import sun.bob.jswebview.utils.DisplayUtil;
import sun.bob.jswebview.utils.ImageProvider;

/**
 * Created by bob.sun on 15/8/5.
 */
public class ImageAdapter extends ArrayAdapter {
    ArrayList images;
    LinearLayout.LayoutParams cellParams;
    public ImageAdapter(Context context, int resource) {
        super(context, resource);
        images = new ImageProvider(context).getAll();
        cellParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cellParams.width = DisplayUtil.getStaticInstance(null).getScreenWidth() / 3;;
        cellParams.height = DisplayUtil.getStaticInstance(null).getScreenWidth() / 3;;
    }

    @Override
    public int getCount(){
        return images.size();
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent){
        View ret = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.layout_image_cell,parent,false);
        ImageView imageView = (ImageView) ret.findViewById(R.id.id_image);
        imageView.setLayoutParams(cellParams);
        DummyPicLoader.getInstance(getContext()).resize(cellParams.width, cellParams.height).loadImageFromFile((String) images.get(index), (ImageView) ret.findViewById(R.id.id_image));
        return ret;
    }

}

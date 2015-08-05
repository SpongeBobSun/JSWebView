package sun.bob.jswebview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import sun.bob.jswebview.R;
import sun.bob.jswebview.adapters.ImageAdapter;
import sun.bob.jswebview.utils.DisplayUtil;

/**
 * Created by bob.sun on 15/8/5.
 */
public class ImageChooseActivity extends AppCompatActivity {
    GridView gridView;
    private ArrayList<String> selectedImages;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayUtil.getStaticInstance(this);
        setContentView(R.layout.activity_imagechoose);
        selectedImages = new ArrayList();
        gridView = (GridView) findViewById(R.id.id_image_gridview);
        gridView.setNumColumns(3);
        gridView.setAdapter(new ImageAdapter(this, R.layout.layout_image_cell));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}

package grimbo.android.demo.slidingmenu;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Utility methods for Views.
 */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void setViewWidths(View view, View[] views) {
        int w = view.getWidth();
        int h = view.getHeight();
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            v.layout((i + 1) * w, 0, (i + 2) * w, h);
            printView("view[" + i + "]", v);
        }
    }

    public static void printView(String msg, View v) {
        System.out.println(msg + "=" + v);
        if (null == v) {
            return;
        }
        System.out.print("[" + v.getLeft());
        System.out.print(", " + v.getTop());
        System.out.print(", w=" + v.getWidth());
        System.out.println(", h=" + v.getHeight() + "]");
        System.out.println("mw=" + v.getMeasuredWidth() + ", mh=" + v.getMeasuredHeight());
        System.out.println("scroll [" + v.getScrollX() + "," + v.getScrollY() + "]");
    }

    public static void initListView(Context context, ListView listView, String prefix, int numItems, int layout) {
        // By using setAdpater method in listview we an add string array in list.
        String[] arr = new String[numItems];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = prefix + (i + 1);
        }
        listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("item[" + position + "]=" + parent.getItemAtPosition(position));
            }
        });
    }
}

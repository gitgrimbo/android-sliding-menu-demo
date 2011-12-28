package grimbo.android.demo.slidingmenu;

import grimbo.android.demo.slidingmenu.MyHorizontalScrollView.SizeCallback;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * This demo uses a custom HorizontalScrollView that ignores touch events, and therefore does NOT allow manual scrolling.
 * 
 * The only scrolling allowed is scrolling in code triggered by the menu button.
 */
public class HorzScrollWithListMenu extends Activity {
    MyHorizontalScrollView scrollView;
    View menu;
    View app;
    ImageView btnSlide;
    boolean menuOut = false;
    Handler handler = new Handler();
    int btnWidth;

    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            System.out.println("onClick " + new Date());

            int menuWidth = menu.getMeasuredWidth();

            // restore the menu after onResume
            menu.setVisibility(View.VISIBLE);

            if (!menuOut) {
                int left = 0;
                scrollView.smoothScrollTo(left, 0);
            } else {
                int left = menuWidth;
                scrollView.smoothScrollTo(left, 0);
            }
            menuOut = !menuOut;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        scrollView = (MyHorizontalScrollView) inflater.inflate(R.layout.horz_scroll_with_list_menu, null);
        setContentView(scrollView);

        menu = inflater.inflate(R.layout.horz_scroll_menu, null);
        app = inflater.inflate(R.layout.horz_scroll_app, null);
        ViewGroup tabBar = (ViewGroup) app.findViewById(R.id.tabBar);

        ListView listView = (ListView) app.findViewById(R.id.list);
        ViewUtils.initListView(this, listView, "Item ", 30, android.R.layout.simple_list_item_1);

        listView = (ListView) menu.findViewById(R.id.list);
        ViewUtils.initListView(this, listView, "Menu ", 30, android.R.layout.simple_list_item_1);

        btnSlide = (ImageView) tabBar.findViewById(R.id.BtnSlide);
        btnSlide.setOnClickListener(new ClickListener());

        final View[] children = new View[] { menu, app };

        scrollView.initViews(children, 1, new SizeCallback() {
            @Override
            public void onGlobalLayout() {
                btnWidth = btnSlide.getMeasuredWidth();
                System.out.println("btnWidth=" + btnWidth);
            }

            @Override
            public void getViewSize(int idx, int w, int h, int[] dims) {
                dims[0] = w;
                dims[1] = h;
                if (idx == 0) {
                    dims[0] = w - btnWidth;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // menu.setVisibility(View.INVISIBLE);
    }
}

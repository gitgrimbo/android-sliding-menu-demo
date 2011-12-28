package grimbo.android.demo.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * This demo does not work.
 */
public class TestSlideActivity extends Activity {
    ViewGroup parentLayout;
    LinearLayout layout1;
    LinearLayout layout2;
    boolean layout1Shown = true;

    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            ViewUtils.printView("parentLayout", parentLayout);
            ViewUtils.printView("layout1", layout1);
            ViewUtils.printView("layout2", layout2);
            if (layout1Shown) {
                parentLayout.scrollTo(-parentLayout.getWidth(), 0);
            } else {
                parentLayout.scrollTo(0, 0);
            }
            layout1Shown = !layout1Shown;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = LayoutInflater.from(this).inflate(R.layout.test_slide_activity, null);
        setContentView(contentView);

        parentLayout = (ViewGroup) findViewById(R.id.ParentLayout);
        layout1 = (LinearLayout) findViewById(R.id.Layout1);
        layout2 = (LinearLayout) findViewById(R.id.Layout2);
        ViewUtils.setViewWidths(layout1, new View[] { layout2 });

        Button btnSlide = (Button) findViewById(R.id.BtnSlide);
        btnSlide.setOnClickListener(new ClickListener());
    }
}

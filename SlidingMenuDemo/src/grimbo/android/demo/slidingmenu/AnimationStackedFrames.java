/*
 * #%L
 * SlidingMenuDemo
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Paul Grime
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package grimbo.android.demo.slidingmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Animates the menu view over the app view in a FrameLayout.
 * 
 * As this uses animations, after the menu has moved over the app, touch events are still passed to the app, as the menu View
 * actually hasn't moved. The animation just renders the menu in a different location to its real position.
 */
public class AnimationStackedFrames extends Activity implements AnimationListener {
    FrameLayout mFrameLayout;
    View menu;
    View app;
    boolean menuOut = false;

    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            AnimationStackedFrames me = AnimationStackedFrames.this;
            Context context = me;
            Animation anim;
            if (!menuOut) {
                menu.setVisibility(View.VISIBLE);
                ViewUtils.printView("menu", menu);
                anim = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
            } else {
                anim = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
            }
            anim.setAnimationListener(me);
            // out.setAnimationListener(me);
            menu.startAnimation(anim);
        }
    }

    static class ViewMover implements OnClickListener {
        View v;
        int dx = 1;

        public ViewMover(View v, int dx) {
            this.v = v;
            this.dx = dx;
        }

        @Override
        public void onClick(View v) {
            move(this.v);
        }

        public void move(View v) {
            v.layout(v.getLeft() + dx, 0, v.getLeft() + dx + v.getWidth(), v.getHeight());
            ViewUtils.printView("menu", v);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_stacked_frames);

        mFrameLayout = (FrameLayout) this.findViewById(R.id.flipper);
        menu = mFrameLayout.findViewById(R.id.menu);
        app = mFrameLayout.findViewById(R.id.app);

        ViewUtils.printView("menu", menu);
        ViewUtils.printView("app", app);

        ListView listView = (ListView) app;
        ViewUtils.initListView(this, listView, "Item ", 30, android.R.layout.simple_list_item_1);

        View btns = findViewById(R.id.btns);
        btns.findViewById(R.id.BtnSlide).setOnClickListener(new ClickListener());
        btns.findViewById(R.id.BtnIncX).setOnClickListener(new ViewMover(menu, 5));
        btns.findViewById(R.id.BtnDecX).setOnClickListener(new ViewMover(menu, -5));
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        System.out.println("onAnimationEnd " + animation);
        System.out.println("menuOut=" + menuOut);
        ViewUtils.printView("menu", menu);
        ViewUtils.printView("app", app);
        menuOut = !menuOut;
        if (!menuOut) {
            menu.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        System.out.println("onAnimationRepeat " + animation);
        System.out.println("menuOut=" + menuOut);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        System.out.println("onAnimationStart " + animation);
        System.out.println("menuOut=" + menuOut);
    }
}

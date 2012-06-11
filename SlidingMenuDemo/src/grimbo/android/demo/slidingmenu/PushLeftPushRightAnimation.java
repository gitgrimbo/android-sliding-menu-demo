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
import android.widget.ViewFlipper;

/**
 * Demo of a push left/push right animation. Note that Android Animations do not move a View, they only alter the rendering of
 * the View.
 */
public class PushLeftPushRightAnimation extends Activity implements AnimationListener {
    ViewFlipper mFlipper;
    Animation in;
    Animation out;

    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            PushLeftPushRightAnimation me = PushLeftPushRightAnimation.this;
            Context context = me;
            int curView = mFlipper.getCurrentView().getId();
            if (R.id.view1 == curView) {
                in = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
                out = AnimationUtils.loadAnimation(context, R.anim.push_right_out);
            } else {
                in = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
                out = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
            }
            in.setAnimationListener(me);
            out.setAnimationListener(me);
            mFlipper.setInAnimation(in);
            mFlipper.setOutAnimation(out);
            mFlipper.showNext();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_left_push_right_animation);

        mFlipper = (ViewFlipper) findViewById(R.id.flipper);

        findViewById(R.id.BtnSlide).setOnClickListener(new ClickListener());
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        System.out.println("onAnimationEnd " + (in == animation ? "in" : "out"));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        System.out.println("onAnimationRepeat " + (in == animation ? "in" : "out"));
    }

    @Override
    public void onAnimationStart(Animation animation) {
        System.out.println("onAnimationStart " + (in == animation ? "in" : "out"));
    }
}

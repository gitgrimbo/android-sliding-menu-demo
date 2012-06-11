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
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Simple Activity that uses each Button in the layout to invoke an Activity.
 */
public class MenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String packageName = this.getClass().getPackage().getName();
        final Context context = this;

        // ScrollView
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.activity_menu, null);

        // Layout containing Buttons
        ViewGroup g = (ViewGroup) contentView.getChildAt(0);
        int count = g.getChildCount();

        for (int i = 0; i < count; i++) {
            Button btn = (Button) g.getChildAt(i);
            final String text = btn.getText().toString();
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Class c = Class.forName(packageName + "." + text);
                        startActivity(new Intent(context, c));
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(context, String.valueOf(e), 5000).show();
                    }
                }
            });
        }

        setContentView(contentView);
    }
}

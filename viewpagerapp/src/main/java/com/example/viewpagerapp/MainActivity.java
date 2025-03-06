package com.example.viewpagerapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewpager;
    MyViewPagerAdapter myAdapter;
    TabLayout  tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);

        myAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle()
        );

//        Adding Fragments to the list in the Adapter Class
        myAdapter.addFragment(new Fragment1());
        myAdapter.addFragment(new Fragment2());
        myAdapter.addFragment(new Fragment3());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            // Set the orientation in ViewPager2
            viewpager = findViewById(R.id.viewPager2);
            viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

           // Connecting the Adapter with ViewPager2
            viewpager.setAdapter(myAdapter);

            // Connecting TabLayout with ViewPager
            new TabLayoutMediator(
                    tabLayout,
                    viewpager,
                    new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText("Fragment" +(position +1));
                        }
                    }
            ).attach();

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
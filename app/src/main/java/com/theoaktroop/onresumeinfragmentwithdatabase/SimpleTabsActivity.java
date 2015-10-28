package com.theoaktroop.onresumeinfragmentwithdatabase;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.theoaktroop.onresumeinfragmentwithdatabase.fragments.QuestionnaireFragment;
import com.theoaktroop.onresumeinfragmentwithdatabase.fragments.SavedFormsFragment;
import com.theoaktroop.onresumeinfragmentwithdatabase.fragments.WelcomeFragment;


public class SimpleTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tabs);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WelcomeFragment(), "Welcome");
        adapter.addFragment(new QuestionnaireFragment(), "Question Fragment");
        adapter.addFragment(new SavedFormsFragment(), "Answer Fragment");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((ViewPagerAdapter) viewPager.getAdapter()).getFragment(position);
                if (position == 0 & fragment!=null) {
                    System.out.println("Form Tabs position= "+position);
                    fragment.onResume();
                }
                if (position == 1 & fragment!=null) {
                    System.out.println("Form Tabs position= "+position);
                    fragment.onResume();
                }
                if (position == 2 & fragment!=null) {
                  System.out.println("Form Tabs position= "+position);
                  fragment.onResume();
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}

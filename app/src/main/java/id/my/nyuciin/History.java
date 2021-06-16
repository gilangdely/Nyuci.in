package id.my.nyuciin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    //deklarasi variable
    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //assign bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.market:
                        return true;
                    case R.id.history:
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        //inisialiasi variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //inisialisasi adapter
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        //add fragments
        mainAdapter.AddFragment(new PickFragment(), "Pick");
        mainAdapter.AddFragment(new TransactionFragment(), "Transaction");
        mainAdapter.AddFragment(new ShopFragment(), "Shop");

        //set adapter
        viewPager.setAdapter(mainAdapter);
        //connect tab layout with view pager
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //inisialisasi array list
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        //create constructor
        public void AddFragment(Fragment fragment, String s){
            //add fragment
            fragmentArrayList.add(fragment);
            //add string
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return stringArrayList.get(position);
        }
    }
}
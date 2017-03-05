package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.reflect.Array.getInt;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper vf;
    ArrayList<String> categories;
    ArrayList<Integer> balances;
    String[] sections;
    Integer[] sums;

    ArrayList<String> transHist;
    String[] transHistArr;
    CategoryListAdapter adapter;
    ArrayAdapter adapter1;
    private static final String SUMM_PREFERENCE_FILE_KEY =
            "me.colinmarsch.simpleweather.mindyourmoney.sum_key";
    private static final String LOG_PREFERENCE_FILE_KEY =
            "me.colinmarsch.simpleweather.mindyourmoney.log_key";
    SharedPreferences sharedPrefSumm;
    SharedPreferences sharedPrefLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vf = (ViewFlipper) findViewById(R.id.vf);
        vf.setDisplayedChild(0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), Payment.class);
                startActivityForResult(in, 2);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPrefSumm = this.getSharedPreferences(SUMM_PREFERENCE_FILE_KEY, MODE_PRIVATE);
        sharedPrefLog = this.getSharedPreferences(LOG_PREFERENCE_FILE_KEY, MODE_PRIVATE);

        categories = new ArrayList<>();
        balances = new ArrayList<>();
        transHist = new ArrayList<>();
        loadSummaryData();
        updateCats();
    }

    private void loadSummaryData() {
        if(categories.size() == 0) {
            Map<String, ?> map = sharedPrefSumm.getAll();
            for(Map.Entry<String, ?> entry : map.entrySet()) {
                categories.add(entry.getKey());
                balances.add((Integer) entry.getValue());
            }
        }
    }

    private void updateTrans() {
        transHistArr = sharedPrefLog.getString("0", "").split(",");
        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transHistArr);
        ListView list = (ListView) findViewById(R.id.transactions);
        list.setAdapter(adapter1);
    }

    private void updateCats() {
        sections = categories.toArray(new String[categories.size()]);
        sums = balances.toArray(new Integer[balances.size()]);
        adapter = new CategoryListAdapter(this, sections, sums);
        ListView list = (ListView) findViewById(R.id.sections);
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_category) {
            Intent in = new Intent(this, NewCat.class);
            startActivityForResult(in, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                categories.add(data.getStringExtra("name"));
                balances.add(data.getIntExtra("budget", 0));
                SharedPreferences.Editor editor = sharedPrefSumm.edit();
                editor.putInt(data.getStringExtra("name"), data.getIntExtra("budget", 0));
                editor.commit();
                updateCats();
                adapter.notifyDataSetChanged();
            }
        } else if(requestCode == 2) {
            if(resultCode == RESULT_OK) {
                transHist.add(data.getStringExtra("category") + " : " + data.getIntExtra("spent", 0));
                SharedPreferences.Editor editor = sharedPrefLog.edit();
                editor.putString("0", sharedPrefLog.getString("0", "") + "," +
                        data.getStringExtra("category") + " : " + data.getIntExtra("spent", 0));
                editor.commit();
                SharedPreferences.Editor editor1 = sharedPrefSumm.edit();
                int temp = sharedPrefSumm.getInt(data.getStringExtra("category"), 0);
                if(temp != 0) {
                    editor1.putInt(data.getStringExtra("category"), (temp - data.getIntExtra("spent", 0)));
                }
                editor1.commit();
                categories = new ArrayList<>();
                balances = new ArrayList<>();
                loadSummaryData();
                updateCats();
                updateTrans();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        if (id == R.id.nav_summary) {
            vf.setDisplayedChild(0);
            tb.getMenu().findItem(R.id.add_category).setVisible(true);
            fab.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_expenses) {
            vf.setDisplayedChild(1);
            transHist = new ArrayList<>();
            updateTrans();
            tb.getMenu().findItem(R.id.add_category).setVisible(false);
            fab.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_settings) {
            vf.setDisplayedChild(2);
            tb.getMenu().findItem(R.id.add_category).setVisible(false);
            fab.setVisibility(View.GONE);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

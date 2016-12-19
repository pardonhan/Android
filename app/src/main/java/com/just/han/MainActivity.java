package com.just.han;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.just.han.activity.BottomNavigationActivity;
import com.just.han.activity.HorizontalListViewActivity;
import com.just.han.activity.HttpConnActivity;
import com.just.han.activity.InflaterActivity;
import com.just.han.activity.ScrollRefreshActivity;
import com.just.han.activity.SmsReadActivity;
import com.just.han.activity.SsqActivity;
import com.just.han.views.CustomTitleText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.hello_world)
    TextView tvHelloWorld;
    @BindView(R.id.android_tv)
    TextView tvAndroid;
    @BindView(R.id.http_connect_tv)
    TextView tvHttpConnect;
    @BindView(R.id.bottom_navigation_bar_tv)
    TextView tvBottomNavigationBar;
    @BindView(R.id.dry_sister_tv)
    TextView tvDrySister;
    @BindView(R.id.custom_title_text)
    CustomTitleText customTitleText;
    @BindView(R.id.code_edit_text)
    EditText etCode;
    @BindView(R.id.btn_yanzheng)
    Button btnYanzheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        tvHelloWorld.setOnClickListener(this);
        tvAndroid.setOnClickListener(this);
        tvHttpConnect.setOnClickListener(this);
        tvBottomNavigationBar.setOnClickListener(this);
        tvDrySister.setOnClickListener(this);
        btnYanzheng.setOnClickListener(this);
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.nav_camera:
                intent.setClass(MainActivity.this, SmsReadActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                intent.setClass(MainActivity.this, CollapsedTextActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_slideshow:
                intent.setClass(MainActivity.this, TaskListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_manage:
                intent.setClass(MainActivity.this, ScrollRefreshActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_share:
                intent.setClass(MainActivity.this, HorizontalListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                intent.setClass(MainActivity.this, InflaterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent();
        switch (view.getId()) {
            case R.id.btn_yanzheng:
                String msg = "";
                if (customTitleText.getmTitleText().equals(etCode.getText().toString())) {
                    msg = "一致";
                } else {
                    msg = "不一致";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                /*Intent intent = new Intent();
                intent.setClass(MainActivity.this, GridViewActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "10086");
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
            case R.id.hello_world:
                showDialog();
                break;
            case R.id.android_tv:
                intent1.setClass(MainActivity.this, SsqActivity.class);
                startActivity(intent1);
                break;
            case R.id.http_connect_tv:
                intent1.setClass(MainActivity.this, HttpConnActivity.class);
                startActivity(intent1);
                break;
            case R.id.bottom_navigation_bar_tv:
                intent1.setClass(MainActivity.this, BottomNavigationActivity.class);
                startActivity(intent1);
                break;
            case R.id.dry_sister_tv:

                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("让我们一起飞，我带着你，你带着钱，来一场说走就走的旅行")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setTitle("Material Design Dialog")
                .show();
    }
}

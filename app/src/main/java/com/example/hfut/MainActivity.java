package com.example.hfut;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener,View.OnClickListener,Toolbar.OnMenuItemClickListener {
    //设置菜单名称数组
    private ListView listView;
    private GridLayout gridLayout;
    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    private com.example.hfut.CircleImageView circleImageView;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView bottomNavigationView1;
    //图标数组
    private int[] icons = {
            R.drawable.id_card, R.drawable.message, R.drawable.smile, R.drawable.team, R.drawable.trophy, R.drawable.book
    };
    //信息数组
    private String[] names = {
            "个人信息", "消息", "GPA计算器", "班务系统", "打卡", "题库"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//全屏显示
        // 创建一个list集合
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        ListView listView = (ListView) findViewById(R.id.listView);
        // 通过for循环将图片id和列表项文字放到Map中，并添加到List集合中
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
            map.put("图标", icons[i]);
            map.put("信息", names[i]);
            map.put("尾部图标", R.drawable.right);
            listItems.add(map);                                     // 将map对象添加到List集合中
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.item, new String[]{"图标", "信息", "尾部图标"}, new int[]{
                R.id.listViewImage, R.id.listViewTextView, R.id.listViewImageLeft});          // 创建SimpleAdapter
        listView.setAdapter(adapter);              // 将适配器与ListView关联
        //获取DrawerLayout布局
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar2);//显示菜单项
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //设置状态变化监听器
        drawerLayout.addDrawerListener(this);
        circleImageView = (com.example.hfut.CircleImageView) findViewById(R.id.viewToBar);
        circleImageView.setOnClickListener(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationview);//底部导航栏
        bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottomnavigationviewPage);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView1.setOnNavigationItemSelectedListener(this);
    }

    //当抽屉的位置发生变化时调用
    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    //当抽屉已经处于完全打开的状态时调用
    @Override
    public void onDrawerOpened(@NonNull View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
    }

    //当抽屉已经完全关闭状态时调用
    @Override
    public void onDrawerClosed(@NonNull View view) {
    }

    //抽屉滑动状态改变时调用
    //状态值STATE_IDLE：闲置、STATE_DRAGGING：拖拽、STATE_SETTLING：固定的
    @Override
    public void onDrawerStateChanged(int i) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == circleImageView.getId()) {
            drawerLayout.openDrawer(Gravity.LEFT);//侧划栏向那边划，在xml的 android:layout_gravity="left"中设置的
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //处理底部导航栏点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        FragmentManager fm = getSupportFragmentManager();       // 获取Fragment
        FragmentTransaction ft = fm.beginTransaction();  // 开启一个事务
        android.support.v4.app.Fragment f = null;                               //为Fragment初始化
        switch (itemId) {
            case R.id.item_1: {
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
            case R.id.item_2: {
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
            case R.id.item_3: {
                finish();
                return true;
            }
            case R.id.item1_1: {
                f = new HomePageFragment();
                TextView textView=findViewById(R.id.toolBarText);
                textView.setText(R.string.HomePage);
                textView.setGravity(Gravity.CENTER);
                break;
            }
            case R.id.item1_2: {
                f = new SocreFragement();
                TextView textView=findViewById(R.id.toolBarText);
                textView.setText(R.string.Score);
                textView.setGravity(Gravity.CENTER);
                break;
            }
            case R.id.item1_3: {
                f = new PersonInfo();
                TextView textView=findViewById(R.id.toolBarText);
                textView.setText(R.string.Person);
                textView.setGravity(Gravity.CENTER);
                break;
            }
        }
        ft.replace(R.id.KS, f);         //替换FragmentLayout
        ft.commit();
        return true;
    }

}
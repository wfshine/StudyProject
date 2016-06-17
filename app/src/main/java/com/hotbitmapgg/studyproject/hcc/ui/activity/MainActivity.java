package com.hotbitmapgg.studyproject.hcc.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.AbsBaseActivity;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.CustomWidgetFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.ExpressionPackageFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.HomeFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.MdWidgetFragment;
import com.hotbitmapgg.studyproject.hcc.ui.fragment.RxjavaDemoFragment;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private HomeFragment homeFragment;

    private ExpressionPackageFragment expressionPackageFragment;

    private RxjavaDemoFragment rxjavaDemoFragment;

    private Fragment[] fragments;

    private int currentTabIndex;

    private int index;

    private CustomWidgetFragment customWidgetFragment;

    private MdWidgetFragment mdFragment;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        if (mNavigationView != null)
        {
            setupDrawerContent(mNavigationView);
        }

        expressionPackageFragment = ExpressionPackageFragment.newInstance();
        homeFragment = HomeFragment.newInstance();
        rxjavaDemoFragment = RxjavaDemoFragment.newInstance();
        customWidgetFragment = CustomWidgetFragment.newInstance();
        mdFragment = MdWidgetFragment.newInstance();

        fragments = new Fragment[]{homeFragment, customWidgetFragment, rxjavaDemoFragment, expressionPackageFragment, mdFragment};

        getFragmentManager().beginTransaction().replace(R.id.content, homeFragment).commit();
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("Gank.Io");
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_debug:
                startActivity(new Intent(MainActivity.this, ApiDebugActivity.class));
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {

                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        index = 0;
                        addFragment(fragments[0]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("Gank.IO");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.nav_messages:
                        index = 1;
                        addFragment(fragments[1]);
                        mToolbar.setTitle("CustomWidget");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_my_focus:
                        index = 2;
                        addFragment(fragments[2]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("RxJava");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_foucs_me:
                        index = 4;
                        addFragment(fragments[4]);
                        mToolbar.setTitle("MaterialDesign");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_article:
                        index = 3;
                        addFragment(fragments[3]);
                        menuItem.setChecked(true);
                        mToolbar.setTitle("AqualandFace");
                        mDrawerLayout.closeDrawers();
                        mFloatingActionButton.setVisibility(View.GONE);
                        return true;

                    case R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, HotBitmapGGActivity.class));
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }


    public void addFragment(Fragment fragment)
    {

        FragmentTransaction trx = getFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded())
        {
            trx.add(R.id.content, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }

    @OnClick(R.id.fab)
    void startPostGank()
    {

        startActivity(new Intent(MainActivity.this, GankPostActivity.class));
    }
}

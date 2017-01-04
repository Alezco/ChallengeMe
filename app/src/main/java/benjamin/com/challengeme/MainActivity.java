package benjamin.com.challengeme;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{
    private Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        myBtn = (Button) findViewById(R.id.button);
        myBtn.setOnClickListener(this);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Click !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            Toast.makeText(MainActivity.this, "nav_camera", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_gallery)
        {
            Toast.makeText(MainActivity.this, "nav_gallery", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_slideshow)
        {
            Toast.makeText(MainActivity.this, "nav_slideshow", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_manage)
        {
            Toast.makeText(MainActivity.this, "nav_manage", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_share)
        {
            Toast.makeText(MainActivity.this, "nav_share", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_send)
        {
            Toast.makeText(MainActivity.this, "nav_send", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String get(String url) throws IOException
    {
        InputStream is = null;
        try
        {
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            return readIt(is);
        }
        finally
        {
            try
            {
                if (is != null)
                    is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String readIt(InputStream is) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null)
            response.append(line).append('\n');
        return response.toString();
    }

    @Override
    public void onClick(View v)
    {
        if (v == myBtn)
        {
            try
            {
                Log.d("===============", "before get URL");
                Log.d(">>>>>>>>>>>>>>>>", get("http://challengeme.fr/get_challenges.php"));
                Log.d("===============", "end get URL with success");
            }
            catch (IOException e)
            {
                Log.d("================", "error catched while getting URL");
                e.printStackTrace();
            }
        }
    }
}

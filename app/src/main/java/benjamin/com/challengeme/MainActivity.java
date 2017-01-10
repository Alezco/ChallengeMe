package benjamin.com.challengeme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import benjamin.com.challengeme.Connection.Authentication.User;
import benjamin.com.challengeme.Connection.Database.RequestManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{
    TextView temp_text;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        temp_text = (TextView) findViewById(R.id.temp_text);
        temp_text.setText(RequestManager.getInstance().getChallenges());

        Intent intent = getIntent();
        User user = (User) intent.getExtras().get("User");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setMenuItems(user);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_manage)
            Log.d("===========", "Settings");
        else if (id == R.id.nav_send)
            sendMail();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v)
    {

    }

    private void sendMail()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"challengeme@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Challenge Me ");
        startActivity(Intent.createChooser(intent, getApplicationContext().getString(R.string.send_mail)));
    }

    private void setMenuItems(User user)
    {
        View view = navigationView.getHeaderView(0);
        TextView name = (TextView) view.findViewById(R.id.menu_name);
        name.setText(user.getFirstName() + " " +  user.getLastName());
        TextView email = (TextView) view.findViewById(R.id.menu_email);
        email.setText(user.getEmail());
        ImageView profilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
        Picasso.with(getApplicationContext()).load(user.getPhotoURL()).placeholder(R.drawable.menu_player).into(profilePhoto);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.menu_cover);
        final ImageView coverPhoto = new ImageView(this);
        Picasso.with(this).load(user.getCoverURL()).into(coverPhoto, new Callback()
        {
            @Override
            public void onSuccess()
            {
                linearLayout.setBackgroundDrawable(coverPhoto.getDrawable());
            }

            @Override
            public void onError() { }
        });
    }
}
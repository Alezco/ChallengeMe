package benjamin.com.challengeme.Connection.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import benjamin.com.challengeme.MainActivity;
import benjamin.com.challengeme.R;

public class LoginActivity extends AppCompatActivity
{
    LoginButton loginButton;
    CallbackManager callbackManager;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.login_screen);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_friends");

        handleLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleLogin()
    {
        login();
        requestBasicInfo();
    }

    private void login()
    {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Toast.makeText(getApplicationContext(), "Successful login !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel()
            {
                Toast.makeText(getApplicationContext(), "Login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestBasicInfo()
    {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("limit", 5000);
                Toast.makeText(getApplicationContext(), "Success retrieving data", Toast.LENGTH_SHORT).show();
                new GraphRequest(
                        loginResult.getAccessToken(),
                        "me?fields=id,email,first_name,last_name,picture.width(120).height(120){url},friends,cover",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback()
                        {
                            public void onCompleted(GraphResponse response)
                            {
                                JSONObject res = response.getJSONObject();
                                user = new User();
                                try
                                {
                                    user.setFbId(res.get("id").toString());
                                    user.setEmail(res.get("email").toString());
                                    user.setFirstName(res.get("first_name").toString());
                                    user.setLastName(res.get("last_name").toString());
                                    user.setPhotoURL(res.getJSONObject("picture").getJSONObject("data").get("url").toString());
                                    user.setCoverURL(res.getJSONObject("cover").get("source").toString());
                                    JSONArray jsonArray = res.getJSONObject("friends").getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++)
                                    {
                                        List<User> tmp = new ArrayList<>();
                                        tmp.add((User) jsonArray.get(i));
                                        user.setFriends(tmp);
                                    }
                                    Log.d("========", res.toString());
                                } catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                                goToMainActivity(user);
                                Log.d("===========", response.toString());
                            }
                        }
                ).executeAsync();
            }

            @Override
            public void onCancel()
            {
                Toast.makeText(getApplicationContext(), "Retrieving data cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(getApplicationContext(), "Retrieving data error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMainActivity(User user)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("User", user);
        startActivityForResult(intent, 0);
    }
}
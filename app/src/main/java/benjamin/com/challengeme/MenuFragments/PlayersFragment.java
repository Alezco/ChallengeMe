package benjamin.com.challengeme.MenuFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import benjamin.com.challengeme.Connection.Authentication.User;
import benjamin.com.challengeme.R;

public class PlayersFragment extends android.support.v4.app.Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.players, container, false);
        ListView friendList = (ListView) rootView.findViewById(R.id.friend_list);
        User user = (User) getActivity().getIntent().getExtras().get("User");
        assert user != null;
        List<User> friends = user.getFriends();
        Log.d("============", String.valueOf(friends.size()));
        ListAdapter listAdapter = new ListAdapter(getActivity(), friends);
        friendList.setAdapter(listAdapter);
        return rootView;
    }
}

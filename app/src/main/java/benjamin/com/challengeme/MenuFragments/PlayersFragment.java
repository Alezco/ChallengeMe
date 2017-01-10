package benjamin.com.challengeme.MenuFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import benjamin.com.challengeme.R;

public class PlayersFragment extends android.support.v4.app.Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.players, container, false);
        assert rootView != null;
        /*ListView friendList = (ListView) rootView.findViewById(R.id.friend_list);
        List<String> arrayList = new ArrayList<>();
        arrayList.add("User 1");
        arrayList.add("User 2");
        arrayList.add("User 3");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.players, arrayList);
        friendList.setAdapter(arrayAdapter);*/
        return rootView;
    }
}

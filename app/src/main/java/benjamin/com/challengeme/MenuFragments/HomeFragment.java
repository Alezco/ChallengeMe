package benjamin.com.challengeme.MenuFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import benjamin.com.challengeme.Connection.Database.RequestManager;
import benjamin.com.challengeme.R;

public class HomeFragment extends Fragment
{
    TextView temp_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.home, container, false);
        assert rootView != null;
        temp_text = (TextView) rootView.findViewById(R.id.temp_text);
        temp_text.setText(RequestManager.getInstance().getChallenges());
        return rootView;
    }
}

package benjamin.com.challengeme.Menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import benjamin.com.challengeme.Connection.Authentication.User;
import benjamin.com.challengeme.R;

public class ListAdapter extends BaseAdapter
{
    private List<User> friendList;
    private LayoutInflater inflater;
    private Activity activity;

    public ListAdapter(Activity activity, List<User> friendList)
    {
        this.activity = activity;
        this.friendList = friendList;
    }

    @Override
    public int getCount()
    {
        return friendList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return friendList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.menu_item, viewGroup, false);

        ImageView friendPicture = (ImageView) view.findViewById(R.id.friend_list_photo);
        Picasso.with(activity.getApplicationContext())
                .load(friendList.get(i).getPhotoURL())
                .placeholder(R.drawable.menu_player)
                .into(friendPicture);
        TextView friendName = (TextView) view.findViewById(R.id.friend_list_name);
        friendName.setText(friendList.get(i).getFirstName() + " " + friendList.get(i).getLastName());

        return view;
    }
}

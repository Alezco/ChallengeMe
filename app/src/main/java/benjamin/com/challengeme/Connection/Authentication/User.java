package benjamin.com.challengeme.Connection.Authentication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable
{
    private String fbId;
    private String email;
    private String firstName;
    private String lastName;
    private String photoURL;
    private String coverURL;
    private List<User> friends;

    public User()
    {
        friends = new ArrayList<>();
    }

    public String getFbId()
    {
        return fbId;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getPhotoURL()
    {
        return photoURL;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCoverURL()
    {
        return coverURL;
    }

    public List<User> getFriends()
    {
        return friends;
    }

    public void setFbId(String fbId)
    {
        this.fbId = fbId;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setPhotoURL(String photoURL)
    {
        this.photoURL = photoURL;
    }

    public void setCoverURL(String coverURL)
    {
        this.coverURL = coverURL;
    }

    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }
}

package benjamin.com.challengeme.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager
{
    private static RequestManager ourInstance = new RequestManager();

    public static RequestManager getInstance()
    {
        return ourInstance;
    }

    private RequestManager()
    {
    }

    private String get(String url) throws IOException
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

    public String getChallenges()
    {
        try
        {
            return get("http://challengeme.fr/get_challenges.php");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

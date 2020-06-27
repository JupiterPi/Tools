package jupiterpi.tools.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An object to do web request.
 * Better use the Http interface to access this.
 * @see Http
 */
public class Request {
    /**
     * The request method.
     */
    private String method;
    /**
     * The url for the request.
     */
    private URL url;
    /**
     * The response.
     */
    private String response = "";

    /**
     * Creates a new Request object.
     * @param method The request method.
     * @param url The url for the request.
     */
    public Request(String method, String url) {
        this.method = method;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the request.
     * @throws RequestError There may be a request error (response code != 200).
     */
    public void send() throws RequestError {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            Reader reader = null;
            int responseCode = connection.getResponseCode();
            boolean error = connection.getResponseCode() > 299;
            if (error) {
                reader = new InputStreamReader(connection.getErrorStream());
            } else {
                reader = new InputStreamReader(connection.getInputStream());
            }
            BufferedReader in = new BufferedReader(reader);
            String inputLine;
            String response = "";
            while ((inputLine = in.readLine()) != null) {
                response += inputLine + "\n";
            }
            response = response.substring(0, response.length()-1);

            if (error) throw new RequestError(responseCode, response);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the response.
     * @return The response.
     * @throws RequestError There may be a request error (response code != 200).
     */
    public String response() throws RequestError {
        if (response.equals("")) send();
        return response;
    }
}

package jupiterpi.tools.http;

/**
 * Some static methods for doing http requests.
 */
public class Http {
    /**
     * Sends a GET request.
     * @param url The url for the request.
     * @return The response.
     * @throws RequestError There may be a request error (response code != 200).
     */
    public static String get(String url) throws RequestError {
        Request request = new Request("GET", url);
        return request.response();
    }

    /**
     * Sends a GET request.
     * @param url The url for the request.
     * @throws RequestError There may be a request error (response code != 200).
     */
    public static void post(String url) throws RequestError {
        Request request = new Request("POST", url);
        request.send();
    }
}

package jupiterpi.tools.http;

public class RequestError extends Exception {
    /**
     * The response code.
     */
    public int responseCode;
    /**
     * The response.
     */
    public String response;

    /**
     * Creates a RequestError object.
     * @param responseCode The response code.
     * @param response The response.
     */
    public RequestError(int responseCode, String response) {
        this.responseCode = responseCode;
        this.response = response;
    }

    /**
     * Formats the exception for console output.
     * @return The output.
     */
    public String toString() {
        return "RequestError: " + responseCode + ", text: " + response;
    }
}

package jupiterpi.tools.crossplatform;

/**
 * A simple handler for platforms.
 */
public class Platforms {

    /**
     * Gives you the platform your application is running on.
     * @return The platform.
     */
    public static Platform getCurrentPlatform() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) return Platform.WINDOWS;
        else if (os.contains("Linux")) return Platform.LINUX;
        else if (os.contains("Mac")) return Platform.MAC;
        else return null;
    }
}

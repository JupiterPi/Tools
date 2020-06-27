package jupiterpi.tools.ssh;

import com.jcraft.jsch.*;
import jupiterpi.tools.streams.StreamReader;

import java.io.IOException;
import java.util.List;

/**
 * A SSH connection using the com.jcraft.jsch library.
 */
public class SSHConnection {
    /**
     * The host's url.
     */
    private String host;
    /**
     * The username to log in with.
     */
    private String username;
    /**
     * The password to log in with.
     */
    private String password;

    /**
     * The SSH port.
     */
    private final int SSH_PORT = 22;

    /**
     * The corresponding Session object.
     * @see Session
     */
    private Session session;

    /**
     * Creates a SSHConnection object.
     * @param host The host's url.
     * @param username The username to log in with.
     * @param password The password to log in with.
     */
    public SSHConnection(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a SSHConnection object.
     * @param host The host's url.
     * @param username The username to log in with.
     * @param password The password to log in with.
     * @param smartConnect If the connect() method should be called automatically.
     */
    public SSHConnection(String host, String username, String password, boolean smartConnect) {
        this.host = host;
        this.username = username;
        this.password = password;
        if (smartConnect) connect();
    }

    /**
     * Connects to the host.
     */
    public void connect() {
        try {
            session = new JSch().getSession(username, host, SSH_PORT);
            session.setPassword(password);
            session.connect(3 * 1000);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs a command on the host server.
     * @param command The command.
     * @return The console output.
     */
    public List<String> run(String command) {
        Channel channel = null;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            List<String> result = null;
            try {
                result = new StreamReader(channel.getInputStream()).getLines();
            } catch (IOException e) {
                e.printStackTrace();
            }

            channel.disconnect();
            return result;
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Disconnects from the host.
     */
    public void disconnect() {
        session.disconnect();
    }
}
package jupiterpi.tools.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An API for running commands in the command line.
 */
public class CommandLine {
    /**
     * Runs a command in the command line.
     * @param command The command to execute.
     * @return The command line output of the command.
     * @throws IOException The command execution and the reading of the response might throw this exception.
     */
    public static String runCommand(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        String line;
        while ((line = reader.readLine()) != null) {
            output += line;
        }
        return output;
    }
}

package jupiterpi.tools.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple console interface class with some methods for user interaction.
 */
public class ConsoleInterface {
    /**
     * Prints out multiple lines.
     * @param lines The lines.
     */
    protected void printLines(String... lines) {
        for (String line : lines) {
            out(line);
        }
    }

    /**
     * Prints out a message in a separate line.
     * @param message The message.
     */
    protected void out(String message) {
        System.out.println(message);
    }

    /**
     * Prints out a message on the screen.
     * @param message The message.
     */
    protected void print(String message) {
        System.out.print(message);
    }

    /**
     * Reads the user's input.
     * @param message A message to send before reading.
     * @return The input.
     */
    protected String in(String message) {
        print(message);
        return in();
    }

    /**
     * Reads the user's input.
     * @return The input.
     */
    protected String in() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

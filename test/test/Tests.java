package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import wrappers.MainWrapper;

import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    private static String[] outLines;
    private static PrintStream originalOut;
    private static ByteArrayOutputStream outContent;
    private static MainWrapper<?> h;

    static void setup() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        h = new MainWrapper<>();
    }

    public static void testMain() {
        setup();

        h.main().invoke();

        outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);
        if (outLines.length != 0) {
            String printed = String.join(System.lineSeparator(), outLines);
            fail("Die Methode main() gibt eine falsche Anzahl an Zeilen aus. Sie darf nichts ausgeben!" + System.lineSeparator() + printed);
        }
    }
}

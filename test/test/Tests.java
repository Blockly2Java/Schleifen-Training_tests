package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import wrappers.MainWrapper;

import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    private static PrintStream originalOut;
    private static ByteArrayOutputStream outContent;
    private static MainWrapper<?> h = new MainWrapper<>();
    private static double[] limits = { 0,1,5,10,15,100,420,-1,-5,-10,11,42,85,32 };





    static void helper_lineCount(String[] expectedLines, String[] outLines, String input)
    {
        if(expectedLines.length != outLines.length) {
            fail("Methoden-Aufruf " + input + "\nDeine Ausgabe hat "+ outLines.length +" Zeilen, erwartet wurden " + expectedLines.length + " Zeilen.");
        }
    }
    static void helper_exaktMatch(String[] expectedLines, String[] outLines, String input)
    {
        List<String> actuals = new LinkedList<>();
        List<String> expecteds = new LinkedList<>();
        List<Integer> indexes = new LinkedList<>();

        for(int i = 0; i < expectedLines.length && i < outLines.length; i++)
        {
            String exp = expectedLines[i].strip().toLowerCase().replace(" ", "").replace(".0", "");
            String act = outLines[i].strip().toLowerCase().replace(" ", "").replace(".0", "");

            if(!exp.equals(act)) {
                indexes.add(i+1);
                actuals.add(outLines[i]);
                expecteds.add(expectedLines[i]);
            }
        }
        if(!indexes.isEmpty()) {
            List<String> errMsgBuilder = new LinkedList<>();
            for(int i = 0; i < indexes.size() && i<5; i++) {
                int ind = indexes.get(i);
                String act = actuals.get(i);
                String exp = expecteds.get(i);
                errMsgBuilder.add(String.format("- Zeile %d: '%s' statt '%s'", ind, act, exp));
            }
            fail("Methoden-Aufruf " + input + String.format("""
                \n
                Die Ausgabe mancher deiner Zeilen stimmt nicht mit der erwarteten Ausgabe überein.
                Zum Beispiel:
                %s
                """, String.join("\n", errMsgBuilder)));
        }
        else if(expectedLines.length != outLines.length) {
            fail("Methoden-Aufruf " + input + "\nDer Inhalt der Zeilen, die du ausgibst, ist korrekt, jedoch passt die Anzahl der Zeilen nicht (es fehlen Zeilen oder du gibst zu viele aus).");
        }
    }


    static void Aufgabe1_ZeilenAnzahl() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe1().invoke();
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe1();
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_lineCount(expectedLines, outLines, "aufgabe1()");
    }
    static void Aufgabe1_Inhalt() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        h.aufgabe1().invoke();
        String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AufgabeMock.aufgabe1();
        String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);

        helper_exaktMatch(expectedLines, outLines, "aufgabe1()");
    }


    static void Aufgabe2_ZeilenAnzahl() {

        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe2().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe2(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_lineCount(expectedLines, outLines, "aufgabe2("+limit+")");
        }
    }
    static void Aufgabe2_Inhalt() {
        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe2().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe2(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_exaktMatch(expectedLines, outLines, "aufgabe2("+limit+")");
        }
    }


    static void Aufgabe3_ZeilenAnzahl() {
        for(double start : limits) {
            for (double ende : limits) {
                ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                h.aufgabe3().invoke(start, ende);
                String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

                outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                AufgabeMock.aufgabe3(start, ende);
                String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

                helper_lineCount(expectedLines, outLines, "aufgabe3("+start+","+ende+")");
            }
        }
    }
    static void Aufgabe3_Inhalt() {
        for(double start : limits) {
            for (double ende : limits) {
                ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                h.aufgabe3().invoke(start,ende);
                String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

                outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                AufgabeMock.aufgabe3(start,ende);
                String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

                helper_exaktMatch(expectedLines, outLines, "aufgabe3("+start+","+ende+")");
            }
        }
    }


    static void Aufgabe4_ZeilenAnzahl() {
        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe4().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe4(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_lineCount(expectedLines, outLines, "aufgabe4("+limit+")");
        }
    }
    static void Aufgabe4_Inhalt() {
        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe4().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe4(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_exaktMatch(expectedLines, outLines, "aufgabe4("+limit+")");
        }
    }


    static void Aufgabe5_ZeilenAnzahl() {
        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe5().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe5(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_lineCount(expectedLines, outLines, "aufgabe5("+limit+")");
        }
    }
    static void Aufgabe5_Inhalt() {
        for(double limit : limits) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            h.aufgabe5().invoke(limit);
            String[] outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            AufgabeMock.aufgabe5(limit);
            String[] expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x -> !x.equals("")).toArray(String[]::new);

            helper_exaktMatch(expectedLines, outLines, "aufgabe5("+limit+")");
        }
    }

}

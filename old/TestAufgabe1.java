package src.x09p03;

import de.tum.in.test.api.BlacklistPath;
import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistPath;
import de.tum.in.test.api.jupiter.Public;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static de.tum.in.test.api.util.ReflectionTestUtils.getMethod;
import static de.tum.in.test.api.util.ReflectionTestUtils.invokeMethod;
import static org.junit.jupiter.api.Assertions.fail;

@Public
@WhitelistPath("target") // mainly for Artemis
@BlacklistPath("target/test-classes") // prevent access to test-related classes and resources
class TestAufgabe1
{
    private String[] outLines;
    private String[] expectedLines;

    private PrintStream originalOut;

    @BeforeEach
    void setup() throws ClassNotFoundException
    {
        originalOut = System.out;
        Random r = new Random();
        int limit = r.nextInt(2000)+10;
        runMethodUnderTest(limit);
        runExpectedMethod(limit);
    }

    @AfterEach
    void cleanup()
    {
        System.setOut(originalOut);
    }

    void runMethodUnderTest(int limit) throws ClassNotFoundException
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Class<?> myClass = Class.forName("src.x09p03.Aufgaben");
        invokeMethod(myClass, getMethod(myClass, "aufgabe1", int.class), limit);

        outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void runExpectedMethod(int limit)
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        src.x09p03.AufgabeMock.aufgabe1(limit);

        expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void helper_lineCount()
    {
        if(expectedLines.length != outLines.length)
        {
            fail("Deine Ausgabe hat "+ outLines.length +" Zeilen, erwartet wurden " + expectedLines.length + " Zeilen.");
        }
    }

    void helper_exaktMatch()
    {
        List<String> errMsgBuilder = new LinkedList<>();
        for(int i = 0; i < expectedLines.length && i < outLines.length; i++)
        {
            if(!expectedLines[i].equals(outLines[i]))
            {
                errMsgBuilder.add((i+1)+".");
            }
        }
        if(errMsgBuilder.size()>0)
        {
            fail("Die Ausgabe deiner " + String.join(", ",errMsgBuilder) + " Zeile(n) stimmt nicht mit der erwarteten überein.");
        }
        else if(expectedLines.length != outLines.length)
        {
            fail("Der Inhalt der Zeilen, die du ausgibst, ist korrekt, jedoch passt die Anzahl der Zeilen nicht (es fehlen Zeilen oder du gibst zu viele aus).");
        }
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe1_lineCount1()
    {
        helper_lineCount();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe1_exactMatch1()
    {
        helper_exaktMatch();
    }


    @Test
    @StrictTimeout(1)
    void testAufgabe1_lineCount2()
    {
        helper_lineCount();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe1_exactMatch2()
    {
        helper_exaktMatch();
    }


    @Test
    @StrictTimeout(1)
    void testAufgabe1_lineCount3()
    {
        helper_lineCount();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe1_exactMatch3()
    {
        helper_exaktMatch();
    }
}

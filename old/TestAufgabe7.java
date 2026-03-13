package src.x09p03;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static de.tum.in.test.api.util.ReflectionTestUtils.*;
import static org.junit.jupiter.api.Assertions.fail;

import de.tum.in.test.api.BlacklistPath;
import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistPath;
import de.tum.in.test.api.jupiter.Public;

@Public
@WhitelistPath("target") // mainly for Artemis
@BlacklistPath("target/test-classes") // prevent access to test-related classes and resources

public class TestAufgabe7
{
    private String[] outLines;
    private String[] expectedLines;

    private PrintStream originalOut;

    @BeforeEach
    void setup() throws ClassNotFoundException
    {
        originalOut = System.out;
        Random r = new Random();
        int limit = r.nextInt(10000)+64;
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
        invokeMethod(myClass, getMethod(myClass, "aufgabe7",int.class),limit);

        outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void runExpectedMethod(int limit)
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        src.x09p03.AufgabeMock.aufgabe7(limit);

        expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void helper_exactMatch()
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
    void testAufgabe7_exactMatch1()
    {
        helper_exactMatch();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe7_exactMatch2()
    {
        helper_exactMatch();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe7_exactMatch3()
    {
        helper_exactMatch();
    }
}

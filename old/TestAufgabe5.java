package src.x09p03;

import de.tum.in.test.api.BlacklistPath;
import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistPath;
import de.tum.in.test.api.jupiter.Public;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
public class TestAufgabe5
{
    private String[] outLines;
    private String[] expectedLines;

    private PrintStream originalOut;

    private static int[] numbers;
    private static int counter;

    @BeforeAll
    static void staticSetup()
    {
        Random r = new Random();
        counter = 0;
        numbers = new int[3];

        numbers[0] = 9;
        numbers[1] = r.nextInt(100)+9;
        numbers[2] = r.nextInt(60)+9;

    }

    @BeforeEach
    void setup() throws ClassNotFoundException
    {
        originalOut = System.out;
        runMethodUnderTest(numbers[counter]);
        runExpectedMethod(numbers[counter]);
        counter++;
        counter %= 3;
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
        invokeMethod(myClass, getMethod(myClass, "aufgabe5",int.class),limit);

        outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void runExpectedMethod(int limit)
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        src.x09p03.AufgabeMock.aufgabe5(limit);

        expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    String helper_exaktMatch()
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
            return "Die Ausgabe deiner " + String.join(", ",errMsgBuilder) + " Zeile(n) stimmt nicht mit der erwarteten überein.";
        }
        else if(expectedLines.length != outLines.length)
        {
            return "Der Inhalt der Zeilen, die du ausgibst, ist korrekt, jedoch passt die Anzahl der Zeilen nicht (es fehlen Zeilen oder du gibst zu viele aus).";
        }
        return null;
    }

    void helperWrapper_exactMatch()
    {
        String errMsg1 = helper_exaktMatch();
        if(errMsg1 != null)
        {
            String[] newArray = new String[expectedLines.length - 1];
            System.arraycopy(expectedLines, 1, newArray, 0, expectedLines.length - 1);
            expectedLines = newArray;

            String errMsg2 = helper_exaktMatch();

            if(errMsg2 != null)
            {
                fail(errMsg1);
            }
        }
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe5_exactMatch1()
    {
        helperWrapper_exactMatch();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe5_exactMatch2()
    {
        helperWrapper_exactMatch();
    }

    @Test
    @StrictTimeout(1)
    void testAufgabe5_exactMatch3()
    {
        helperWrapper_exactMatch();
    }
}

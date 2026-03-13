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
class TestAufgabe3
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
        numbers = new int[6];

        numbers[0] = r.nextInt(2000);
        numbers[1] = -r.nextInt(2000);

        numbers[2] = r.nextInt(2000)+1000;
        numbers[3] = r.nextInt(999);

        numbers[4] = -r.nextInt(100);
        numbers[5] = -r.nextInt(2000)-101;

    }

    @BeforeEach
    void setup() throws ClassNotFoundException
    {
        originalOut = System.out;
        runMethodUnderTest(numbers[counter],numbers[counter+1]);
        runExpectedMethod(numbers[counter],numbers[counter+1]);
        counter+=2;
        counter %= 6;
    }

    @AfterEach
    void cleanup()
    {
        System.setOut(originalOut);
    }

    void runMethodUnderTest(int start, int ende) throws ClassNotFoundException
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Class<?> myClass = Class.forName("src.x09p03.Aufgaben");
        invokeMethod(myClass, getMethod(myClass, "aufgabe3",int.class,int.class),start,ende);

        outLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }

    void runExpectedMethod(int start, int ende)
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        src.x09p03.AufgabeMock.aufgabe3(start,ende);

        expectedLines = Arrays.stream(outContent.toString().split(System.lineSeparator())).filter(x->!x.equals("")).toArray(String[]::new);
    }




    void helper_lineCount()
    {
        if(expectedLines.length != outLines.length)
        {
            fail("Deine Ausgabe hat "+ outLines.length +" Zeilen, erwartet wurden " + expectedLines.length + " Zeilen.");
        }
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
    void testAufgabe3_lineCount1()
    {
        helper_lineCount();
    }
    @Test
    @StrictTimeout(1)
    void testAufgabe3_exactMatch1()
    {
        helper_exactMatch();
    }
    @Test
    @StrictTimeout(1)
    void testAufgabe3_lineCount2()
    {
        helper_lineCount();
    }
    @Test
    @StrictTimeout(1)
    void testAufgabe3_exactMatch2()
    {
        helper_exactMatch();
    }
    @Test
    @StrictTimeout(1)
    void testAufgabe3_lineCount3()
    {
        helper_lineCount();
    }
    @Test
    @StrictTimeout(1)
    void testAufgabe3_exactMatch3()
    {
        helper_exactMatch();
    }
}

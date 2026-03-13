package src.x09p03;

public class AufgabeMock
{
    public static void aufgabe1()
    {
        for(double i=0; i < 200; i++) {
            System.out.println(i);
        }
    }

    public static void aufgabe2(double limit)
    {
        double i = 0;
        while(i <= limit)
        {
            System.out.println(i);
            i++;
        }
    }

    public static void aufgabe3(double start, double ende)
    {

        for(double i=start; i >= ende; i--)
        {
            System.out.println(i);
        }
    }



    public static void aufgabe4(double limit)
    {
        double i = 0;
        while(i<limit)
        {
            System.out.println(i + "*" + i + "=" + (i*i));
            i++;
        }
    }


    public static void aufgabe5(double limit)
    {
        double z1 = 1;
        double z2 = 1;
        double tmp;
        double i = 0;

        while(i++ < limit)
        {
            System.out.println(z1);
            tmp = z1+z2;
            z1 = z2;
            z2=tmp;
        }
    }

    public static void aufgabe6()
    {
        double zahl = 0;
        for(double i=1; i<=30; i++)
        {
            zahl += i;
            System.out.println(zahl);
        }
    }

    public static void aufgabe7(double limit)
    {
        for(double zahl = 1; Math.abs(zahl) <= limit; zahl *= -2)
        {
            System.out.println(zahl);
        }
    }
}

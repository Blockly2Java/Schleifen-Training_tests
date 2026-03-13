package wrappers;

import levenshtein.*;

public class MainWrapper<T> extends ClassWrapper<T>
{
    private final MethodWrapper<T, ?> aufgabe1;
    private final MethodWrapper<T, ?> aufgabe2;
    private final MethodWrapper<T, ?> aufgabe3;
    private final MethodWrapper<T, ?> aufgabe4;
    private final MethodWrapper<T, ?> aufgabe5;


    public MainWrapper()
    {
        super(
            "Main",
            "", 
            "public"
        );

        aufgabe1 = new MethodWrapper<>(
            this,
            "aufgabe1",
            void.class,
            "public", "static"
        );

        aufgabe2 = new MethodWrapper<>(
            this,
            "aufgabe2",
            void.class,
            new Class<?>[] {double.class},
            "public", "static"
        );

        aufgabe3 = new MethodWrapper<>(
                this,
                "aufgabe3",
                void.class,
                new Class<?>[] {double.class, double.class},
                "public", "static"
        );

        aufgabe4 = new MethodWrapper<>(
                this,
                "aufgabe4",
                void.class,
                new Class<?>[] {double.class},
                "public", "static"
        );

        aufgabe5 = new MethodWrapper<>(
                this,
                "aufgabe5",
                void.class,
                new Class<?>[] {double.class},
                "public", "static"
        );


        
    }

    @Override
    public Object getObj(boolean forceNew, boolean useByteBuddy) {
        return null;
    }

    public MethodWrapper<T, ?> aufgabe1() {
        return aufgabe1;
    }
    public MethodWrapper<T, ?> aufgabe2() {
        return aufgabe2;
    }
    public MethodWrapper<T, ?> aufgabe3() {
        return aufgabe3;
    }
    public MethodWrapper<T, ?> aufgabe4() {
        return aufgabe4;
    }
    public MethodWrapper<T, ?> aufgabe5() {
        return aufgabe5;
    }

}

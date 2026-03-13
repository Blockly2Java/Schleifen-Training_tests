package test;

import wrappers.*;
import levenshtein.*;


import static levenshtein.StructuralLevenshtein.DetailLevel.ONE_PER_CLASS;
import static levenshtein.StructuralLevenshtein.structuralTestFactory;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import levenshtein.LevenshteinTest;

import java.util.List;



@LevenshteinTest
public class TestManager {

    static MainWrapper<?> mainClz;

    public static MainWrapper<?> mainClz() {
        return mainClz;
    }

    @BeforeAll
    static void beforeAll() {
        mainClz = new MainWrapper<>();
    }

    void testCompilationAndSetup() {
        assertThat(mainClz).isNotNull();
        assertThat(mainClz).isInstanceOf(MainWrapper.class);

    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        testCompilationAndSetup();
        return structuralTestFactory(
            ONE_PER_CLASS,
            mainClz
        );
    }

    @Test
    void Aufgabe1_ZeilenAnzahl() {
        try {
            Tests.Aufgabe1_ZeilenAnzahl();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }






}    


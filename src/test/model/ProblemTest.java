package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProblemTest {
    private Problem p1;

    @BeforeEach
    public void runBefore() {
         p1 = new Problem();
    }

    @Test
    public void testConstructor() {
        assertTrue(p1.getN1() < 251);
        assertTrue(p1.getN1() > 0);

        assertTrue(p1.getN2() < 251);
        assertTrue(p1.getN2() > 0);
    }

    @Test
    public void testGetOperation() {
        String operation = p1.getOperation();
        assertTrue(operation.equals("+") || operation.equals("-") ||
                operation.equals("*"));

    }


    @Test
    public void testGetResult() {
        assertTrue(p1.getN1()+p1.getN2() == p1.getResult()||p1.getN1()-p1.getN2() == p1.getResult()||
                p1.getN1()*p1.getN2() == p1.getResult());
    }

    @Test
    public void testGetResultAddition() {
        p1.setOperation("+");
        assertEquals(p1.getResult(), p1.getN1()+p1.getN2());
    }

    @Test
    public void testGetResultSub() {
        p1.setOperation("-");
        assertEquals(p1.getResult(), p1.getN1()-p1.getN2());
    }

    @Test
    public void testGetResultMult() {
        p1.setOperation("*");
        assertEquals(p1.getResult(), p1.getN1()*p1.getN2());
    }

    @Test
    public void testGetQuestion() {
        p1.setOperation("+");
        assertEquals(p1.getQuestion(), p1.getN1() + "+" + p1.getN2());
        p1.setOperation("-");
        assertEquals(p1.getQuestion(), p1.getN1() + "-" + p1.getN2());
        p1.setOperation("*");
        assertEquals(p1.getQuestion(), p1.getN1() + "*" + p1.getN2());
    }
}

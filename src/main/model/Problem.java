package model;

import java.util.Random;

// a math problem with two operands and an operator, which corresponds to an operation
public class Problem {
    private static final int MIN_VALUE = 2;
    private static final int MAX_VALUE = 20;

    private int n1;
    private int n2;
    private int correctAns;
    private String operation;


    // EFFECTS: makes a problem with two random operands, with a MAX/MIN_VALUE, an operation,
    // and result
    public Problem() {
        n1 = new Random().nextInt(MIN_VALUE + MAX_VALUE);
        n2 = new Random().nextInt(MIN_VALUE + MAX_VALUE);
        operation = getOperation();
        EventLog.getInstance().logEvent(new Event("New Question"));
    }

    //getters + setters

    // MODIFIES: this
    // EFFECTS: assigns the operator value an operation based off a number key and result
    public String getOperation() {
        int key = getKey();
        if (key == 1) {
            operation = "+";
        } else if (key == 2) {
            operation = "-";
        } else {
            operation = "*";
        }
        return operation;
    }

    //REQUIRES: all other fields of problem must be valid
    //EFFECTS: returns solution to problem based off operation and operands
    public int getResult() {
        int result = 0;
        if (operation.equals("+")) {
            result = n1 + n2;

        } else if (operation.equals("-")) {
            result = n1 - n2;

        } else {
            result = n1 * n2;
        }
        return result;
    }

    // EFFECTS: returns the question in a complete string format
    public String getQuestion() {
        correctAns = this.getResult();
        return (this.getN1() + operation + this.getN2());
    }

    //SIMPLE getters + setters

    // EFFECTS: returns an integer key (1, 2, or 3), that correlates with an operation
    public int getKey() {
        return new Random().nextInt(3) + 1;
    }

    // EFFECTS: return first operand of problem
    public int getN1() {
        return n1;
    }

    // EFFECTS: return second operand of problem
    public int getN2() {
        return n2;
    }

    //for testing purposes, sets the operation
    public void setOperation(String operation) {
        this.operation = operation;
    }
}

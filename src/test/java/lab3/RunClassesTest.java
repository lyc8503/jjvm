package lab3;

import lab2.TestUtils;
import org.junit.jupiter.api.Test;

public class RunClassesTest {

    @Test
    public void runInstance() {
        TestUtils.runClass("lab3.Instance");
    }

    @Test
    public void runInstanceWithFields() {
        TestUtils.runClass("lab3.InstanceWithFields");
    }

    @Test
    public void runLinkedList() {
        TestUtils.runClass("lab3.LinkedList");
    }

    @Test
    public void runStaticFields() {
        TestUtils.runClass("lab3.StaticFields");
    }

}
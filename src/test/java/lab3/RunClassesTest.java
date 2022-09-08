package lab3;

import util.TestUtils;
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

    @Test
    public void runPolymorphism() {
        TestUtils.runClass("lab3.Polymorphism");
    }

    @Test
    public void runInheritance() {
        TestUtils.runClass("lab3.Inheritance");
    }

    @Test
    public void runOverride() {
        TestUtils.runClass("lab3.Override");
    }

    @Test
    public void runArray() {
        TestUtils.runClass("lab3.Array");
    }

    @Test
    public void runBubbleSort() {
        TestUtils.runClass("lab3.BubbleSort");
    }

//    @Test
//    public void runStringTest() {
//        TestUtils.runClass("lab3.StringTest");
//    }

}

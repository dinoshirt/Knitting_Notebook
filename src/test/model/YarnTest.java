package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YarnTest {

    private Yarns testYarn;


    @BeforeEach
    public void runBefore() {
        testYarn = new Yarns();
    }

    @Test
    public void newYarnsEmpty() {
        assertEquals(0, testYarn.getSupplies().size());
    }

    @Test
    public void addOneYarn() {
        testYarn.addSupply("blue yarn");
        assertEquals(1, testYarn.getSupplies().size());
        assertEquals("blue yarn", testYarn.getSupplies().get(0));
    }

    @Test
    public void addManyYarns() {
        for(int i = 0; i < 10; i++) {
            testYarn.addSupply("yarn number " + i);
        }
        assertEquals(10, testYarn.getSupplies().size());
        assertEquals("yarn number 3", testYarn.getSupplies().get(3));

        for(int i = 0; i < 10; i++) {
            testYarn.addSupply("yarn number " + (i+10));
        }

        assertEquals(20, testYarn.getSupplies().size());
        assertEquals("yarn number 14", testYarn.getSupplies().get(14));
    }

    @Test
    public void removeFirstYarn() {
        addManyYarns();
        testYarn.removeSupply("yarn number 0");

        assertEquals(19, testYarn.getSupplies().size());
        assertEquals("yarn number 1", testYarn.getSupplies().get(0));
    }

    @Test
    public void removeLastYarn() {
        addManyYarns();
        testYarn.removeSupply("yarn number 19");

        assertEquals(19, testYarn.getSupplies().size());
        assertEquals("yarn number 18", testYarn.getSupplies().get(18));
    }

    @Test
    public void removeMiddleYarn() {
        addManyYarns();
        testYarn.removeSupply("yarn number 10");

        assertEquals(19, testYarn.getSupplies().size());
        assertEquals("yarn number 11", testYarn.getSupplies().get(10));
    }



}
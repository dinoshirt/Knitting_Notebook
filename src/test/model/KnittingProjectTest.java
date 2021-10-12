package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnittingProjectTest {

    private KnittingProject newKnittingProject;
    private String projectName = "First Project";

    @BeforeEach
    public void runBefore() {
        newKnittingProject = new KnittingProject(projectName);
    }

    @Test
    public void addOneItem() {
        newKnittingProject.addItem("Yarn 1", "Yarn");

        assertEquals("Yarn 1", newKnittingProject.getYarnName().get(0));
        assertEquals(1, newKnittingProject.getYarnName().size());

        newKnittingProject.addItem("Needle 1", "Needle");

        assertEquals("Needle 1", newKnittingProject.getNeedleNames().get(0));
        assertEquals(1, newKnittingProject.getNeedleNames().size());
    }
    @Test
    public void addMultipleItems() {
        newKnittingProject.addItem("Yarn 1", "Yarn");
        newKnittingProject.addItem("Yarn 2", "Yarn");
        newKnittingProject.addItem("Yarn 3", "Yarn");

        assertEquals(3, newKnittingProject.getYarnName().size());
        assertEquals("Yarn 3", newKnittingProject.getYarnName().get(2));

        newKnittingProject.addItem("Needle 1", "Needle");
        newKnittingProject.addItem("Needle 2", "Needle");
        newKnittingProject.addItem("Needle 3", "Needle");

        assertEquals(3, newKnittingProject.getNeedleNames().size());
        assertEquals("Needle 3", newKnittingProject.getNeedleNames().get(2));

    }

}
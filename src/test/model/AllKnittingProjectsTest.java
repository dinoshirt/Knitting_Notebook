package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllKnittingProjectsTest {

    private AllKnittingProjects testKnittingProjects;

    @BeforeEach
    public void runBefore() {
        testKnittingProjects = new AllKnittingProjects();
    }

    @Test
    public void addOneKnittingProject() {
        KnittingProject newKnittingProject = new KnittingProject("project 1");
        newKnittingProject.addItem("blue yarn", "Yarn");
        newKnittingProject.addItem("10mm", "Needle");
        KnittingProject gottenProject;

        assertEquals(0, testKnittingProjects.getAllKnittingProjects().size());
        gottenProject = testKnittingProjects.getKnittingProject("project 1");
        //assertEquals(new KnittingProject("dummy"), gottenProject);
        assertEquals("dummy", gottenProject.getProjectName());
        assertEquals(0, gottenProject.getYarnName().size());
        assertEquals(0, gottenProject.getNeedleNames().size());

        testKnittingProjects.addKnittingProject(newKnittingProject);
        assertEquals(1, testKnittingProjects.getAllKnittingProjects().size());
        assertEquals(newKnittingProject, testKnittingProjects.getAllKnittingProjects().get(0));
        gottenProject = testKnittingProjects.getKnittingProject("project 1");
        //assertEquals(new KnittingProject("project 1"), gottenProject);
        assertEquals("project 1", gottenProject.getProjectName());
        assertEquals(1, gottenProject.getYarnName().size());
        assertEquals("blue yarn", gottenProject.getYarnName().get(0));
        assertEquals(1, gottenProject.getNeedleNames().size());
        assertEquals("10mm", gottenProject.getNeedleNames().get(0));
    }

    @Test
    public void addManyKniitingProjects() {
        for(int i = 0; i < 10; i++) {
            testKnittingProjects.addKnittingProject(new KnittingProject("project " + i));
        }
        assertEquals(10, testKnittingProjects.getAllKnittingProjects().size());
        KnittingProject project11 = new KnittingProject("project eleven");
        project11.addItem("blue yarn", "Yarn");
        project11.addItem("10mm", "Needle");

        testKnittingProjects.addKnittingProject(project11);

        for(int i = 0; i < 9; i++) {
            testKnittingProjects.addKnittingProject(new KnittingProject("project " + (i + 11)));
        }
        assertEquals(20,testKnittingProjects.getAllKnittingProjects().size());
        assertEquals(project11, testKnittingProjects.getAllKnittingProjects().get(10));

        KnittingProject gottenProject;
        gottenProject = testKnittingProjects.getAllKnittingProjects().get(10);
        assertEquals("project eleven", gottenProject.getProjectName());
        assertEquals(1, gottenProject.getYarnName().size());
        assertEquals("blue yarn", gottenProject.getYarnName().get(0));
        assertEquals(1, gottenProject.getNeedleNames().size());
        assertEquals("10mm", gottenProject.getNeedleNames().get(0));

    }

}
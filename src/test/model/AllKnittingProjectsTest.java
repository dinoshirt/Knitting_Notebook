package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        newKnittingProject.getYarns().add("blue yarn");
        newKnittingProject.getNeedles().add("10mm");
        assertEquals(0, testKnittingProjects.getAllKnittingProjects().size());

        testKnittingProjects.addKnittingProject(newKnittingProject);
        assertEquals(1, testKnittingProjects.getAllKnittingProjects().size());
        assertEquals(newKnittingProject, testKnittingProjects.getAllKnittingProjects().get(0));
    }

    @Test
    public void listOneKnittingProject() {
        List<String> listedProjects;
        listedProjects = testKnittingProjects.listProjectNames();
        assertEquals(0, listedProjects.size());
        addOneKnittingProject();
        listedProjects = testKnittingProjects.listProjectNames();
        assertEquals(1, listedProjects.size());
        assertEquals("project 1", listedProjects.get(0));
    }

    @Test
    public void getDummyKnittingProject() {
        addOneKnittingProject();

        KnittingProject gottenProject;
        ArrayList<String> dummyYarn = new ArrayList<>();
        ArrayList<String> dummyNeedle = new ArrayList<>();

        gottenProject = testKnittingProjects.getKnittingProject("new project");
        dummyYarn.add("dummy yarn");
        dummyNeedle.add("dummy needles");

        assertEquals("dummy", gottenProject.getProjectName());
        assertEquals(dummyYarn, gottenProject.getYarns());
        assertEquals(dummyNeedle, gottenProject.getNeedles());
        assertEquals(1, gottenProject.getYarns().size());
        assertEquals(1, gottenProject.getNeedles().size());
    }

    @Test
    public void getKnittingProjectOneProjectLongList() {
        addOneKnittingProject();

        KnittingProject gottenProject;
        gottenProject = testKnittingProjects.getKnittingProject("project 1");

        assertEquals("project 1", gottenProject.getProjectName());
        assertEquals(1, gottenProject.getYarns().size());
        assertEquals("blue yarn", gottenProject.getYarns().get(0));
        assertEquals(1, gottenProject.getNeedles().size());
        assertEquals("10mm", gottenProject.getNeedles().get(0));
    }

    @Test
    public void addManyKnittingProjects() {
        for(int i = 0; i < 10; i++) {
            testKnittingProjects.addKnittingProject(new KnittingProject("project " + i));
        }
        assertEquals(10, testKnittingProjects.getAllKnittingProjects().size());
        KnittingProject project11 = new KnittingProject("project eleven");
        project11.getYarns().add("blue yarn");
        project11.getNeedles().add("10mm");

        testKnittingProjects.addKnittingProject(project11);

        for(int i = 0; i < 9; i++) {
            testKnittingProjects.addKnittingProject(new KnittingProject("project " + (i + 11)));
        }
        assertEquals(20,testKnittingProjects.getAllKnittingProjects().size());
        assertEquals(project11, testKnittingProjects.getAllKnittingProjects().get(10));

    }

    @Test
    public void listManyKnittingProject() {
        addManyKnittingProjects();
        List<String> listedProjects;
        listedProjects = testKnittingProjects.listProjectNames();
        assertEquals(20, listedProjects.size());
        assertEquals("project eleven", listedProjects.get(10));
    }

    @Test
    public void getKnittingProjectInMiddleOfLongList() {
        //initialize a list of knitting projects that is 20 long
        addManyKnittingProjects();

        KnittingProject gottenProject;
        gottenProject = testKnittingProjects.getKnittingProject("project eleven");
        assertEquals("project eleven", gottenProject.getProjectName());
        assertEquals(1, gottenProject.getYarns().size());
        assertEquals("blue yarn", gottenProject.getYarns().get(0));
        assertEquals(1, gottenProject.getNeedles().size());
        assertEquals("10mm", gottenProject.getNeedles().get(0));

    }

}
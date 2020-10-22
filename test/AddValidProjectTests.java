import model.Model;
import model.ProjectsComboboxModel;
import model.data.AbstractProjectTask;
import model.data.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeModel;
import java.util.Calendar;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AddValidProjectTests {
    /*
     * Given that the model has no entrees attached to the tree node
     * When adding the project ("English Assignment" "Shakespeare Essay" "No parent" "20th October 2020")
     * This project is attached to the root node
     */
    @Test
    public void AddValidProject() {
        Model model = new Model();
        final Calendar date = Calendar.getInstance();
        model.addProject("English Assignment", "Shakespeare Essay", null, date);
        date.set(2020, Calendar.OCTOBER, 20);
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        Assertions.assertEquals(1, root.getChildCount());
        assertEquals(1, treeModel.getChildCount(root));
        Optional<AbstractProjectTask> project = root.getChild("English Assignment");
        assertTrue(project.isPresent());
        Project engProject = (Project) project.get();
        assertTrue(treeModel.isLeaf(engProject));
    }

    /*
    Given that this model has no entrees attached to it
    And I add "English Project" with description "Poetry Essay" to the root with a Due Date "25/10/2020"
    And I add "Science Project" with description "Water Cycle Model" to the root with Due Date "31/10/2020"
    And I add task "Introduction" with description "Evaporation" to the Science Project with Due Date "24/10/2020"
    I expect the projects to show up on the Project tree with the correct names
    I expect the root to have three entrees attached, and one child task attached to Project "Science Project"
    I expect English Project and Introduction to be leaf nodes and Science Project not
     */

    @Test
    public void VerifyProjectTree() {
        final Model model = new Model();
        final Calendar date = Calendar.getInstance();
        final Calendar date2 = Calendar.getInstance();
        final Calendar date3 = Calendar.getInstance();
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addProject("English Project", "Poetry Essay", root, date);
        Project english = (Project) treeModel.getChild(root, 0);
        model.addProject("Science Project", "Water Cycle Model", root, date2);
        Project science = (Project) treeModel.getChild(root, 1);
        model.addProject("Introduction", "Evaporation", science, date3);
        date.set(2020, Calendar.OCTOBER, 25);
        date2.set(2020, Calendar.OCTOBER, 31);
        date3.set(2020, Calendar.OCTOBER, 24);
        Project intro = (Project) treeModel.getChild(science, 0);

        assertEquals(2, treeModel.getChildCount(root));
        assertEquals("English Project", english.getTitle());
        assertEquals("Science Project", science.getTitle());
        assertEquals("Introduction", intro.getTitle());
        assertEquals(0, treeModel.getChildCount(english));
        assertEquals(1, treeModel.getChildCount(science));
        assertEquals(intro, treeModel.getChild(science, 0));

        assertFalse(treeModel.isLeaf(root));
        assertTrue(treeModel.isLeaf(english));
        assertFalse(treeModel.isLeaf(science));
        assertTrue(treeModel.isLeaf(intro));

        assertEquals(0, treeModel.getIndexOfChild(science, intro));
        assertEquals(0, treeModel.getIndexOfChild(root, english));
    }
    /*
    Assuming the above has been entered using the project box
    The below is listed within the combo box
    The first listed in the combo box is the root
    And the rest is in alphabetical order
    */
    @Test
    public void VerifyParentComboBox() {
        final Model model = new Model();
        final Calendar date = Calendar.getInstance();
        final Calendar date2 = Calendar.getInstance();
        final Calendar date3 = Calendar.getInstance();
        final Calendar date4 = Calendar.getInstance();
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addProject("English Project", "Poetry Essay", root, date);
        Project english = (Project) treeModel.getChild(root, 0);
        model.addProject("Science Project", "Water Cycle Model", root, date2);
        Project science = (Project) treeModel.getChild(root, 1);
        model.addProject("Introduction", "Evaporation", science, date3);
        Project intro = (Project) treeModel.getChild(science, 0);
        model.addProject("Keats", "Write Poem", english, date4);
        Project poem = (Project) treeModel.getChild(english, 0);
        date.set(2020, Calendar.OCTOBER, 25);
        date2.set(2020, Calendar.OCTOBER, 31);
        date3.set(2020, Calendar.OCTOBER, 24);
        date4.set(2020, Calendar.OCTOBER, 24);

        ProjectsComboboxModel comboboxModel = new ProjectsComboboxModel(model);

        assertEquals(5, comboboxModel.getSize());
        assertSame(science, comboboxModel.getElementAt(4));
        assertSame(root, comboboxModel.getElementAt(0));
        assertSame(english, comboboxModel.getElementAt(1));
        assertSame(intro, comboboxModel.getElementAt(2));
        assertSame(poem, comboboxModel.getElementAt(3));
    }


}

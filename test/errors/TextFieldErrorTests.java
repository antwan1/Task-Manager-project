package errors;

import model.Model;
import model.data.Project;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeModel;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFieldErrorTests {

    /*
        Given I have a model with no entrees
        And I try to add a Project into the collection without a title
        Then an error should be displayed in the error panel
        Saying "No project title provided."
     */
    @Test
    public void NoProjectTitleError(){
        Model model = new Model();
        model.addProject("", "", null, Calendar.getInstance());
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("No project title provided.", model.errorMessages().getElementAt(0));
    }

    /*
        Given I am to add "English Project" to the root
        Adding no description to the English Project
        Should receive an error message in the error panel
        Saying "No project description provided."
     */
    @Test
    public void NoProjectDescriptionError(){
        Model model = new Model();
        model.addProject("English Project", "", null, Calendar.getInstance());
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("No project description provided.", model.errorMessages().getElementAt(0));
    }

    /*
        Given I add a project to the root titled "English Project"
        And I add a second project to the root titled "English project"
        Then an error message should be received in the error panel
        Saying "Project title already used."
     */
    @Test
    public void TwoProjectsWithTheSameTitleError(){
        Model model = new Model();
        model.addProject("English Project", "Empty", null, Calendar.getInstance());
        model.addProject("English Project", "Empty", null, Calendar.getInstance());
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("Project title already used.", model.errorMessages().getElementAt(0));
    }

    /*
        Given I have a model with no entrees
        And I try to add a Task into the collection without a title
        Then an error should be displayed in the error panel
        Saying "No task title provided."
    */
    @Test
    public void NoTaskTitleError(){
        Model model = new Model();
        model.addTask("", "", null, Calendar.getInstance(), 0);
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("No task title provided.", model.errorMessages().getElementAt(0));
    }

    /*
        Given I have a model with a parent Project "Clean House"
        And I try to add a Task "Tidy room" into the parent without a description
        Then an error should be displayed in the error panel
        Saying "No task description provided."
     */
    @Test
    public void NoTaskDescriptionError(){
        Model model = new Model();
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addProject("Clean House", "Clean House", root, Calendar.getInstance());
        Project chores = (Project) treeModel.getChild(root, 0);
        model.addTask("Tidy room", "", chores, Calendar.getInstance(), 0);
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("No task description provided.", model.errorMessages().getElementAt(0));
    }

    /*
        Given I have a model with a parent Project "Clean House"
        And I try to add a Task "Tidy room" when a task of the same name already exists
        Then an error should be displayed in the error panel
        Saying "Task title already used."
     */
    @Test
    public void TwoTitlesWithTheSameTitleError(){
        Model model = new Model();
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addProject("Clean House", "Clean House", root, Calendar.getInstance());
        Project chores = (Project) treeModel.getChild(root, 0);
        model.addTask("Tidy room", "Empty", chores, Calendar.getInstance(), 0);
        model.addTask("Tidy room", "Empty", chores, Calendar.getInstance(), 0);
        assertEquals(1, model.errorMessages().getSize());
        assertEquals("Task title already used.", model.errorMessages().getElementAt(0));
    }
}

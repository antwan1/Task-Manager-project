package data;

import model.Model;
import model.data.Project;
import model.data.Task;
import model.utility.Validation;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeModel;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TaskCreation {

   /*
   Assuming I have a project "English Project", "Shakespeare", no parent
   That when I add a task as a child with "Introduction", "100 words", english Project, "1/11/2020", Importance = 33
   That the above task is added with all information correct
   */

    @Test
    public void VerifyTaskCreation(){
        Model model = new Model();
        final Calendar date = Calendar.getInstance();
        TreeModel treeModel = model.getProjectTreeModel();
        Project root = (Project) treeModel.getRoot();
        model.addProject("English Project", "Shakespeare", null, Calendar.getInstance());
        Project english = (Project) treeModel.getChild(root, 0);
        Validation<Task> task = Task.create("Introduction", "100 words", english, date, 33);
        date.set(2020, Calendar.NOVEMBER, 1);
        assertEquals("Introduction", task.getObject().getTitle());
        assertEquals("100 words", task.getObject().getDescription());
        assertSame(date, task.getObject().getDueDate());
        // Importance function not been added so unable to claim importance
    }


}

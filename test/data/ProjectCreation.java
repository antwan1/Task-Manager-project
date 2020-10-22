package data;

import model.data.Project;
import model.utility.Validation;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectCreation {

    /*
        Given I have no Project in the tree model
        That when I create "English Project" with description "Shakespeare" and due date "1/11/2020"
        The Project Name is "English Project"
        And the description is "Shakespeare"
        And the Due Date is set to 1/11/2020
     */
    @Test
    public void VerifyProjectCreation(){
        final Calendar date = Calendar.getInstance();
        Validation<Project> project = Project.create("English Project", "Shakespeare", date);
        date.set(2020, Calendar.NOVEMBER, 1);
        assertEquals("English Project", project.getObject().getTitle());
        assertEquals("Shakespeare", project.getObject().getDescription());
        assertSame(date, project.getObject().getDueDate());
    }

}

package model.data;

import model.utility.Errors;
import model.utility.Validation;

import javax.swing.tree.TreeNode;
import java.util.Calendar;

/**
 * @author manmohansingh
 * 17/10/2020 14:20
 *
 * [1] (Thompson, 2020)
 */

public abstract class AbstractProjectTask implements TreeNode {
    protected final String title;
    protected final String description;
    protected final Project parent;
    protected final Calendar dueDate;

    public enum ProjectOrTask {
        PROJECT ("Project"), TASK("Task");

        String title;
        ProjectOrTask(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public AbstractProjectTask(String title, String description, Project parentProject, Calendar dueDate) {
        this.title = title.trim();
        this.description = description.trim();
        this.parent = parentProject;
        this.dueDate = dueDate;
        if(parent != null) {
            parent.add(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Project getParent() { return parent; }

    public Calendar getDueDate() { return dueDate; }

    static Errors verifyTitleAndDescription
            (String title, String description, ProjectOrTask projectOrTask, Calendar dueDate) {
        Errors errors = Errors.create(Validation.isPresent(title, "No " +
                projectOrTask.getTitle() + " name provided."));
        errors.add(Validation.isPresent(description, "No " + projectOrTask.name().toLowerCase()
                + " description provided."));
        if(dueDate == null) {
            errors.add("Due Date entered is invalid.");
        }
        return errors;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

}

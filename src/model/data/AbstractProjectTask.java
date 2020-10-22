package model.data;

import model.utility.Errors;
import model.utility.Validation;

import javax.swing.tree.TreeNode;
import java.util.Calendar;

/**
 * @author manmohansingh
 * 17/10/2020 14:20
 *
 * (Thompson, 2020)
 *
 * ********************************************************************
 * Title: TemperatureRecording
 * Author: Thompson, E (@thompel1)
 * Date: 2020
 * Code Version: N/A
 * Availability: https://gitlab.com/FoOOSD/temperaturerecording.git
 * ********************************************************************
 * [Source Code] https://gitlab.com/FoOOSD/temperaturerecording.git
 *
 */

public abstract class AbstractProjectTask implements TreeNode {
    protected final String title;
    protected final String description;
    protected final Project parent;
    protected final Calendar dueDate;
    private int importance;

    /**
     * Special "enum" class representing constants for Project and Task used to indicate which Branch/Node the
     * program is working with while adding specific component to the General tree.
     */

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

    /**
     * Constructor to initialise "this" class's variables to the specific inherited values.
     *
     * @param title to store the title (to Project or Task) entered by the user
     * @param description to store the description (to Project or Task) entered by the user
     * @param parentProject to the parent project selected by the user from the ComboBox
     * @param dueDate to store the date on which the project/task is due
     */
    public AbstractProjectTask(String title, String description, Project parentProject, Calendar dueDate) {
        this.title = title.trim();
        this.description = description.trim();
        this.parent = parentProject;
        this.dueDate = dueDate;
        if(parent != null) {
            parent.add(this);
        }
    }

    public AbstractProjectTask(String title, String description, Project parentProject, Calendar dueDate, int importance) {
        this.title = title.trim();
        this.description = description.trim();
        this.parent = parentProject;
        this.dueDate = dueDate;
        this.importance = importance;
        if(parent != null) {
            parent.add(this);
        }
    }

    /**
     * to extract the title for project or sub-project or task
     *
     * @return the project's / sub-project's / task's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * to extract the description for project or sub-project or task
     *
     * @return the project's / sub-project's / task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * to extract the parent Project for the sub-project or task
     *
     * @return the sub-project's / task's parent Project
     */
    public Project getParent() { return parent; }

    /**
     * to extract the due date for the task / (sub-)project
     *
     * @return the project's / sub-project's / task's due date
     */
    public Calendar getDueDate() { return dueDate; }

    public int getImportance() { return importance; }

    /**
     * this method verifies whether the user has entered title, description and due date in the fields
     *
     * @param title to be assigned to the project / sub-project / task
     * @param description to be assigned to the project / sub-project / task
     * @param projectOrTask indicates whether the data to be verified is for project or task
     * @param dueDate to be assigned to the project / sub-project / task
     * @return applicable error message or an empty string value
     */
    static Errors verifyTitleAndDescription
            (String title, String description, ProjectOrTask projectOrTask, Calendar dueDate) {
        Errors errors = Errors.create(Validation.isPresent(title, "No " +
                projectOrTask.getTitle() + " name provided."));
        errors.add(Validation.isPresent(description, "No " + projectOrTask.name().toLowerCase()
                + " description provided."));
        if(dueDate == null) {
            errors.add("Due Date entered is invalid.");
        }
        if(title != "ROOT" && dueDate.compareTo(Calendar.getInstance()) < 1) {
            errors.add("Due Date must be higher than Today's Date");
        }
        return errors;
    }

    /**
     * returns true if the caller allows children to the nodes of the tree
     *
     * @return true of it allows
     */
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    /**
     * deletes the selected node's children when selected and deletion is authorised.
     *
     * @param selectedItem is the item to be deleted.
     */
    public abstract boolean deleteChild(TreeNode selectedItem);

}

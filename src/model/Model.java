package model;

import model.data.AbstractProjectTask;
import model.data.Project;
import model.data.Task;
import model.utility.Errors;
import model.utility.Validation;
import view.ChangedTreeSelection;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author manmohansingh
 * 17/10/2020 10:15
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

public class Model {

    /* The project tree. The root node is not visible to the user other than in combo boxes for
   selecting a project. */
    private final Project rootProject =
            Project.create("ROOT", "Select a project", Calendar.getInstance()).getObject();
    /* This is the model required to make the JTree work */
    private final ProjectTreeModel projectTree = new ProjectTreeModel(rootProject);
    /* TreeMap Used to provide a list of projects in alphabetical order */
    private final TreeMap<String, Project> projects = new TreeMap<>();
    /* TreeMap Used to provide a list of tasks in alphabetical order */
    private final TreeMap<String, Task> tasks = new TreeMap<>();
    /* TreeMap Used to provide a list of titles in order to check for repetitions */
    private HashSet<String> titles = new HashSet<>();
    /* model to handle error messages */
    private DefaultListModel<String> errorModel = new DefaultListModel<>();
    private TreeNode selectedItem;
    private Vector<ChangedTreeSelection> selectionListeners = new Vector<>();

    public Model() {
        projects.put(" ", rootProject);
    }

    /**
     * This is the function used to obtain data to display in tree-format inside the ProjectTreePanel
     *
     * @return a TreeModel of the projects, sub-projects and tasks
     */
    public TreeModel getProjectTreeModel()  {
        return projectTree;
    }

    /**
     * a private function used to verify the uniqueness of title by accessing the function of validation
     *
     * @param title to be verified
     * @param projectOrTask indicates whether the title is for project or task
     * @return true if any error is found, false if the title is unique and is not left empty by the user
     */
    private boolean verifyUniqueTitle(String title, AbstractProjectTask.ProjectOrTask projectOrTask) {
        Errors errors = new Errors(Validation.isPresent(title,
                "No "+ projectOrTask.name().toLowerCase() + " title provided."));
        if(!errors.isEmpty()) {
            errorModel.addAll(errors);
            return true;
        }
        if(titles.contains(title)) {
            errorModel.add(0, projectOrTask.getTitle() + " title already used.");
            return true;
        }
        return false;
    }

    /**
     * the following function is accessed by the Project class in order to validate the data entered by the
     * user inside the project details panel and adding it to the tree, combobox (for parent selection) and the
     * title is stored inside the hashmap for titles to preserve it's uniqueness and to keep the user
     * from using the same title in the future data logs.
     *
     * if any errors any found while validation is carried out, nothing is entered into the tree
     * and the specific error message is added to the errorModel to be later displayed inside the
     * error panel.
     *
     * @param title of the project
     * @param description of the project
     * @param parent of the project if any
     * @param dueDate is the date on which the project / sub-project is due
     */
    public void addProject(String title, String description, Project parent, Calendar dueDate) {
        errorModel.clear();
        if (verifyUniqueTitle(title, AbstractProjectTask.ProjectOrTask.PROJECT)) return;
        Validation<Project> projectValidation = Project.create(title, description,
                parent == null ? rootProject : parent, dueDate);
        if(projectValidation.isPresent()) {
            final Project project = projectValidation.getObject();
            projects.put(project.getTitle(), project);
            titles.add(project.getTitle());
            projectTree.fireTreeNodeAdded((TreeNode) project);
        } else {
            errorModel.addAll(projectValidation.getErrorMessage());
        }
    }

    /**
     * the following function is accessed by the Task class in order to validate the data entered by the
     * user inside the task details panel and adding it to the tree, the task combo box and the
     * title is stored inside the hashmap for titles to preserve it's uniqueness and to keep the user
     * from using the same title in the future data logs.
     *
     * if any errors any found while validation is carried out, nothing is entered into the tree
     * and the specific error message is added to the errorModel to be later displayed inside the
     * error panel.
     *
     * @param title of the task
     * @param description of the task
     * @param parent project of the task
     * @param dueDate on which the task is due
     */
    public void addTask(String title, String description, Project parent, Calendar dueDate, int importance) {
        errorModel.clear();
        if(verifyUniqueTitle(title, AbstractProjectTask.ProjectOrTask.TASK)) return;
        Validation<Task> taskValidation = Task.create(title, description, parent, dueDate, importance);
        if(taskValidation.isPresent()) {
            final Task task = taskValidation.getObject();
            tasks.put(task.getTitle(), task);
            titles.add(task.getTitle());
            projectTree.fireTreeNodeAdded(task);
        }
        else {
            errorModel.addAll(taskValidation.getErrorMessage());
        }
    }

    /**
     * Returns a reference to the project tree
     *
     * @return a reference to the project tree
     */
    public TreeMap<String, Project> getProjects() {
        return projects;
    }

    /**
     * returns a reference to the project combo model for the list of projects.
     *
     * @return a reference to the project combo model for the list of projects.
     */
    public ComboBoxModel<Project> getProjectComboBoxModel() {
        return new ProjectsComboboxModel(this);
    }

    /**
     * returns a reference to the task combo model for the list of tasks.
     *
     * @return a reference to the task combo model for the list of tasks.
     */
    public ComboBoxModel<Task> getTaskComboBoxModel(){ return new TasksComboboxModel(this); }

    /**
     * Returns a reference to the list of tasks.
     *
     * @return a reference to the list of tasks.
     */
    Task[] getTasks() {
        return tasks.values().toArray(new Task[0]);
    }

    /**
     * Returns a reference to the error list model.
     *
     * @return a reference to the error list model.
     */
    public ListModel<String> errorMessages() {
        return errorModel;
    }

    /**
     * Accessed when the tree selection is changed.
     *
     * @param selectedItem stores the object reference if any item is selected, otherwise null.
     */
    public void setSelection(TreeNode selectedItem) {
        this.selectedItem = selectedItem;
        for (ChangedTreeSelection treeModeListener : selectionListeners) {
            treeModeListener.treeSelectionChange(selectedItem);
        }
    }

    public TreeNode getSelection() {
        return selectedItem;
    }

    public void addSelectionListener(ChangedTreeSelection listener) {
        selectionListeners.add(listener);
    }

    public boolean deleteTreeEntry() {
        AbstractProjectTask parent = (AbstractProjectTask) selectedItem.getParent();
        final boolean accepted = parent.deleteChild(selectedItem);
        projectTree.fireTreeNodeAdded(parent);
        return accepted;
    }

    public void modifyProject(String title, String description, Project parent, Calendar dueDate) {
    }
}

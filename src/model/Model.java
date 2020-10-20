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
 * [1] (Thompson, 2020)
 */

public class Model {

    public static final String[] IMP_SELECT = new String[]{"High", "Medium", "Low"};
    private final Project rootProject =
            Project.create("ROOT", "Select a project", Calendar.getInstance()).getObject();
    private final ProjectTreeModel projectTree = new ProjectTreeModel(rootProject);
    private final TreeMap<String, Project> projects = new TreeMap<>();
    private final TreeMap<String, Task> tasks = new TreeMap<>();
    private HashSet<String> titles = new HashSet<>();
    private DefaultListModel<String> errorModel = new DefaultListModel<>();
    private TreeNode selectedItem;
    private Vector<ChangedTreeSelection> selectionListeners = new Vector<>();

    public Model() {
        projects.put(" ", rootProject);
    }

    public TreeModel getProjectTreeModel()  {
        return projectTree;
    }

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

    public void addTask(String title, String description, Project parent, Calendar dueDate) {
        errorModel.clear();
        if(verifyUniqueTitle(title, AbstractProjectTask.ProjectOrTask.TASK)) return;
        Validation<Task> taskValidation = Task.create(title, description, parent, dueDate);
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

    public TreeMap<String, Project> getProjects() {
        return projects;
    }

    public ComboBoxModel<Project> getProjectComboBoxModel() {
        return new ProjectsComboboxModel(this);
    }

    public ComboBoxModel<Task> getTaskComboBoxModel(){ return new TasksComboboxModel(this); }

    Task[] getTasks() {
        return tasks.values().toArray(new Task[0]);
    }

    public ListModel<String> errorMessages() {
        return errorModel;
    }

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

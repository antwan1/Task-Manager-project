package model.data;

import jdk.jfr.Description;
import model.utility.Errors;
import model.utility.Validation;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import javax.swing.tree.TreeNode;

/**
 * @author manmohansingh
 * 17/10/2020 12:28
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

public class Project extends AbstractProjectTask implements Comparable<Project> {

    private final HashMap<String, AbstractProjectTask> children = new HashMap<>();

    private Project(String title, String description, Project parent, Calendar dueDate) {
        super(title, description, parent, dueDate);
    }

    public Project(String title, String description, Calendar dueDate) {
        super(title, description, null, dueDate);
    }

    void add(AbstractProjectTask project) {
        children.put(project.getTitle(), project);
    }

    public Optional<AbstractProjectTask> getChild(String title) {
        return Optional.ofNullable(children.get(title));
    }

    @Override
    public int compareTo(Project p) {
        return "ROOT".equals(getTitle()) ? -1 :
                "ROOT".equals(p.getTitle()) ? 1 : getTitle().compareTo(p.getTitle());
    }

    @Override
    public String toString() {
        return ("ROOT".equals(getTitle()) ? "" : getTitle() + ": ") + getDescription() +
                (getDueDate().compareTo(Calendar.getInstance()) < 1
                        ? "" : " (Due on " +
                        DateFormat.getDateInstance(DateFormat.MEDIUM).format(dueDate.getTime()) + ")");
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return (TreeNode) children.values().toArray()[childIndex];
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public boolean deleteChild(TreeNode selectedItem) {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public int getIndex(TreeNode node) {
        Object[] projectArray = children.values().toArray();
        for (int i = 0; i < children.size(); i++) {
            if (node == projectArray[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children.values());
    }

    public static Validation<Project> create(String title, String description, Calendar dueDate) {
        Errors errorMessage = verifyTitleAndDescription(title, description, ProjectOrTask.PROJECT, dueDate);
        if (errorMessage.size() != 0) {
            return new Validation<>(errorMessage);
        }
        return new Validation<>(new Project(title, description, dueDate));
    }

    public static Validation<Project> create
            (String title, String description, Project parent, Calendar dueDate) {
        Errors errorMessage = verifyTitleAndDescription(title, description, ProjectOrTask.PROJECT, dueDate);
        errorMessage.add(parent == null ? "No parent project provided" : null);
        if(dueDate.equals(""))
            errorMessage.add("No due Date entered.");
        if ((parent.getDueDate()).compareTo(dueDate) < 1 &&
                ((parent.getDueDate()).compareTo(Calendar.getInstance()) == 1)) {
            errorMessage.add("Due date cannot be higher than Parent Project.");
        }
        if(dueDate.compareTo(Calendar.getInstance()) < 1)
            errorMessage.add("Due Date cannot be less than Today's Date.");
        if (errorMessage.size() != 0) {
            return new Validation<>(errorMessage);
        }
        return new Validation<>(new Project(title, description, parent, dueDate));
    }
}






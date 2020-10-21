package model.data;

import model.utility.Errors;
import model.utility.Validation;

import javax.swing.tree.TreeNode;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;

/**
 * @author manmohansingh
 * 17/10/2020 13:15
 *
 * [1] (Thompson, 2020)
 */

public class Task extends AbstractProjectTask implements Comparable<Task>{

    private Task(String title, String description, Project project, Calendar dueDate) {
        super(title, description, project, dueDate);
    }

    @Override
    public int compareTo(Task t) {
        return getTitle().compareTo(t.getTitle());
    }

    @Override
    public String toString() {
        String temp = getDueDate().getTime().toString();
        return String.format(getTitle() + ": " + getDescription() +
                " (Due on " + temp.substring(4, 10) + temp.substring(temp.lastIndexOf(" ")) + ")");
    }

    @Override
    public boolean deleteChild(TreeNode selectedItem) {
        return false;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    public static Validation<Task> create
            (String title, String description, Project project, Calendar dueDate) {
        Errors errorMessages = verifyTitleAndDescription(title, description, ProjectOrTask.TASK, dueDate);
        errorMessages.add(project == null ? "No project is provided" : null);
        if(errorMessages.size() != 0) {
            return new Validation<>(errorMessages);
        }
        return new Validation<>(new Task(title, description, project, dueDate));
    }
//    private void TasksFile{
//
//        try{
//            FileWriter TaskFile = new FileWriter("Tasks.txt");
//            TaskFile.write(title);
//            TaskFile.write(description);
//            TaskFile.close();
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }


}


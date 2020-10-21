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

public class Task extends AbstractProjectTask implements Comparable<Task>{

    /**
     * private constructor used to initialise a Task object, it is marked private in order to
     * validate entered data before adding it to the tree.
     *
     * @param title is a unique title for the Task
     * @param description is the description to the specific task.
     * @param project is the parent to the unique task
     * @param dueDate is the date on which that specific task is due
     */
    private Task(String title, String description, Project project, Calendar dueDate) {
        super(title, description, project, dueDate);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param   t the object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this object.
     */
    @Override
    public int compareTo(Task t) {
        return getTitle().compareTo(t.getTitle());
    }

    /**
     * returns a default string value for the task.
     *
     * @return a default string for the task.
     */
    @Override
    public String toString() {
        String temp = getDueDate().getTime().toString();
        return "Task - " + String.format(getTitle() + ": " + getDescription() +
                (getDueDate().compareTo(Calendar.getInstance()) < 1 ? "" :
                " (Due on " + temp.substring(4, 10) + temp.substring(temp.lastIndexOf(" ")) + ")"));
    }

    /**
     *
     * @param selectedItem is the item to be deleted.
     * @return
     */
    @Override
    public boolean deleteChild(TreeNode selectedItem) {
        return true;
    }

    /**
     * Returns the child <code>TreeNode</code> at index
     * <code>childIndex</code>.
     *
     * @param   childIndex  index of child
     * @return              the child node at given index
     */
    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    /**
     * Returns the number of children <code>TreeNode</code>s the receiver
     * contains.
     *
     * @return              the number of children the receiver contains
     */
    @Override
    public int getChildCount() {
        return 0;
    }

    /**
     * Returns the index of <code>node</code> in the receivers children.
     * If the receiver does not contain <code>node</code>, -1 will be
     * returned.
     *
     * @param   node        node to be loked for
     * @return              index of specified node
     */
    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    /**
     * Returns true if the receiver is a leaf.
     *
     * @return              whether the receiver is a leaf
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * Returns the children of the receiver as an <code>Enumeration</code>.
     *
     * @return              the children of the receiver as an {@code Enumeration}
     */
    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    /**
     * This is a factory method for creating a Task object. It validates the parameters passed
     * in and will return either a Validation object containing either a Task object or an
     * error message.
     *
     *
     * @param title is the unique title for the task which is validated to ensure it is not blank.
     * @param description is the description to the title which is validated to ensure it is not blank.
     * @param project is the parent project for the task
     * @param dueDate is the date on which this task is due
     * @return
     */
    public static Validation<Task> create
            (String title, String description, Project project, Calendar dueDate) {
        Errors errorMessages = verifyTitleAndDescription(title, description, ProjectOrTask.TASK, dueDate);
        errorMessages.add(project == null ? "No project is provided" : null);
        if((project.getDueDate()).compareTo(dueDate) < 1)
            errorMessages.add("Task's Due date cannot be higher than parent's due date.");
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


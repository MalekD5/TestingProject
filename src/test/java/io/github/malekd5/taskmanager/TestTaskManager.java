package io.github.malekd5.taskmanager;

import io.github.malekd5.taskmanager.tasks.Task;
import io.github.malekd5.taskmanager.tasks.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class TestTaskManager {

    private static TaskManager taskManager;

    private static final String SPORTS_CATEGORY = "sports";

    @BeforeAll
    public static void setUpOnce() {
        taskManager = new TaskManager();

        taskManager.addTask(new Task("Test12",
                "test description 2",
                SPORTS_CATEGORY, 1));
    }

    @Test
    public void testAddTask() {
        String taskName = "Test1";
        taskManager.addTask(new Task(taskName,
                "test description",
                SPORTS_CATEGORY,
                1));

        Optional<Task> task = taskManager.getTask(taskName);

        assertTrue(task.isPresent());
    }


    @Test
    public void testRemoveTask() {
        String taskName = "Test1";

        Optional<Task> taskOptional = taskManager.getTask(taskName);

        assertTrue(taskOptional.isPresent());

        Task task = taskOptional.get();

        assertTrue(taskManager.deleteTask(task));
    }

    @Test
    public void testGetTasksByPriority() {
        int priority = 1;

        taskManager.addTask(new Task("Test123",
                "test description 3",
                "Entertainment", 2));

        List<Task> tasksPriority1 = taskManager.
                getTasksByPriority(priority);

        assertEquals(2, tasksPriority1.size());
    }

    @Test
    public void testGetTasksByCategory() {

        List<Task> tasksCategorySports = taskManager.
                getTasksByCategory(SPORTS_CATEGORY);

        assertEquals(1, tasksCategorySports.size());
    }

    @Test
    public void testMarkTaskAsCompleted() {
        String taskName = "Test12";

        assertDoesNotThrow(() -> taskManager.markTaskAsCompleted(taskName));
    }

    @Test
    public void testMarkTaskAsCompletedShouldThrow() {
        String taskName = "FakeTask";

        assertThrows(IllegalArgumentException.class,
                () -> taskManager.markTaskAsCompleted(taskName));
    }

}

package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao (){
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData (){
        Task todo = new Task();
        todo.setTask("Descrição");
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada (){
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now().minusDays(1));
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void nDeveSalvarTarefaComSucesso () throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);

    }
}

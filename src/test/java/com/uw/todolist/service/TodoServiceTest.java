package com.uw.todolist.service;

import com.uw.todolist.entity.Todo;
import com.uw.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testGetAllTodos() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");
        todo.setDescription("Description of test todo");

        when(todoRepository.findAll()).thenReturn(List.of(todo));

        List<Todo> todos = todoService.getAllTodos();
        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals("Test Todo", todos.get(0).getTitle());
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> result = todoService.getTodoById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Todo", result.get().getTitle());
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        todo.setTitle("New Todo");
        todo.setDescription("New Description");

        when(todoRepository.save(todo)).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo);
        assertEquals("New Todo", createdTodo.getTitle());
    }

    @Test
    public void testUpdateTodo() {
        Todo existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setTitle("Old Title");
        existingTodo.setDescription("Old Description");

        Todo updatedDetails = new Todo();
        updatedDetails.setTitle("Updated Title");
        updatedDetails.setDescription("Updated Description");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(updatedDetails);

        Todo updatedTodo = todoService.updateTodo(1L, updatedDetails);
        assertEquals("Updated Title", updatedTodo.getTitle());
        assertEquals("Updated Description", updatedTodo.getDescription());
    }

    @Test
    public void testDeleteTodoById() {
        doNothing().when(todoRepository).deleteById(1L);

        assertDoesNotThrow(() -> todoService.deleteTodoById(1L));
        verify(todoRepository, times(1)).deleteById(1L);
    }
}

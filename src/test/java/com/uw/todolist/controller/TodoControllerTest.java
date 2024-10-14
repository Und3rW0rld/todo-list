package com.uw.todolist.controller;

import com.uw.todolist.entity.Todo;
import com.uw.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void testGetAllTodos() throws Exception {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");
        todo.setDescription("Description of test todo");

        when(todoService.getAllTodos()).thenReturn(List.of(todo));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Todo"));
    }

    @Test
    public void testGetTodoById() throws Exception {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");

        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Todo"));
    }

    @Test
    public void testCreateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle("New Todo");
        todo.setDescription("New Description");

        when(todoService.createTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Todo\", \"description\": \"New Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Todo"));
    }

    @Test
    public void testUpdateTodo() throws Exception {
        Todo updatedTodo = new Todo();
        updatedTodo.setId(1L);
        updatedTodo.setTitle("Updated Todo");
        updatedTodo.setDescription("Updated Description");

        when(todoService.updateTodo(eq(1L), any(Todo.class))).thenReturn(updatedTodo);

        mockMvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Todo\", \"description\": \"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Todo"));
    }

    @Test
    public void testDeleteTodoById() throws Exception {
        doNothing().when(todoService).deleteTodoById(1L);

        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isNoContent());
    }
}
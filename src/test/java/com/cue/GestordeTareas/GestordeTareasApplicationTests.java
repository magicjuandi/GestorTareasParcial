package com.cue.GestordeTareas;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class GestordeTareasApplicationTests {
	@LocalServerPort
	private int port;
	@MockBean
	private TaskService taskService;
	@Autowired
	private MockMvc mockMvc;
	private final Task task = new Task.Builder()
			.id(1)
			.title("Titulo de tarea")
			.build();
	@Test
	void setup() {
		RestAssured.port = port;
	}

	@Test
	void getTasks() {
		when(taskService.getTasks()).thenReturn(List.of(task));

		RestAssured.given()
				.when()
				.get("/tasks/get")
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON);
	}
	@Test
	void getTask() throws Exception {
		Task task = new Task();
		task.setId(1);
		when(taskService.getTask(1)).thenReturn(task);

		mockMvc.perform(get("/tasks/byId/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void deleteTask() {
		when(taskService.deleteTask(anyInt())).thenReturn("Task Deleted");
		RestAssured.given()
				.when()
				.delete("/tasks/delete/" + task.getId())
				.then()
				.statusCode(200)
				.contentType(ContentType.TEXT);
	}
	@Test
	void createTask() {
		when(taskService.saveTask(any(Task.class))).thenReturn(task);
		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(task)
				.when()
				.post("/tasks/create")
				.then()
				.statusCode(200);
	}
	@Test
	void updateTask() throws Exception {
		when(taskService.updateTask(anyInt(),anyString())).thenReturn(task);

		mockMvc.perform(put("/tasks/update/" + task.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(task)))
				.andExpect(status().isOk());
	}


}

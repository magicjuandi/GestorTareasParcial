package com.cue.GestordeTareas;

import com.cue.GestordeTareas.domain.entities.Task;
import com.cue.GestordeTareas.repositories.TaskRepository;
import com.cue.GestordeTareas.services.TaskService;
import com.cue.GestordeTareas.services.impl.TaskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GestordeTareasApplicationTests {
	@Autowired
	private DataSource dataSource;
	@LocalServerPort
	private int port;
	@MockBean
	private TaskService taskService;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private TaskRepository taskRepositoryU;
	@InjectMocks
	private TaskServiceImpl taskServiceU;
	private final Task task = new Task.Builder()
			.id(1)
			.title("Titulo de tarea")
			.build();
	@Test
	void setup() {
		RestAssured.port = port;
	}
	@Test
	void testDatabaseConnection() throws Exception {
		assertNotNull(dataSource, "El DataSource debe estar configurado");
		try(Connection connection = dataSource.getConnection()) {
			assertNotNull(connection, "La conexión a la BD es exitosa");
		}
	}

	@Test
	void getTask() throws Exception {
		Task task = new Task();
		task.setId(1);
		when(taskService.getTask(1)).thenReturn(Optional.of(task));

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
	@Test
	public void testSaveTask(){
		Task task = new Task(1,"New Task");
		when(taskRepositoryU.save(any())).thenReturn(task);
		Task result = taskServiceU.saveTask(task);
		assertNotNull(result);
		verify(taskRepositoryU,times(1)).save(any());
	}
	@Test
	public void testUpdateTask(){
		Task task = new Task(1,"New Task");
		when(taskRepositoryU.findById(anyInt())).thenReturn(Optional.of(task));
		when(taskRepositoryU.save(any())).thenReturn(task);
		Task result = taskServiceU.updateTask(task.getId(),"New Task Title");
		assertNotNull(result);
		verify(taskRepositoryU,times(1)).save(any());
	}
	@Test
	public void testFindTask(){
		Task task = new Task(1,"New Task");
		when(taskRepositoryU.findById(1)).thenReturn(Optional.of(task));
		Task result = taskServiceU.getTask(task.getId()).orElseThrow();
		assertNotNull(result);
		verify(taskRepositoryU,times(1)).findById(task.getId());
	}
	@Test
	public void testDeleteTask(){
		int taskId = 1;
		when(taskRepositoryU.findById(taskId)).thenReturn(Optional.of(new Task(taskId,"Existing Task")));
		doNothing().when(taskRepositoryU).deleteById(taskId);
		String result = taskServiceU.deleteTask(taskId);
		assertEquals("Task Deleted", result);
		verify(taskRepositoryU,times(1)).deleteById(taskId);
	}

}

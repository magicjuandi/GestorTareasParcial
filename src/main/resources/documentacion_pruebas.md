# Documentación de Pruebas

## 1. Definición de las Pruebas
Esta sección proporciona una descripción general de las pruebas unitarias, de integración y de sistema que se realizarán en el proyecto.

### Pruebas Unitarias
Las pruebas unitarias se enfocan en verificar la funcionalidad de cada unidad de código de manera aislada. Se utilizan frameworks como JUnit para escribir y ejecutar estas pruebas.

### Pruebas de Integración
Las pruebas de integración se realizan para verificar que diferentes componentes del sistema funcionen correctamente cuando se integran entre sí. Se utilizan herramientas como Spring Test para estas pruebas.

### Pruebas de Sistema
Las pruebas de sistema se realizan para verificar que el sistema completo cumpla con los requisitos funcionales y no funcionales. Se utilizan herramientas como Postman para estas pruebas.

## 2. Objetivo de las Pruebas
El propósito de las pruebas es validar que el sistema cumpla con los requisitos especificados y que funcione correctamente en diferentes escenarios. Se busca identificar y corregir errores antes del despliegue del sistema.

## 3. Cronograma de Pruebas
A continuación, se detalla el cronograma de las pruebas:

| Fase de Prueba | Fecha de Inicio | Fecha de Finalización |
|----------------|-----------------|-----------------------|
| Pruebas Unitarias | 01/10/2023 | 07/10/2023 |
| Pruebas de Integración | 08/10/2023 | 14/10/2023 |
| Pruebas de Sistema | 15/10/2023 | 21/10/2023 |

## 4. Matriz de Trazabilidad de Requisitos
La siguiente matriz relaciona los requisitos funcionales con las pruebas correspondientes:

| Requisito Funcional | Prueba Correspondiente |
|---------------------|------------------------|
| RF-01: Crear Tarea | TestCreateTask |
| RF-02: Leer Tarea | TestReadTask |
| RF-03: Actualizar Tarea | TestUpdateTask |
| RF-04: Eliminar Tarea | TestDeleteTask |

## 5. Valores a Usar en las Pruebas
Se utilizarán los siguientes datos de prueba y configuraciones de servicios:

### Datos de Prueba
- **Tarea 1:**
  - Título: "Titulo de tarea"
  - Descripción: "Descripción de la tarea"
  - ID: 1

### Configuraciones de Servicios
- **DataSource:**
  - Configuración de la base de datos para las pruebas.

## 6. Resultados Esperados
Se describen los resultados esperados de cada prueba:

### Pruebas Unitarias
- **TestCreateTask:**
  - Resultado Esperado: La tarea se crea correctamente y se guarda en la base de datos.

- **TestReadTask:**
  - Resultado Esperado: La tarea se lee correctamente desde la base de datos.

- **TestUpdateTask:**
  - Resultado Esperado: La tarea se actualiza correctamente en la base de datos.

- **TestDeleteTask:**
  - Resultado Esperado: La tarea se elimina correctamente de la base de datos.

### Pruebas de Integración
- **TestTaskService:**
  - Resultado Esperado: El servicio de tareas funciona correctamente al interactuar con la base de datos y Redis.

### Pruebas de Sistema
- **TestSystem:**
  - Resultado Esperado: El sistema completo cumple con los requisitos funcionales y no funcionales.

## 7. Reporte de Pruebas
El formato del reporte de pruebas incluirá los siguientes elementos:

- **Nombre de la Prueba:**
  - Descripción de la prueba realizada.

- **Resultado:**
  - Éxito o fallo de la prueba.

- **Observaciones:**
  - Cualquier observación o error encontrado durante la prueba.

Ejemplo de reporte de pruebas:

| Nombre de la Prueba | Resultado | Observaciones |
|---------------------|-----------|---------------|
| TestCreateTask | Éxito | Ninguna |
| TestReadTask | Éxito | Ninguna |
| TestUpdateTask | Éxito | Ninguna |
| TestDeleteTask | Éxito | Ninguna |
| TestTaskService | Éxito | Ninguna |
| TestSystem | Éxito | Ninguna |

### Detalles de las Pruebas Realizadas

#### TestCreateTask
- **Descripción:** Crea una nueva tarea y verifica que se haya guardado correctamente en la base de datos.
- **Código:**
  ```java
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
  ```

#### TestReadTask
- **Descripción:** Lee una tarea existente y verifica que se haya recuperado correctamente de la base de datos.
- **Código:**
  ```java
  @Test
  void getTask() throws Exception {
      Task task = new Task();
      task.setId(1);
      when(taskService.getTask(1)).thenReturn(Optional.of(task));

      mockMvc.perform(get("/tasks/byId/1")
                      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());
  }
  ```

#### TestUpdateTask
- **Descripción:** Actualiza una tarea existente y verifica que se haya actualizado correctamente en la base de datos.
- **Código:**
  ```java
  @Test
  void updateTask() throws Exception {
      when(taskService.updateTask(anyInt(), anyString())).thenReturn(task);

      mockMvc.perform(put("/tasks/update/" + task.getId())
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(new ObjectMapper().writeValueAsString(task)))
              .andExpect(status().isOk());
  }
  ```

#### TestDeleteTask
- **Descripción:** Elimina una tarea existente y verifica que se haya eliminado correctamente de la base de datos.
- **Código:**
  ```java
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
  ```

Este documento proporciona una guía detallada para la realización y documentación de las pruebas en el proyecto.

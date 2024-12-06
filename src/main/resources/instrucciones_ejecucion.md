# Instrucciones de Ejecución Local

## 1. Requisitos Previos

### Software Necesario
- **Docker**: Asegúrate de tener Docker instalado en tu sistema. Puedes descargarlo desde [Docker Hub](https://www.docker.com/get-started).
- **Docker Compose**: Docker Compose debe estar instalado para poder levantar los contenedores. Puedes instalar Docker Compose desde [la documentación oficial](https://docs.docker.com/compose/install/).

## 2. Ejecución de la Aplicación Localmente

### Instrucciones para Construir y Levantar los Contenedores

1. **Navega al Directorio del Proyecto:**
   ```sh
   cd c:/Users/jdgra/Downloads/GestordeTareas/GestordeTareas
   ```

2. **Construye y Levanta los Contenedores:**
   ```sh
   docker-compose up -d
   ```

   Este comando construirá y levantará los contenedores en segundo plano.

3. **Verifica que los Contenedores Estén en Ejecución:**
   ```sh
   docker-compose ps
   ```

   Este comando mostrará el estado de los contenedores.

## 3. Ejecución de las Pruebas Localmente

### Procedimiento para Ejecutar las Pruebas dentro de los Contenedores

1. **Ejecuta las Pruebas:**
   ```sh
   docker-compose exec app mvn clean test
   ```

   Este comando ejecutará las pruebas dentro del contenedor de la aplicación.

2. **Verifica los Resultados de las Pruebas:**
   - Los resultados de las pruebas se mostrarán en la consola.

## 4. Detener la Aplicación y Contenedores

### Instrucciones para Detener los Contenedores

1. **Detén los Contenedores:**
   ```sh
   docker-compose down
   ```

   Este comando detendrá y eliminará los contenedores.

## 5. Notas Importantes

### Información Adicional Relevante

- **Entorno de Ejecución:**
  - Asegúrate de que el archivo `application.properties` esté configurado correctamente para el entorno de desarrollo.
  - Verifica que las credenciales de la base de datos coincidan con las configuradas en el archivo `docker-compose.yml`.

- **Problemas Comunes:**
  - **Conexión a la Base de Datos:**
    - Si experimentas problemas de conexión a la base de datos, asegúrate de que el servicio de PostgreSQL esté en ejecución y que la URL de la base de datos en `application.properties` sea correcta.
  - **Logs de Docker:**
    - Si necesitas más información sobre los logs de los contenedores, puedes usar el comando:
      ```sh
      docker-compose logs
      ```

Este documento proporciona instrucciones detalladas para la ejecución local de la aplicación y las pruebas usando Docker Compose.

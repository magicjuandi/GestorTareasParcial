# Descripción del Proyecto

## 1. Descripción del Proyecto

### Explicación General de la API y su Propósito
Este proyecto es una API RESTful desarrollada para gestionar tareas. La API permite a los usuarios crear, leer, actualizar y eliminar tareas. La aplicación está diseñada para ser escalable y fácil de mantener, utilizando las mejores prácticas de desarrollo y herramientas modernas.

## 2. Requisitos

### Dependencias Necesarias
- **Docker**: Asegúrate de tener Docker instalado en tu sistema. Puedes descargarlo desde [Docker Hub](https://www.docker.com/get-started).
- **Docker Compose**: Docker Compose debe estar instalado para poder levantar los contenedores. Puedes instalar Docker Compose desde [la documentación oficial](https://docs.docker.com/compose/install/).
- **Lenguaje y Framework**: El proyecto está desarrollado en Java utilizando Spring Boot.

## 3. Instrucciones de Instalación

### Clonación del Repositorio
1. Clona el repositorio desde GitHub:
   ```sh
   git clone https://github.com/tu-usuario/GestordeTareas.git
   ```

### Instalación de Dependencias
1. Navega al directorio del proyecto:
   ```sh
   cd GestordeTareas
   ```

2. Instala las dependencias necesarias:
   ```sh
   mvn clean install
   ```

### Configuración de Variables de Entorno
1. Asegúrate de que el archivo `application.properties` esté configurado correctamente. Puedes encontrar el archivo en `src/main/resources/application.properties`.

## 4. Ejecución Local

### Instrucciones para Levantar la Aplicación usando Docker y Docker Compose
1. Navega al directorio del proyecto:
   ```sh
   cd c:/Users/jdgra/Downloads/GestordeTareas/GestordeTareas
   ```

2. Construye y levanta los contenedores:
   ```sh
   docker-compose up -d
   ```

3. Verifica que los contenedores estén en ejecución:
   ```sh
   docker-compose ps
   ```

### Acceso a la API y Pruebas Básicas
1. Accede a la API a través de `http://localhost:8080`.
2. Puedes usar herramientas como Postman para realizar pruebas básicas de las rutas de la API.

## 5. Pruebas

### Instrucciones para Ejecutar las Pruebas Unitarias, de Integración y de Sistema Localmente
1. Ejecuta las pruebas dentro del contenedor de la aplicación:
   ```sh
   docker-compose exec app mvn clean test
   ```

2. Verifica los resultados de las pruebas en la consola.

## 6. Tecnologías Usadas

### Lista de Tecnologías y Herramientas Empleadas
- **API**: Spring Boot
- **Base de Datos**: PostgreSQL
- **Cache**: Redis
- **Contenedores**: Docker, Docker Compose
- **CI/CD**: GitHub Actions
- **Lenguaje**: Java
- **Framework**: Spring Boot

Este documento proporciona una descripción general del proyecto, incluyendo los requisitos, instrucciones de instalación, ejecución local, pruebas y tecnologías utilizadas.

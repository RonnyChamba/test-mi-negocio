# API de Registro de Clientes y Direcciones
Esta API está desarrollada con Spring Boot 2.7.13 utiliza Java 11 y PostgreSQL como base de datos. Permite el registro de clientes y sus sucursales asociadas. Cada cliente puede tener múltiples sucursales.

## Requisitos previos
* Java 11: Asegúrate de tener instalado Java 11 en tu sistema.
* PostgreSQL: Debes contar con una instancia de PostgreSQL en funcionamiento para la base de datos.
  
## Configuración del proyecto
1) Clona el repositorio del proyecto.
2) Abre el proyecto en tu IDE de preferencia.
3) Configura la conexión a la base de datos PostgreSQL en el archivo application-local.properties. Asegúrate de proporcionar la URL, el nombre de usuario y la contraseña correctos.
4) Ten en cuenta que la aplicación esta corriendo por defecto en el puerto 8081.

## Ejecución del Proyecto 
1) Compila y construye el proyecto utilizando las herramientas de construcción compatibles con Spring Boot, como Maven o Gradle.
2) Ejecuta la aplicación utilizando el comando específico de tu herramienta de construcción o directamente desde tu IDE.

## Endpoints

### Clientes
* GET /api/v1/customers: Obtiene todos los clientes registrados con la dirección matriz.
* POST /api/v1/customers: Registra un nuevo cliente con la dirección matriz.
* PUT /api/v1/customers/{ide}: Actualiza los datos de un cliente existente.
* DELETE /api/v1/customers/{ide}: Elimina un cliente existente.

### Sucursales o Direcciones
* POST /api/v1/customers/{ide}/subsidiary: Agrega una nueva sucursal a un cliente.
* GET /api/v1/customers/{ide}/subsidiary: Obtiene todas las sucursales de un cliente.

## Documentación de la API
La documentación de la API está disponible a través de Swagger. Puedes acceder a ella en el siguiente enlace después de ejecutar el proyecto:
* [Documentación de la API](http://localhost:8081/openapi/swagger-ui/index.html)

## Tecnologías utilizadas
* Java 11
* Spring Boot 2.7.13
* PostgreSQL
* Swagger
* Lombok
* mapstruct


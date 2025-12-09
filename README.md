# 🧩 Gestión de Eventos — JDBC + DAO + Arquitectura en 3 Capas

Este proyecto implementa un sistema básico de **gestión de Eventos** utilizando:

✔ JDBC Driver  
✔ Patrón de diseño **DAO**  
✔ Arquitectura por capas (**Dominio – Datos – Presentación**)  
✔ Programación Orientada a Objetos (POO)  
✔ Operaciones CRUD probadas por consola  

---

# 🏗 Arquitectura del Proyecto

```
/src
 ├── dominio/
 │     └── Evento.java
 │
 ├── datos/
 │     ├── IEventoDAO.java
 │     └── EventoDAO.java
 │
 ├── conexion/
 │     └── Conexion.java
 │
 └── presentacion/
       └── EventosApp.java
```

---

# 🗃 1. Capa de Conexión — JDBC

En esta capa se implementa la clase **Conexion**, encargada de:

- Registrar el **driver JDBC**
- Establecer la conexión con MySQL
- Probar el establecimiento efectivo de la conexión
- Retornar el objeto `Connection` para usarlo en el DAO

Incluye manejo de excepciones y cierre adecuado de recursos.

---

# 👤 2. Capa de Dominio — Entidad Evento

La clase `Evento` representa la estructura del evento:

- Atributos del evento  
- Constructores para:
  - Buscar / eliminar (solo ID)
  - Crear (fecha, tema…)
  - Modificar (ID + datos)
- Métodos `get` y `set`
- Métodos sobrescritos:
  - `toString()`  
  - `equals()`  
  - `hashCode()`  

Esto permite un mejor manejo de los objetos y favorece la comparación entre instancias.

---

# 💾 3. Capa de Datos — DAO

Incluye:

### ✔ Interfaz `IEventoDAO`
Define los métodos CRUD:

- `listarEventos()`
- `buscarEventoPorId(Evento evento)`
- `agregarEvento(Evento evento)`
- `modificarEvento(Evento evento)`
- `eliminarEvento(Evento evento)`

### ✔ Implementación `EventoDAO`
Usando:

- `Connection`
- `PreparedStatement`
- `ResultSet`

Se desarrollan los métodos CRUD accediendo directamente a la base de datos.

Todos los métodos fueron probados por consola.

---

# 🎮 4. Capa de Presentación — Consola

La aplicación presenta un menú interactivo:

```
1. Listar Eventos
2. Buscar Evento
3. Agregar Evento
4. Modificar Evento
5. Eliminar Evento
6. Salir
```

El menú utiliza un objeto del servicio/DAO para ejecutar cada operación.

---

# 🚀 Tecnologías Utilizadas

- Java 17+
- MySQL 8
- JDBC Driver
- Patrón DAO
- Arquitectura por capas
- Programación Orientada a Objetos

---

# 📌 Objetivo del Proyecto

Este repositorio forma parte de mi ruta de aprendizaje backend con Java, donde desarrollo un CRUD por cada módulo del sistema **Gestión de Biblioteca**, iniciando con JDBC antes de avanzar hacia:

➡ Spring Boot  
➡ Spring MVC + Thymeleaf

---

# 🙌 Autor

**morocho**  
📧 Correo: *portafoliomoro@gmail.com*  
🔗 GitHub: *portafoliomoro-moro*  

---


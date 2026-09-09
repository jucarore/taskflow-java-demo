# TASKFLOW-101 · Fecha límite opcional para las tareas

## Solicitud

Permitir que una tarea tenga una fecha límite opcional en formato ISO-8601.

## Alcance y elementos de configuración

- Entidad, servicio y API de tareas.
- Migración versionada de la base de datos.
- Pruebas automatizadas.
- `pom.xml`, `Dockerfile`, `CHANGELOG.md` y SBOM de la release.
- Workflow de integración continua y evidencia del Pull Request.

## Criterios de aceptación

1. `POST /api/tasks` acepta `dueDate` opcional en formato `YYYY-MM-DD`.
2. La respuesta de creación y `GET /api/tasks` muestran `dueDate` cuando existe.
3. Una tarea sin `dueDate` continúa siendo válida.
4. Una fecha con formato inválido obtiene respuesta HTTP 400.
5. La migración agrega la columna sin eliminar las tareas existentes.
6. Las pruebas anteriores y las nuevas deben pasar.

## Riesgos

- Aceptar fechas inválidas.
- Romper clientes que no envían la fecha.
- Perder datos al modificar el esquema.
- Publicar una etiqueta que no coincida con la versión interna o el SBOM.

## Responsables y decisión

- Desarrollo: implementa y prueba en `feature/fecha-limite`.
- Calidad/revisión: valida criterios, migración y evidencia automática.
- Configuración/CCB: autoriza el merge y la creación de LB-02.
- Decisión inicial: cambio aprobado para implementación; la liberación queda condicionada a PR aprobado y CI exitoso.

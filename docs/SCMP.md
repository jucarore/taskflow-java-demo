# SCMP didáctico — TaskFlow Java

## 1. Propósito

Este SCMP define el control de configuración de TaskFlow Java durante las Unidades 3 y 4. La línea base inicial es **LB-01 / v1.4.0**. El cambio posterior es **TASKFLOW-101: agregar fecha límite a las tareas**.

## 2. Ítems de configuración (CIs)

| CI | Ubicación | Control |
|---|---|---|
| Construcción y dependencias | `pom.xml`, `.mvn/`, `mvnw*` | Maven Wrapper y revisión de dependencias. |
| Código API | `src/main/java/` | Ramas y Pull Request. |
| Pruebas | `src/test/java/` | Deben pasar antes de integrar. |
| Base de datos | `src/main/resources/db/migration/` | Migraciones Flyway numeradas. |
| Ambientes | `application-*.yml`, `Dockerfile`, `docker-compose.yml` | Versionados y revisados. |
| Automatización | `.github/workflows/ci.yml` | Pipeline de CI. |
| Evidencias | `docs/evidencias/` | Trazabilidad de pruebas y SBOM. |

## 3. Roles

| Rol | Responsabilidad |
|---|---|
| Responsable de configuración / CCB | Mantiene este SCMP, registra líneas base y verifica la regla de `main`. |
| Desarrollador | Implementa en rama y acompaña la evidencia de pruebas. |
| Calidad y revisor | Revisa el Pull Request de otra persona y verifica el pipeline. |

## 4. Control de cambios

1. `main` no recibe cambios directos.
2. Cada cambio nace en una rama, por ejemplo `feature/fecha-limite`.
3. El autor no aprueba su propio Pull Request.
4. La integración requiere revisión aprobada y pipeline verde.
5. Si el pipeline falla, el cambio queda pendiente; no se crea línea base nueva.

## 5. Líneas base y evidencias

| Línea base | Tag | Evidencia mínima |
|---|---|---|
| LB-01 | `v1.4.0` | `./mvnw clean verify`, SBOM CycloneDX, aplicación ejecutándose y tag anotado. |
| LB-02 | `v1.5.0` | PR de TASKFLOW-101, revisión, CI verde, SBOM y tag anotado. |

## 6. Exclusiones

No versionar `target/`, `data/`, secretos, credenciales, archivos personales ni artefactos locales no aprobados.

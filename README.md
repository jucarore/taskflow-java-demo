# TaskFlow Java

Proyecto didáctico para aplicar Gestión de la Configuración de Software con Java. Esta copia representa **LB-01 / v1.4.0**; todavía no contiene la fecha límite de tareas.

## Requisitos

- Java 21.
- Git.
- Docker Desktop, solo para la práctica de ambiente staging.

No se requiere instalar Maven: el proyecto incluye Maven Wrapper.

## Ejecutar en desarrollo

```bash
chmod +x mvnw
./mvnw clean verify
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Abra `http://localhost:8080/api/health`.

Para crear una tarea:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"Preparar línea base"}'
```

## Generar SBOM

```bash
./mvnw cyclonedx:makeBom
```

El comando genera `target/bom.json` y `target/bom.xml`.

## Ejecutar staging simulado

```bash
docker compose up --build
```

Abra `http://localhost:8080/api/health` y detenga el ambiente con `Ctrl+C`.

## Crear LB-01 en Git

```bash
git init
git add .
git commit -m "chore: baseline inicial TaskFlow Java v1.4.0"
git branch -M main
git tag -a v1.4.0 -m "LB-01: TaskFlow Java inicial aprobado"
git log --oneline --decorate --all
```

Consulte `docs/SCMP.md` para las reglas de control de cambios.

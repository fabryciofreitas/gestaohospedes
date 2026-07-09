SISTEMA DE GESTAO DE HOSPEDES E CHECK-IN DE HOTEL
====================================================

SOBRE O PROJETO
----------------
Sistema full-stack para cadastro de hospedes e controle de check-in/check-out
de um hotel, com calculo automatico de tarifas (dia util, fim de semana,
vaga de garagem e diaria extra por saida tardia), login com token JWT e
dois perfis de acesso (Recepcionista e Gerente).

ARQUITETURA
-----------
Backend:   Java 21 + Spring Boot 4.1 (WebMvc, Data JPA, Security, Validation)
Banco:     PostgreSQL 16 (via Docker Compose)
Frontend:  Angular 20 (standalone components) + SCSS
Auth:      JWT proprio (SSO simulado), RBAC com 2 perfis
Docs API:  Swagger / OpenAPI
Testes:    JUnit 5, Mockito, Testcontainers (backend) | Jasmine + Karma (frontend)
Empacotamento: WAR (executavel tambem via "java -jar")

PRE-REQUISITOS
---------------
- JDK 21
- Maven (ou usar o mvnw incluido)
- Node.js LTS + Angular CLI
- Docker Desktop

COMO RODAR
----------
1. docker compose up -d                      (na raiz do projeto)
2. Abrir o backend no IntelliJ e rodar GestaohospedesApplication
3. cd frontend && npm install && ng serve
4. Acessar http://localhost:4200

USUARIOS DE TESTE
------------------
recepcionista / recepcionista123      (perfil RECEPCIONISTA)
gerente       / gerente123            (perfil GERENTE)

ENDPOINTS PRINCIPAIS
---------------------
POST   /api/auth/login
GET    /api/hospedes?busca=&page=&size=
POST   /api/hospedes
PUT    /api/hospedes/{id}
DELETE /api/hospedes/{id}                 (somente GERENTE)
POST   /api/checkins
PUT    /api/checkins/{id}/saida
GET    /api/checkins/presentes
GET    /api/checkins/finalizados
GET    /api/hospedes/{id}/resumo-financeiro

Documentacao interativa completa: http://localhost:8080/swagger-ui/index.html

REGRAS DE NEGOCIO
------------------
- Diaria dia util (seg-sex): R$ 120,00 | fim de semana (sab-dom): R$ 150,00
- Vaga de garagem: R$ 15,00 (dia util) ou R$ 20,00 (fim de semana), por diaria
- Saida apos 16h30: cobra-se uma diaria extra (tarifa do dia da saida)
- Estadia no mesmo dia: cobra-se 1 diaria cheia

TESTES
------
mvn test               (backend: testes unitarios e de integracao com Testcontainers)
cd frontend && ng test (frontend: testes de servico e de componente)

ESTRUTURA DE PASTAS
---------------------
src/main   dominio, repositorio, servico, controlador, dto, seguranca, config
src/test   testes do backend
frontend/  core (models, services, guards, interceptors), features, styles

AUTOR
-----
Fabrycio - desenvolvedor backend Java, Rio de Janeiro

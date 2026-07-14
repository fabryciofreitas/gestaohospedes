SISTEMA DE GESTÃO DE HÓSPEDES E CHECK-IN DE HOTEL
====================================================

SOBRE O PROJETO
----------------
Sistema full-stack para cadastro de hóspedes e controle de check-in/check-out
de um hotel, com cálculo automático de tarifas (dia útil, fim de semana,
vaga de garagem e diária extra por saída tardia), login com token JWT e
dois perfis de acesso (Recepcionista e Gerente).

ARQUITETURA
-----------
Backend:   Java 21 + Spring Boot 4.1 (WebMvc, Data JPA, Security, Validation) <br/>
Banco:     PostgreSQL 16 (via Docker Compose) <br/>
Frontend:  Angular 20 (standalone components) + SCSS <br/>
Auth:      JWT proprio (SSO simulado), RBAC com 2 perfis <br/>
Docs API:  Swagger / OpenAPI <br/>
Testes:    JUnit 5, Mockito, Testcontainers (backend) | Jasmine + Karma (frontend) <br/>
Empacotamento: WAR (executável tambem via "java -jar") 

PRÉ-REQUISITOS
---------------
- JDK 21
- Maven (ou usar o mvnw incluído)
- Node.js LTS + Angular CLI
- Docker Desktop

COMO RODAR
----------
1. docker compose up -d (na raiz do projeto)
2. Abrir o backend no IntelliJ e rodar GestaohospedesApplication
3. cd frontend && npm install && ng serve
4. Acessar http://localhost:4200

USUÁRIOS DE TESTE
------------------
recepcionista / recepcionista123      (perfil RECEPCIONISTA) <br/>
gerente       / gerente123            (perfil GERENTE)

ENDPOINTS PRINCIPAIS
---------------------
POST   /api/auth/login <br/>
GET    /api/hospedes?busca=&page=&size= <br/>
POST   /api/hospedes <br/>
PUT    /api/hospedes/{id} <br/>
DELETE /api/hospedes/{id} (somente GERENTE) <br/>
POST   /api/checkins <br/>
PUT    /api/checkins/{id}/saida <br/>
GET    /api/checkins/presentes <br/>
GET    /api/checkins/finalizados <br/>
GET    /api/hospedes/{id}/resumo-financeiro

Documentação interativa completa: http://localhost:8080/swagger-ui/index.html

REGRAS DE NEGÓCIO
------------------
- Diária dia útil (seg-sex): R$ 120,00 | fim de semana (sab-dom): R$ 150,00
- Vaga de garagem: R$ 15,00 (dia útil) ou R$ 20,00 (fim de semana), por diária
- Saída após 16h30: cobra-se uma diária extra (tarifa do dia da saída)
- Estadia no mesmo dia: cobra-se 1 diária cheia

TESTES
------
mvn test               (backend: testes unitários e de integração com Testcontainers) <br/>
cd frontend && ng test (frontend: testes de serviço e de componente)

ESTRUTURA DE PASTAS
---------------------
src/main   dominio, repositorio, servico, controlador, dto, seguranca, config
src/test   testes do backend
frontend/  core (models, services, guards, interceptors), features, styles

AUTOR
-----
Fabrycio - desenvolvedor backend Java, Rio de Janeiro

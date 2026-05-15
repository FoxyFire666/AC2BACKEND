# AC2 Dev Web - Sistemas de Controle de Projetos e PetCare

Este projeto contém dois sistemas Spring Boot:

## Parte 1: Sistema de Controle de Projetos

Gerencia projetos, funcionários e setores com relacionamentos.

### Funcionalidades
- CRUD de Projetos
- CRUD de Funcionários
- CRUD de Setores
- Vinculação funcionário ↔ projeto
- Consultas customizadas (projetos por data, por funcionário, setores com funcionários)

### Tecnologias
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Lombok
- Spring Web

### Como executar
```bash
cd project-management
mvn spring-boot:run
```
Acesse: http://localhost:8080

## Parte 2: Sistema PetCare

Sistema de gerenciamento veterinário.

### Funcionalidades
- Cadastro de animais e tutores
- Agendamento de consultas (sem conflitos)
- Histórico de vacinação
- Prontuário veterinário
- Regras: Veterinário atende apenas sua especialidade

### Tecnologias
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Lombok
- Spring Web

### Como executar
```bash
cd pet-care
mvn spring-boot:run
```
Acesse: http://localhost:8081

## Estrutura do Projeto
- `pom.xml` - Parent POM
- `project-management/` - Módulo do sistema de projetos
- `pet-care/` - Módulo do sistema PetCare

## Banco de Dados
Ambos os sistemas usam H2 em memória. Console H2 disponível em `/h2-console`.
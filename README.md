# Challenge Base2

## Test Cases for MantisBT Software

### Verify it is possible to add markers to a Task

#### Initial Setup
- Ter um projeto já criado

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Ver Tarefas"
3. Clicar na última Task criada
4. No campo "Marcadores", digitar "newMark%"
5. No campo "Marcadores atuais", selecionar "bug"
6. Clicar no botão "Aplicar"

#### Expected Results
- Verificar se os marcadores selecionados são adicionados ao campo "Marcadores"

---

### Search for a task that exists

#### Initial Setup
- Ter um projeto já criado

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Ver Tarefas"
3. Copiar o ID da última tarefa criada
4. No campo de busca, colar o ID copiado e buscar

#### Expected Results
- Verificar se a busca retorna a task com o ID inserido na busca

---

### Search for a task that does not exist

#### Initial Setup
- Ter um projeto já criado

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Ver Tarefas"
3. No campo de busca, digitar uma string que não corresponda a nenhum ID de task existente

#### Expected Results
- Verificar se a busca retorna a seguinte mensagem de erro: "Um número era esperado para bug_id."

---

### Can download a file

#### Initial Setup
- Ter projetos já criados

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Ver Tarefas"
3. Na tabela "Visualizando Tarefas", localizar os botões "Exportar para Arquivo CSV" e "Exportação para Excel"
4. Clicar em algum dos botões indicados no passo anterior

#### Expected Results
- Verificar no diretório de downloads se um arquivo no formato `nomedoProjeto_Project.csv` ou `nomedoProjeto_Project.xml` é baixado

---

### Verify if it is possible to filter tasks

#### Initial Setup
- Ter um projeto criado com estado atribuído

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Ver Tarefas"
3. Na tabela "Filtros", localizar o campo "Estado" e filtrar por "atribuído"
4. Clicar no botão "Aplicar filtro"

#### Expected Results
- Verificar se na tabela "Visualizando Tarefas" são retornadas tarefas com o filtro adicionado

---

### Can create a new Task with non-ASCII digits

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Criar Tarefa"
3. Escolher uma categoria
4. No campo "Resumo", adicionar uma string com dígitos non-ASCII
5. No campo "Descrição", adicionar uma string com dígitos non-ASCII
6. Clicar no botão "Criar Nova Tarefa"

#### Expected Results
- Verificar se a task é criada

---

### Create a private task

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Criar Tarefa"
3. Escolher uma categoria
4. No campo "Resumo", adicionar uma string com dígitos non-ASCII
5. No campo "Descrição", adicionar uma string com dígitos non-ASCII
6. Selecionar "privado" no campo "visibilidade"
7. Clicar no botão "Criar Nova Tarefa"

#### Expected Results
- Verificar se a task do tipo privada é criada

---

### Can not create a task without required field

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2. Clicar em "Criar Tarefa"
3. Não escolher nenhuma categoria
4. No campo "Resumo", adicionar uma string com dígitos non-ASCII
5. No campo "Descrição", adicionar uma string com dígitos non-ASCII
6. Selecionar "privado" no campo "visibilidade"
7. Clicar no botão "Criar Nova Tarefa"

#### Expected Results
- Verificar se a seguinte mensagem de texto aparece na tela: "Um campo necessário 'category' estava vazio. Por favor, verifique novamente suas entradas."

---

### Can't login with wrong password

#### Initial Setup
- Already have an account in MantisBT

#### Test Steps
1. Access [MantisBT](https://mantis-prova.base2.com.br)
2. Type your user name
3. In password field type a wrong password
4. Clique para logar

#### Expected Results
- Verify that the site does not allow the login using a wrong password.
---

### Can login with wrong password

#### Initial Setup
- Already have an account in MantisBT

#### Test Steps
1. Access [MantisBT](https://mantis-prova.base2.com.br)
2. Type your user name
3. In password field type your password
4. Clique para logar

#### Expected Results
- Verify that the site allows the login.

---
### Can create a new Profile

#### Initial Setup
- 

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2.
3. 
4.

#### Expected Results
- 
---

### Can delete a Profile Created

#### Initial Setup
- 

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2.
3. 
4.

#### Expected Results

---

### Can not delete (Select)

#### Initial Setup
- 

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2.
3. 
4.

#### Expected Results

---
### Can create a task using a profile created

#### Initial Setup
- 

#### Test Steps
1. Logar em [MantisBT](https://mantis-prova.base2.com.br)
2.
3. 
4.

#### Expected Results

### Configurações para executar o projeto
1. Criar um arquivo config.properties dentro da pasta src contendo
username=seu_user
password=sua_senha
2. O chrome driver utilizado está dentro da pasta do projeto
3. Algumas configurações da máquina utilizada no desenvolvimento:
Maven: apache-maven-3.9.9
Java version: 22.0.2, vendor: Oracle Corporation
Default locale: pt_BR, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"


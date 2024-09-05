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

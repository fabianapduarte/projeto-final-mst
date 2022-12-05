<h1 align="center"> 
	Árvore geradora mínima
</h1>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-exemplo">Exemplo</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> •
 <a href="#-autores">Autores</a>
</p>

## 💻 Sobre o projeto

Trabalho desenvolvido para as disciplinas de Estruturas de Dados Básicas II e Linguagem de Programação II, cujo objetivo é implementar uma solução para um problema NP-difícil de obtenção de uma árvore geradora mínima em Java com base no algoritmo de Kruskal.

### Links extras

- [Relatório do trabalho](https://docs.google.com/document/d/17_2G-u_DUoTQZEkwlLO0KR2zLgqyOgJS/edit?usp=sharing&ouid=116738805822748469008&rtpof=true&sd=true)
- [Slides de apresentação](https://docs.google.com/presentation/d/1DIv3HYaDD9ErowkvRs-FbzNh_wUaePxY_Hu-Ofm9vts/edit?usp=sharing)

---

## ⚙ Funcionalidades

- [x] Leitura do arquivo de entrada
- [ ] Gerar árvores geradoras
  - [ ] Sem ordem de custo
  - [ ] Com ordem de custo
- [ ] Salvar no arquivo a melhor solução encontrada 

---

## 📑 Exemplo

<!-- <p align="center">
  <img src="./.github/terminal.png" alt="Terminal" width="800px" style="margin: 16px 0" />
</p> -->

---

## 🚀 Como executar o projeto

### Instalação

Para executar o programa, é necessário ter o JDK do Java instalado na máquina. Para instalar no Linux, execute no terminal:

```bash
$ sudo apt install default-jdk
```

Após a instalação, clone este repositório:

```bash

# Clone este repositório
$ git clone https://github.com/fabianapduarte/projeto-final-mst.git

# Acesse a pasta do projeto no terminal/cmd
$ cd projeto-final-mst

```

<br />

### Rodando o projeto com os arquivos presentes em `/data`

```bash

# Acesse a pasta ArvoreGeradoraMinima do projeto no terminal/cmd
$ cd ArvoreGeradoraMinima

# Compile o código
$ javac @compile.txt

# Execute o programa
# Substitua <modo> pelo modo de execução do algoritmo (se não indicado, o modo padrão é sem ordenação)
# 1 - sem ordem de custo
# 2 - com ordem de custo
$ java @run.txt in <modo>

```

<br />

### Rodando o projeto com outros arquivos de entrada

**⚠ Observações:**

- Adicione as novas bases de dados em formato `.txt` na pasta `/data`;
- As bases de dados devem seguir o seguinte formato:

```

<valor de n> <valor de d>
<custo c1---c2> <custo c1---c3> <custo c1---c4> ... <custo c1---cn>
<custo c2---c3> <custo c2---c4> ... <custo c2---cn>
<custo c3---c4> ... <custo c3---cn>
...
<custo c(n-1)---cn>

```

Para executar no terminal:

```bash

# Acesse a pasta ArvoreGeradoraMinima do projeto no terminal/cmd
$ cd ArvoreGeradoraMinima

# Compile o código
$ javac @compile.txt

# Execute o programa com os arquivos de entrada
# Substitua <in> pelo nome do arquivo
# Substitua <modo> pelo modo de execução do algoritmo (se não indicado, o modo padrão é sem ordenação)
# 1 - sem ordem de custo
# 2 - com ordem de custo
$ java @run.txt <in> <modo>

```

---

## 👥 Autores

- Fabiana Pereira, aluna de Estruturas de Dados Básicas II
- Samuel Costa, aluno de Estruturas de Dados Básicas II e Linguagem de Programação II

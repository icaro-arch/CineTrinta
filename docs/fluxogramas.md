# 🔀 Fluxogramas e Máquina de Estados — CineTrinta

> ⬅️ [Voltar para o README principal](../README.md) · 🧱 [Ver Diagramas de Classes UML](diagrama-de-classes.md)

Este documento detalha os fluxos operacionais do **CineTrinta** por caso de uso e requisito funcional (`RF01`–`RF06`), incluindo as validações obrigatórias das regras de negócio (**RN01**, **RN02** e **RN03**) e a máquina de estados do player.

---

## 1. Inicialização e Persistência em Arquivos (`RF04`)

Fluxo executado por `Main.main(...)` e `PersistenciaArquivoTexto` na abertura e no encerramento da aplicação.

### 1.1 Carga Inicial (Startup)

```mermaid
flowchart TD
    ini(["Início: Main.main()"]) --> load["PersistenciaService.carregarDados(dir)"]
    load --> ioLoad{"Erro de leitura<br/>(IOException)?"}
    ioLoad -->|"Sim"| errLoad["Exibe alerta e inicia<br/>com DadosSistemaDTO vazio"]
    ioLoad -->|"Não"| arqExist{"Arquivos existem?<br/>(catalogo.txt, contas_perfis.txt,<br/>avaliacoes.txt)"}
    arqExist -->|"Não (1º uso)"| dtoVazio["Retorna DadosSistemaDTO vazio"]
    arqExist -->|"Sim"| ler["BufferedReader lê linhas,<br/>faz split(';') e reconstrói objetos"]
    errLoad --> initCtrl
    dtoVazio --> initCtrl
    ler --> initCtrl["Popula RepositorioCatalogo,<br/>GerenciadorUsuarios e<br/>ServicoAvaliacaoEFavoritos"]
    initCtrl --> ui(["Inicia TelaPrincipalController (RF05)"])
```

### 1.2 Salvamento (Encerramento)

```mermaid
flowchart LR
    sessao(["Usuário encerra a UI"]) --> montarDTO["Monta DadosSistemaDTO<br/>com estado atual"]
    montarDTO --> salvar["PersistenciaArquivoTexto.<br/>salvarDados(dto, dir)"]
    salvar --> ioSave{"Gravação via<br/>BufferedWriter OK?"}
    ioSave -->|"Sim"| fim(["Fim da execução"])
    ioSave -->|"Não (IOException)"| errSave["Exibe erro de escrita<br/>ao usuário"] --> fim
```

---

## 2. Autenticação, Planos e Gestão de Perfis (`RF02` e `RN01`)

Fluxo de entrada do assinante, criação de conta, seleção/remoção de perfil e validação da cota máxima de perfis por plano (**RN01**).

```mermaid
flowchart TD
    loginIni(["Tela de Login (RF05)"]) --> acao{"Ação do usuário"}

    acao -->|"Cadastrar Conta"| dadosConta["Informa email, senha,<br/>nomeTitular e TipoPlano"]
    dadosConta --> cadConta["GerenciadorUsuarios.<br/>cadastrarConta(novaConta)"]
    cadConta --> emailOk{"E-mail ainda não<br/>cadastrado?"}
    emailOk -->|"Não"| errEmail["Retorna false:<br/>e-mail já existe"] --> loginIni
    emailOk -->|"Sim"| salvaConta["Salva ContaAssinante<br/>no Map"] --> telaPerfis

    acao -->|"Entrar"| auth["GerenciadorUsuarios.<br/>autenticar(email, senha)"]
    auth --> credOk{"Conta existe e<br/>senha confere?"}
    credOk -->|"Não"| errAuth["Retorna null:<br/>credenciais inválidas"] --> loginIni
    credOk -->|"Sim"| telaPerfis["Tela: 'Quem está assistindo?'"]

    telaPerfis --> escPerfil{"Ação na conta"}
    escPerfil -->|"Selecionar Perfil"| perfilAtivo["Define perfilAtivo<br/>(PerfilAdulto ou PerfilInfantil)"] --> vitrine(["Abre Vitrine do Catálogo (RF01)"])
    escPerfil -->|"Logout / Trocar Conta"| loginIni
    escPerfil -->|"Remover Perfil"| remPerfil["ContaAssinante.removerPerfil(nome)"] --> telaPerfis

    escPerfil -->|"Criar Novo Perfil"| verLimite["ContaAssinante.<br/>adicionarPerfil(novoPerfil)"]
    verLimite --> rn01{"perfis.size() >=<br/>plano.getLimiteMaximoPerfis()?<br/>(BASICO=1, PADRAO=3, PREMIUM=5)"}
    rn01 -->|"Sim (RN01 violada)"| excRN01["Lança<br/>LimitePerfisExcedidoException"] --> avisoPlano["UI exibe aviso<br/>de limite do plano"] --> telaPerfis
    rn01 -->|"Não"| addPerfil["Adiciona Perfil<br/>à lista da conta"] --> telaPerfis
```

---

## 3. Catálogo (`RF01`), Bloqueio Parental (`RN02`) e Player (`RF06`)

### 3.1 Gestão Administrativa e Consultas ao Catálogo (`RF01`)

```mermaid
flowchart TD
    catIni(["Módulo Catálogo (RF01)"]) --> modo{"Perfil / Modo de Acesso"}

    modo -->|"Administração do Acervo"| opAdmin{"Operação CRUD"}
    opAdmin -->|"Cadastrar"| cadM["RepositorioCatalogo.<br/>cadastrarMidia(midia)"]
    opAdmin -->|"Atualizar"| updM["RepositorioCatalogo.<br/>atualizarMidia(id, novosDados)"]
    opAdmin -->|"Remover"| delM["RepositorioCatalogo.<br/>removerMidia(id)"]
    cadM --> catFim(["Acervo atualizado"])
    updM --> catFim
    delM --> catFim

    modo -->|"Vitrine do Espectador"| busca{"Filtro ou Busca"}
    busca -->|"Por Título"| m1["buscarPorTitulo(termo)"]
    busca -->|"Por Gênero"| m2["filtrarPorGenero(genero)"]
    busca -->|"Por Ano"| m3["filtrarPorAno(ano)"]
    busca -->|"Listar Tudo"| m4["listarTodas()"]
    busca -->|"Por ID"| m5["buscarPorId(id)"]

    m1 --> detalhes(["Abre Modal de Detalhes da Mídia"])
    m2 --> detalhes
    m3 --> detalhes
    m4 --> detalhes
    m5 --> detalhes
```

### 3.2 Bloqueio Parental (`RN02`) e Fluxo do Player Integrado (`RF06`)

```mermaid
flowchart TD
    playBtn(["Usuário clica em Assistir:<br/>MotorReproducao.iniciarReproducao(perfil, midia)"]) --> tocando{"Já existe mídia<br/>em reprodução?"}
    tocando -->|"Sim"| salvaAnt["Salva progresso anterior no perfil<br/>e encerra sessão anterior"] --> valIdade
    tocando -->|"Não"| valIdade

    valIdade["Valida acesso etário (RN02):<br/>GerenciadorUsuarios.validarAcessoEtario /<br/>perfil.podeAcessarMidia(midia)"] --> rn02{"PerfilInfantil e<br/>idadeMinima > 12?"}
    rn02 -->|"Sim (RN02 violada)"| excRN02["Lança<br/>ClassificacaoIndicativaException"] --> blockUI["UI exibe bloqueio parental<br/>e impede reprodução"] --> fimPlay(["Retorna ao Modal / Vitrine"])

    rn02 -->|"Não (acesso permitido)"| resume["Consulta perfil.getMinutosAssistidos(idMidia)<br/>('Continuar Assistindo') e<br/>incrementa totalVisualizacoes"]
    resume --> estadoPlay["estadoAtual = REPRODUZINDO<br/>Notifica PlayerListener.onEstadoAlterado"] --> ctrl{"Comando durante<br/>reprodução"}

    ctrl -->|"Pausar / Retomar"| saveProg["pausar() / retomar()<br/>Salva progresso e alterna<br/>PAUSADO ↔ REPRODUZINDO"] --> notifyLoop
    ctrl -->|"Avançar / Retroceder"| ajustaMin["avancar(min) / retroceder(min)<br/>Atualiza minutoAtual e<br/>registra progresso"] --> notifyLoop["Notifica onEstadoAlterado /<br/>onProgressoAtualizado"] --> ctrl

    ctrl -->|"Parar"| stopPlay["parar(): salva progresso,<br/>estadoAtual = PARADO<br/>e notifica PlayerListener"] --> fimPlay

    ctrl -->|"Fim da mídia (finalizar)"| fimMidia["finalizar(): estadoAtual = FINALIZADO<br/>e salva progresso no perfil"]
    fimMidia --> ehSerie{"Mídia é Serie e<br/>há próximo Episodio<br/>(na Temporada ou próxima)?"}
    ehSerie -->|"Sim"| proxEp["Avança para próximo Episodio<br/>e notifica onEpisodioAvancado"] --> estadoPlay
    ehSerie -->|"Não"| fimPlay
```

---

## 4. Máquina de Estados do Player (`EstadoPlayer` — RF06)

Transições válidas controladas por `MotorReproducao` e comunicadas à interface gráfica via `PlayerListener`.

```mermaid
stateDiagram-v2
    direction LR

    [*] --> PARADO : inicialização
    PARADO --> REPRODUZINDO : iniciarReproducao() [RN02 OK]

    REPRODUZINDO --> PAUSADO : pausar()
    PAUSADO --> REPRODUZINDO : retomar() / iniciarReproducao(outraMidia)

    REPRODUZINDO --> REPRODUZINDO : avancar() / retroceder() / trocar mídia

    REPRODUZINDO --> PARADO : parar()
    PAUSADO --> PARADO : parar()

    REPRODUZINDO --> FINALIZADO : finalizar() [fim da duração]
    FINALIZADO --> REPRODUZINDO : próximo episódio (Série) / iniciarReproducao()
    FINALIZADO --> PARADO : parar()
```

> *Nota:* Em `pausar()`, `parar()`, `avancar()`, `retroceder()`, `finalizar()` e troca de mídia (`iniciarReproducao` com sessão ativa), o `MotorReproducao` persiste `minutoAtual` em `perfil.registrarProgresso(idMidia, minutoAtual)` antes de concluir a transição.

---

## 5. Engajamento (`RN03`) e Relatórios Gerenciais (`RF03`)

### 5.1 Favoritos e Avaliações (`ServicoAvaliacaoEFavoritos`)

```mermaid
flowchart TD
    eng(["Ação no Modal da Mídia (RF03)"]) --> tipo{"Operação solicitada"}

    tipo -->|"Adicionar Favorito"| favCheck{"Mídia já está em<br/>perfil.getMinhaListaFavoritos()?"}
    favCheck -->|"Sim (RN03 violada)"| excFav["Lança<br/>FavoritoDuplicadoException"] --> retModal(["Retorna ao Modal da Mídia"])
    favCheck -->|"Não"| addFav["Adiciona Mídia ao Set<br/>de favoritos do perfil"] --> retModal

    tipo -->|"Remover Favorito"| remFav["removerDosFavoritos(perfil, midia)<br/>Remove do Set do perfil"] --> retModal
    tipo -->|"Listar Avaliações"| listAv["listarAvaliacoesDaMidia(idMidia)<br/>Exibe notas e resenhas"] --> retModal

    tipo -->|"Avaliar Mídia (1 a 5)"| calcCons["minutos = perfil.getMinutosAssistidos(idMidia)<br/>total = midia.getDuracaoTotalMinutos()"]
    calcCons --> rn03{"total > 0 e<br/>minutos >= 20% de total?"}
    rn03 -->|"Não (RN03 violada)"| excCons["Lança<br/>ConsumoMinimoInsuficienteException"] --> retModal
    rn03 -->|"Sim"| notaVal{"Estrelas entre<br/>1 e 5?"}
    notaVal -->|"Não"| errNota["Construtor de Avaliacao<br/>rejeita nota inválida"] --> retModal
    notaVal -->|"Sim"| saveAval["Cria e salva Avaliacao<br/>em todasAvaliacoes"] --> recalc["calcularMediaAvaliacoes(idMidia)<br/>e atualiza midia.mediaAvaliacoes"] --> retModal
```

### 5.2 Relatórios Gerenciais (`GeradorRelatorios`)

```mermaid
flowchart LR
    adm(["Painel Administrativo (RF03)"]) --> relTipo{"Tipo de Relatório"}
    relTipo -->|"Audiência"| top10["GeradorRelatorios.<br/>gerarTop10MaisAssistidos(catalogo)<br/>Ranking Top 10"]
    relTipo -->|"Por Gênero"| porGen["GeradorRelatorios.<br/>gerarConsumoPorGenero(catalogo)<br/>Visualizações por Genero"]
    relTipo -->|"Financeiro"| fin["GeradorRelatorios.<br/>gerarRelatorioFinanceiro(contasAtivas)<br/>Contas por TipoPlano + Faturamento"]
    top10 --> exibe(["Exibe relatório na UI"])
    porGen --> exibe
    fin --> exibe
```

# 🧱 Diagramas de Classes UML — CineTrinta

> ⬅️ [Voltar para o README principal](../README.md) · 🔀 [Ver Fluxogramas Detalhados](fluxogramas.md)

Este documento reúne o **Diagrama de Classes UML completo** do projeto **CineTrinta**, separado por módulos (`RF01` a `RF06` e `exceptions`) para manter a leitura clara e legível tanto no GitHub quanto no VS Code.

> **Nota sobre o estado atual do código:** O repositório encontra-se na fase de arquitetura inicial (esqueletos das classes). Os diagramas refletem fielmente **as classes, interfaces, enumerações, exceções e assinaturas já declaradas** no código-fonte, bem como os **atributos, construtores e métodos planejados nos comentários `// TODO:`** de cada arquivo (destacados nas observações abaixo de cada módulo).

### Legenda UML (Mermaid)

| Símbolo / Seta | Significado | Exemplo |
| :--- | :--- | :--- |
| `<\|--` | Herança (`extends`) | `Midia <\|-- Filme` |
| `<\|..` | Implementação de Interface (`implements`) | `PersistenciaService <\|.. PersistenciaArquivoTexto` |
| `*--` | Composição (ciclo de vida dependente) | `Serie *-- Temporada` |
| `o--` | Agregação (coleção / referência gerenciada) | `RepositorioCatalogo o-- Midia` |
| `-->` | Associação direta (atributo de instância) | `ContaAssinante --> TipoPlano` |
| `..>` | Dependência (parâmetro, retorno ou `throws`) | `ContaAssinante ..> LimitePerfisExcedidoException` |
| `~T~` | Tipo genérico (`<T>`) | `List~Midia~`, `Map~String, Integer~` |
| `*` (ao final do método) | Método abstrato (`abstract`) | `+getDuracaoTotalMinutos()* int` |

---

## 1. Arquitetura Modular e Dependências entre Pacotes

Visão macro de como o ponto de entrada (`main`) e a camada de interface (`ui`) orquestram os módulos de domínio, serviços, persistência e o tratamento de exceções:

```mermaid
flowchart LR
    main["main<br/>(Main)"] --> ui["ui<br/>(TelaPrincipalController)"]
    main --> persistencia["persistencia<br/>(RF04: DTO / Service / ArquivoTexto)"]

    ui --> catalogo["catalogo<br/>(RF01: Midia / RepositorioCatalogo)"]
    ui --> usuarios["usuarios<br/>(RF02: Contas / Planos / Perfis)"]
    ui --> engajamento["engajamento<br/>(RF03: Avaliações / Favoritos / Relatórios)"]
    ui --> persistencia
    ui --> player["player<br/>(RF06: MotorReproducao / PlayerListener)"]
    ui -.-> exceptions["exceptions<br/>(RN01 / RN02 / RN03)"]

    usuarios --> catalogo
    usuarios -.-> exceptions
    engajamento --> catalogo
    engajamento --> usuarios
    engajamento -.-> exceptions
    player --> catalogo
    player --> usuarios
    player -.-> exceptions
    persistencia --> catalogo
    persistencia --> usuarios
    persistencia --> engajamento
```

---

## 2. Módulo Catálogo (`br.ufc.cinetrinta.catalogo` — RF01)

Responsável pela hierarquia polimórfica de mídias (`Midia`), estrutura hierárquica de séries (`Serie` → `Temporada` → `Episodio`) e operações de CRUD/busca no acervo (`RepositorioCatalogo`).

```mermaid
classDiagram
    direction TB

    class RepositorioCatalogo {
        -acervo: List~Midia~
        +cadastrarMidia(midia: Midia) void
        +atualizarMidia(id: String, novosDados: Midia) boolean
        +removerMidia(id: String) boolean
        +buscarPorId(id: String) Midia
        +buscarPorTitulo(termoBusca: String) List~Midia~
        +filtrarPorGenero(genero: Genero) List~Midia~
        +filtrarPorAno(ano: int) List~Midia~
        +listarTodas() List~Midia~
    }

    class Midia {
        <<abstract>>
        -id: String
        -titulo: String
        -sinopse: String
        -genero: Genero
        -classificacao: ClassificacaoIndicativa
        -anoLancamento: int
        -caminhoPoster: String
        -caminhoMidia: String
        -mediaAvaliacoes: double
        -totalVisualizacoes: int
        +getDuracaoTotalMinutos()* int
        +getTipoMidia()* String
    }

    class Filme {
        -diretor: String
        -duracaoMinutos: int
        +getDuracaoTotalMinutos() int
        +getTipoMidia() String
    }

    class Documentario {
        -temaAbordado: String
        -duracaoMinutos: int
        +getDuracaoTotalMinutos() int
        +getTipoMidia() String
    }

    class Serie {
        -temporadas: List~Temporada~
        -criador: String
        +adicionarTemporada(t: Temporada) void
        +getTemporadas() List~Temporada~
        +getQuantidadeTotalEpisodios() int
        +getDuracaoTotalMinutos() int
        +getTipoMidia() String
    }

    class Temporada {
        -numeroTemporada: int
        -episodios: List~Episodio~
        +Temporada(numeroTemporada: int)
        +adicionarEpisodio(ep: Episodio) void
        +getEpisodios() List~Episodio~
        +getNumeroTemporada() int
        +getDuracaoTotalTemporada() int
    }

    class Episodio {
        -numeroEpisodio: int
        -titulo: String
        -resumo: String
        -duracaoMinutos: int
        -caminhoMidia: String
    }

    class Genero {
        <<enumeration>>
        ACAO
        COMEDIA
        DRAMA
        FICCAO_CIENTIFICA
        TERROR
        DOCUMENTARIO
        ANIMACAO
        -descricao: String
        +getDescricao() String
    }

    class ClassificacaoIndicativa {
        <<enumeration>>
        LIVRE
        DEZ_ANOS
        DOZE_ANOS
        QUATORZE_ANOS
        DEZESSEIS_ANOS
        DEZOITO_ANOS
        -idadeMinima: int
        +getIdadeMinima() int
        +getRotulo() String
    }

    RepositorioCatalogo "1" o-- "0..*" Midia : gerencia
    RepositorioCatalogo ..> Genero : filtra por
    Midia --> Genero : possui
    Midia --> ClassificacaoIndicativa : possui
    Midia <|-- Filme
    Midia <|-- Documentario
    Midia <|-- Serie
    Serie "1" *-- "0..*" Temporada : compõe
    Temporada "1" *-- "0..*" Episodio : compõe
```

> **Observações (`// TODO`):**
> * Construtores, getters e setters de `Midia`, `Filme`, `Documentario`, `Serie` e `Episodio` foram omitidos nas caixas para preservar a legibilidade.
> * Em `ClassificacaoIndicativa`, `getIdadeMinima()` já está implementado; `getRotulo()` e os campos de `Genero` (`descricao`, `getDescricao()`) são planejados via `// TODO`.

---

## 3. Módulo Usuários, Planos e Perfis (`br.ufc.cinetrinta.usuarios` — RF02)

Controla o cadastro/autenticação de contas (`GerenciadorUsuarios`), cotas de perfis por plano (`RN01` em `ContaAssinante` e `TipoPlano`) e validação polimórfica de faixa etária (`RN02` em `PerfilAdulto` e `PerfilInfantil`).

```mermaid
classDiagram
    direction TB

    class GerenciadorUsuarios {
        -contasPorEmail: Map~String, ContaAssinante~
        +cadastrarConta(novaConta: ContaAssinante) boolean
        +autenticar(email: String, senha: String) ContaAssinante
        +buscarPorEmail(email: String) ContaAssinante
        +listarTodasContas() List~ContaAssinante~
        +validarAcessoEtario(perfil: Perfil, midia: Midia) void
    }

    class ContaAssinante {
        -email: String
        -senha: String
        -nomeTitular: String
        -plano: TipoPlano
        -perfis: List~Perfil~
        +adicionarPerfil(novoPerfil: Perfil) void
        +removerPerfil(nomePerfil: String) boolean
        +buscarPerfil(nomePerfil: String) Perfil
    }

    class TipoPlano {
        <<enumeration>>
        BASICO
        PADRAO
        PREMIUM
        -nome: String
        -limiteMaximoPerfis: int
        -valorMensalidade: double
        +getNome() String
        +getLimiteMaximoPerfis() int
        +getValorMensalidade() double
    }

    class Perfil {
        <<abstract>>
        -nome: String
        -minhaListaFavoritos: Set~Midia~
        -historicoTempoAssistido: Map~String, Integer~
        +getNome() String
        +getMinhaListaFavoritos() Set~Midia~
        +getHistoricoTempoAssistido() Map~String, Integer~
        +registrarProgresso(idMidia: String, minutosAssistidos: int) void
        +getMinutosAssistidos(idMidia: String) int
        +podeAcessarMidia(midia: Midia)* boolean
        +isInfantil()* boolean
    }

    class PerfilAdulto {
        +podeAcessarMidia(midia: Midia) boolean
        +isInfantil() boolean
    }

    class PerfilInfantil {
        +podeAcessarMidia(midia: Midia) boolean
        +isInfantil() boolean
    }

    class Midia {
        <<abstract>>
    }

    class LimitePerfisExcedidoException {
        <<exception>>
    }

    class ClassificacaoIndicativaException {
        <<exception>>
    }

    GerenciadorUsuarios "1" o-- "0..*" ContaAssinante : gerencia
    GerenciadorUsuarios ..> Perfil : valida
    GerenciadorUsuarios ..> Midia : verifica
    GerenciadorUsuarios ..> ClassificacaoIndicativaException : throws RN02
    ContaAssinante "*" --> "1" TipoPlano : assina
    ContaAssinante "1" *-- "0..*" Perfil : possui
    ContaAssinante ..> LimitePerfisExcedidoException : throws RN01
    Perfil <|-- PerfilAdulto
    Perfil <|-- PerfilInfantil
    Perfil "0..*" o-- "0..*" Midia : favoritos
```

> **Observações (`// TODO`):**
> * Construtores, getters e setters de `ContaAssinante`, `PerfilAdulto` e `PerfilInfantil` foram omitidos nas caixas para manter o diagrama compacto.
> * Em `ContaAssinante`, `adicionarPerfil(...)` já possui assinatura declarada; os métodos de remoção/busca de perfil (`removerPerfil`, `buscarPerfil`) estão previstos no `// TODO #4` do arquivo.

---

## 4. Módulo Engajamento e Relatórios (`br.ufc.cinetrinta.engajamento` — RF03)

Gerencia notas/resenhas (`Avaliacao`), validação de consumo mínimo de 20% e unicidade de favoritos (`RN03` em `ServicoAvaliacaoEFavoritos`), além dos relatórios gerenciais (`GeradorRelatorios`).

### 4.1 Avaliações e Favoritos (`ServicoAvaliacaoEFavoritos` e `Avaliacao`)

```mermaid
classDiagram
    direction TB

    class Avaliacao {
        -idMidia: String
        -nomePerfilAutor: String
        -estrelas: int
        -comentario: String
        +Avaliacao(idMidia: String, autor: String, estrelas: int, comentario: String)
        +toString() String
    }

    class ServicoAvaliacaoEFavoritos {
        -todasAvaliacoes: List~Avaliacao~
        +registrarAvaliacao(perfil: Perfil, midia: Midia, estrelas: int, comentario: String) void
        +calcularMediaAvaliacoes(idMidia: String) double
        +listarAvaliacoesDaMidia(idMidia: String) List~Avaliacao~
        +adicionarAosFavoritos(perfil: Perfil, midia: Midia) void
        +removerDosFavoritos(perfil: Perfil, midia: Midia) void
    }

    class Perfil {
        <<abstract>>
    }

    class Midia {
        <<abstract>>
    }

    class ConsumoMinimoInsuficienteException {
        <<exception>>
    }

    class FavoritoDuplicadoException {
        <<exception>>
    }

    ServicoAvaliacaoEFavoritos "1" o-- "0..*" Avaliacao : armazena
    ServicoAvaliacaoEFavoritos ..> Perfil : consulta
    ServicoAvaliacaoEFavoritos ..> Midia : atualiza média
    ServicoAvaliacaoEFavoritos ..> ConsumoMinimoInsuficienteException : throws RN03
    ServicoAvaliacaoEFavoritos ..> FavoritoDuplicadoException : throws RN03
```

### 4.2 Relatórios Gerenciais (`GeradorRelatorios`)

```mermaid
classDiagram
    direction TB

    class GeradorRelatorios {
        +gerarTop10MaisAssistidos(catalogoCompleto: List~Midia~) List~Midia~
        +gerarConsumoPorGenero(catalogoCompleto: List~Midia~) Map~Genero, Integer~
        +gerarRelatorioFinanceiro(contasAtivas: List~ContaAssinante~) String
    }

    class Midia {
        <<abstract>>
    }

    class Genero {
        <<enumeration>>
    }

    class ContaAssinante

    class TipoPlano {
        <<enumeration>>
    }

    GeradorRelatorios ..> Midia : ranking
    GeradorRelatorios ..> Genero : agrupa
    GeradorRelatorios ..> ContaAssinante : analisa
    GeradorRelatorios ..> TipoPlano : mensalidade
```

> **Observação (`// TODO`):** Em `Avaliacao`, o construtor (com validação de `1..5` estrelas), getters e setters estão especificados nos comentários `// TODO`.

---

## 5. Módulo Persistência (`br.ufc.cinetrinta.persistencia` — RF04)

Desacopla o sistema do formato de armazenamento através da interface `PersistenciaService` e do objeto agregador `DadosSistemaDTO`.

```mermaid
classDiagram
    direction TB

    class PersistenciaService {
        <<interface>>
        +salvarDados(dados: DadosSistemaDTO, diretorioDestino: String) void
        +carregarDados(diretorioOrigem: String) DadosSistemaDTO
    }

    class PersistenciaArquivoTexto {
        +salvarDados(dados: DadosSistemaDTO, diretorioDestino: String) void
        +carregarDados(diretorioOrigem: String) DadosSistemaDTO
    }

    class DadosSistemaDTO {
        -catalogo: List~Midia~
        -contas: List~ContaAssinante~
        -avaliacoes: List~Avaliacao~
    }

    class Midia {
        <<abstract>>
    }

    class ContaAssinante

    class Avaliacao

    PersistenciaService <|.. PersistenciaArquivoTexto
    PersistenciaService ..> DadosSistemaDTO : serializa / retorna
    PersistenciaArquivoTexto ..> DadosSistemaDTO : grava e lê txt
    DadosSistemaDTO "1" o-- "0..*" Midia : catalogo
    DadosSistemaDTO "1" o-- "0..*" ContaAssinante : contas
    DadosSistemaDTO "1" o-- "0..*" Avaliacao : avaliacoes
```

> **Observações (`// TODO`):**
> * `DadosSistemaDTO` prevê construtor, getters e setters para `catalogo`, `contas` e `avaliacoes`.
> * Arquivos previstos em `PersistenciaArquivoTexto`: `catalogo.txt`, `contas_perfis.txt` e `avaliacoes.txt` (delimitados por `;` com `BufferedWriter`/`BufferedReader`).

---

## 6. Módulos Player, Interface Gráfica e Inicialização (`player`, `ui`, `main` — RF05 & RF06)

Integra o ponto de entrada (`Main`), o controlador de telas (`TelaPrincipalController`) e o motor de reprodução com padrão **Observer** (`MotorReproducao` + `PlayerListener` + `EstadoPlayer`).

```mermaid
classDiagram
    direction TB

    class Main {
        +main(args: String[]) void$
    }

    class TelaPrincipalController {
        <<controller RF05>>
    }

    class MotorReproducao {
        -estadoAtual: EstadoPlayer
        -midiaEmReproducao: Midia
        -perfilAtivo: Perfil
        -minutoAtual: int
        -listeners: List~PlayerListener~
        +iniciarReproducao(perfil: Perfil, midia: Midia) void
        +pausar() void
        +retomar() void
        +avancar(minutos: int) void
        +retroceder(minutos: int) void
        +parar() void
        +finalizar() void
    }

    class PlayerListener {
        <<interface>>
        +onEstadoAlterado(novoEstado: EstadoPlayer, midiaAtual: Midia) void
        +onProgressoAtualizado(minutoAtual: int, duracaoTotal: int) void
        +onEpisodioAvancado(nomeProximoEpisodio: String) void
    }

    class EstadoPlayer {
        <<enumeration>>
        PARADO
        REPRODUZINDO
        PAUSADO
        FINALIZADO
    }

    class RepositorioCatalogo
    class GerenciadorUsuarios
    class ServicoAvaliacaoEFavoritos
    class GeradorRelatorios
    class PersistenciaService {
        <<interface>>
    }
    class Midia {
        <<abstract>>
    }
    class Perfil {
        <<abstract>>
    }
    class ClassificacaoIndicativaException {
        <<exception>>
    }

    Main ..> PersistenciaService : carrega dados
    Main ..> TelaPrincipalController : inicia UI

    TelaPrincipalController --> RepositorioCatalogo : RF01
    TelaPrincipalController --> GerenciadorUsuarios : RF02
    TelaPrincipalController --> ServicoAvaliacaoEFavoritos : RF03
    TelaPrincipalController --> GeradorRelatorios : RF03
    TelaPrincipalController --> PersistenciaService : RF04
    TelaPrincipalController --> MotorReproducao : RF06
    TelaPrincipalController ..> PlayerListener : observa eventos

    MotorReproducao --> EstadoPlayer : estadoAtual
    MotorReproducao --> Midia : midiaEmReproducao
    MotorReproducao --> Perfil : perfilAtivo
    MotorReproducao "1" o-- "0..*" PlayerListener : notifica
    MotorReproducao ..> ClassificacaoIndicativaException : throws RN02
    PlayerListener ..> EstadoPlayer
    PlayerListener ..> Midia
```

> **Observações (`// TODO`):**
> * Em `MotorReproducao`, os métodos `iniciarReproducao`, `pausar`, `avancar`, `retroceder` e `parar` já possuem assinaturas declaradas; `retomar()` e `finalizar()` estão especificados no bloco `// TODO` da classe (assim como o gerenciamento da lista `listeners`).
> * `TelaPrincipalController` prevê as conexões acima com todos os serviços do sistema e a atuação como observador (`PlayerListener`) das mudanças do player.

---

## 7. Hierarquia de Exceções (`br.ufc.cinetrinta.exceptions`)

Todas as exceções de negócio são **checked exceptions** (estendem `java.lang.Exception`), exigindo tratamento explícito pelas camadas chamadoras e pela interface gráfica.

```mermaid
classDiagram
    direction LR

    class Exception {
        <<java.lang>>
    }

    class LimitePerfisExcedidoException {
        <<exception>>
        +LimitePerfisExcedidoException(mensagem: String)
    }

    class ClassificacaoIndicativaException {
        <<exception>>
        +ClassificacaoIndicativaException(mensagem: String)
    }

    class ConsumoMinimoInsuficienteException {
        <<exception>>
        +ConsumoMinimoInsuficienteException(mensagem: String)
    }

    class FavoritoDuplicadoException {
        <<exception>>
        +FavoritoDuplicadoException(mensagem: String)
    }

    Exception <|-- LimitePerfisExcedidoException
    Exception <|-- ClassificacaoIndicativaException
    Exception <|-- ConsumoMinimoInsuficienteException
    Exception <|-- FavoritoDuplicadoException
```

| Exceção | Regra | Disparo previsto em | Motivo |
| :--- | :---: | :--- | :--- |
| `LimitePerfisExcedidoException` | **RN01** | `ContaAssinante.adicionarPerfil(...)` | Quantidade de perfis atingiu `plano.getLimiteMaximoPerfis()` (`1`, `3` ou `5`). |
| `ClassificacaoIndicativaException` | **RN02** | `GerenciadorUsuarios.validarAcessoEtario(...)` / `MotorReproducao.iniciarReproducao(...)` | `PerfilInfantil` tentando acessar ou reproduzir mídia com `idadeMinima > 12`. |
| `ConsumoMinimoInsuficienteException` | **RN03** | `ServicoAvaliacaoEFavoritos.registrarAvaliacao(...)` | Perfil assistiu a menos de **20%** de `midia.getDuracaoTotalMinutos()`. |
| `FavoritoDuplicadoException` | **RN03** | `ServicoAvaliacaoEFavoritos.adicionarAosFavoritos(...)` | Mídia já presente em `perfil.getMinhaListaFavoritos()`. |

# 🎬 CineTrinta — Sistema Integrado de Streaming e Gestão de Conteúdos Audiovisuais

Repositório oficial do projeto final da disciplina de **Programação Orientada a Objetos (TI0142)** — Curso de Engenharia da Computação — **Universidade Federal do Ceará (UFC)**.  
**Professor:** Fernando Antonio Mota Trinta

---

## 👥 Equipe e Divisão de Responsabilidades

Cada integrante possui seu próprio pacote isolado com os esqueletos das classes e comentários `// TODO` indicando o que deve ser implementado, permitindo o desenvolvimento **100% independente**:

| Integrante | Matrícula | Módulo / Requisitos | Pacote Principal (`src/br/ufc/cinetrinta/...`) |
| :--- | :---: | :--- | :--- |
| **Paulo Ícaro Matias Franco** *(Líder)* | 601549 | **RF05 e RF06:** Interface Gráfica e Motor de Reprodução (Player) | `player/`, `ui/`, `main/` |
| **Davi Osugi Alencar Ferreira Gomes** | 604474 | **RF01:** Gestão do Catálogo de Conteúdos (Filmes, Séries, Episódios) | `catalogo/` |
| **João Cauã Barbosa Silva** | 598714 | **RF02:** Gestão de Assinantes, Planos (RN01) e Perfis (RN02) | `usuarios/` |
| **Gustavo Nicolas Teixeira Pinheiro** | 605004 | **RF03:** Avaliações (RN03), Favoritos, Histórico e Relatórios | `engajamento/` |
| **Samuel Lucas Oliveira Santos** | 599736 | **RF04:** Armazenamento e Carga de Dados em Arquivos (Persistência) | `persistencia/` |

---

## 📂 Estrutura de Pastas do Projeto

```text
CineTrinta/
├── docs/
│   ├── diagrama-de-classes.md  # Diagramas UML detalhados por módulo (RF01-RF06 e Exceções)
│   └── fluxogramas.md          # Fluxogramas por caso de uso e máquina de estados do Player
├── src/
│   └── br/ufc/cinetrinta/
│       ├── catalogo/        # [RF01 - Davi Osugi] Hierarquia de Mídias, Filmes, Séries e Acervo
│       ├── usuarios/        # [RF02 - João Cauã] Contas, Planos de Assinatura e Perfis (Adulto/Kids)
│       ├── engajamento/     # [RF03 - Gustavo Nicolas] Avaliações, Favoritos, Histórico e Relatórios
│       ├── persistencia/    # [RF04 - Samuel Lucas] Salvamento e Leitura de Arquivos
│       ├── player/          # [RF06 - Paulo Ícaro] Motor de Reprodução e Controle de Estados
│       ├── ui/              # [RF05 - Paulo Ícaro] Telas da Interface Gráfica
│       ├── exceptions/      # Exceções Customizadas das Regras de Negócio (RN01, RN02, RN03)
│       └── main/            # Classe Principal de Inicialização (Main.java)
└── README.md
```

---

## ⚖️ Regras de Negócio e Exceções Customizadas

* **RN01 (Limite de Perfis por Plano de Assinatura):**
  * **Plano Básico:** até 1 perfil | **Plano Padrão:** até 3 perfis | **Plano Premium:** até 5 perfis.
  * Exceção lançada: `LimitePerfisExcedidoException` (pacote `exceptions`).
* **RN02 (Bloqueio Parental por Classificação Indicativa):**
  * Perfis na modalidade **Infantil (Kids)** são impedidos de acessar ou reproduzir títulos com classificação superior a **12 anos**.
  * Exceção lançada: `ClassificacaoIndicativaException` (pacote `exceptions`).
* **RN03 (Consumo Mínimo para Avaliação e Unicidade de Favoritos):**
  * Um perfil só pode avaliar uma obra (1 a 5 estrelas) se tiver assistido a pelo menos **20%** da duração total.
  * Um título não pode ser inserido em duplicidade na lista de favoritos do mesmo perfil.
  * Exceções lançadas: `ConsumoMinimoInsuficienteException` e `FavoritoDuplicadoException` (pacote `exceptions`).

---

## 🏗️ Arquitetura, Diagrama de Classes e Fluxograma

* 🧱 **[Diagramas de Classes UML Detalhados (`docs/diagrama-de-classes.md`)](docs/diagrama-de-classes.md)** — Visão modular completa de todos os 8 pacotes (`RF01`–`RF06` e `exceptions`).
* 🔀 **[Fluxogramas Detalhados e Máquina de Estados (`docs/fluxogramas.md`)](docs/fluxogramas.md)** — Fluxos passo a passo de Persistência, Login/`RN01`, Catálogo/`RN02`/Player (`EstadoPlayer`) e Engajamento/`RN03`.

### 🧱 Diagrama de Classes — Visão Geral da Arquitetura

```mermaid
classDiagram
    direction TB

    class Main
    class TelaPrincipalController {
        <<controller RF05>>
    }

    namespace Persistencia_RF04 {
        class PersistenciaService {
            <<interface>>
        }
        class PersistenciaArquivoTexto
        class DadosSistemaDTO
    }

    namespace Catalogo_RF01 {
        class RepositorioCatalogo
        class Midia {
            <<abstract>>
        }
        class Filme
        class Documentario
        class Serie
        class Temporada
        class Episodio
    }

    namespace Usuarios_RF02 {
        class GerenciadorUsuarios
        class ContaAssinante
        class TipoPlano {
            <<enumeration>>
        }
        class Perfil {
            <<abstract>>
        }
        class PerfilAdulto
        class PerfilInfantil
    }

    namespace Engajamento_RF03 {
        class ServicoAvaliacaoEFavoritos
        class Avaliacao
        class GeradorRelatorios
    }

    namespace Player_RF06 {
        class MotorReproducao
        class PlayerListener {
            <<interface>>
        }
        class EstadoPlayer {
            <<enumeration>>
        }
    }

    Main ..> PersistenciaService : carrega dados
    Main ..> TelaPrincipalController : inicia UI

    TelaPrincipalController --> RepositorioCatalogo : RF01
    TelaPrincipalController --> GerenciadorUsuarios : RF02
    TelaPrincipalController --> ServicoAvaliacaoEFavoritos : RF03
    TelaPrincipalController --> GeradorRelatorios : RF03
    TelaPrincipalController --> PersistenciaService : RF04
    TelaPrincipalController --> MotorReproducao : RF06
    TelaPrincipalController ..> PlayerListener : observa

    PersistenciaService <|.. PersistenciaArquivoTexto
    PersistenciaService ..> DadosSistemaDTO

    RepositorioCatalogo "1" o-- "0..*" Midia
    Midia <|-- Filme
    Midia <|-- Documentario
    Midia <|-- Serie
    Serie "1" *-- "0..*" Temporada
    Temporada "1" *-- "0..*" Episodio

    GerenciadorUsuarios "1" o-- "0..*" ContaAssinante
    ContaAssinante "*" --> "1" TipoPlano
    ContaAssinante "1" *-- "0..*" Perfil
    Perfil <|-- PerfilAdulto
    Perfil <|-- PerfilInfantil
    Perfil "0..*" o-- "0..*" Midia : favoritos

    ServicoAvaliacaoEFavoritos "1" o-- "0..*" Avaliacao
    ServicoAvaliacaoEFavoritos ..> Perfil
    ServicoAvaliacaoEFavoritos ..> Midia
    GeradorRelatorios ..> Midia
    GeradorRelatorios ..> ContaAssinante

    MotorReproducao --> EstadoPlayer
    MotorReproducao --> Midia
    MotorReproducao --> Perfil
    MotorReproducao "1" o-- "0..*" PlayerListener
```

> 👉 Veja os atributos, métodos, `Genero`, `ClassificacaoIndicativa` e a hierarquia completa das 4 exceções customizadas em **[`docs/diagrama-de-classes.md`](docs/diagrama-de-classes.md)**.

### 🔀 Fluxograma Geral do Sistema

```mermaid
flowchart TD
    ini(["Início: Main.main()"]) --> carga["RF04: PersistenciaService.carregarDados()<br/>Carrega catálogo, contas, perfis e avaliações"]
    carga --> login["RF05 / RF02: Tela de Login<br/>ou Cadastro de Conta"]
    login --> auth{"Autenticação<br/>válida?"}
    auth -->|"Não"| login
    auth -->|"Sim"| selPerfil["Selecionar Perfil ou Criar Novo<br/>(RN01: limite TipoPlano 1 / 3 / 5)"]
    selPerfil -->|"Limite excedido"| errRN01["LimitePerfisExcedidoException"] --> selPerfil
    selPerfil -->|"Perfil ativo"| menu{"Área acessada<br/>na UI"}

    menu -->|"Trocar Perfil / Logout"| selPerfil
    menu -->|"Admin / Relatórios<br/>(RF01 / RF03)"| admin["CRUD Catálogo + Top 10,<br/>Consumo/Gênero e Financeiro"] --> menu
    menu -->|"Vitrine do Catálogo<br/>(RF01)"| escolhe["Busca / filtra e<br/>seleciona Mídia"]

    escolhe --> acaoMidia{"Ação na<br/>Mídia"}
    acaoMidia -->|"Voltar ao menu"| menu

    acaoMidia -->|"Favoritar (RF03)"| chkFav{"Já nos favoritos?<br/>(RN03)"}
    chkFav -->|"Sim"| errFav["FavoritoDuplicadoException"] --> escolhe
    chkFav -->|"Não"| addFav["Adiciona em Minha Lista"] --> escolhe

    acaoMidia -->|"Avaliar (RF03)"| chk20{"Assistiu >= 20%<br/>da obra? (RN03)"}
    chk20 -->|"Não"| err20["ConsumoMinimoInsuficienteException"] --> escolhe
    chk20 -->|"Sim"| salvaAval["Salva Avaliação e<br/>recalcula média"] --> escolhe

    acaoMidia -->|"Assistir (RF06)"| chkIdade{"PerfilInfantil e<br/>classificação > 12? (RN02)"}
    chkIdade -->|"Sim"| errRN02["ClassificacaoIndicativaException"] --> escolhe
    chkIdade -->|"Não"| player["MotorReproducao: substitui sessão,<br/>retoma progresso e notifica PlayerListener"]
    player --> fimMidia{"Fim de episódio<br/>de Série?"}
    fimMidia -->|"Sim"| player
    fimMidia -->|"Não"| escolhe

    menu -->|"Encerrar App"| salvar["RF04: PersistenciaService.salvarDados()<br/>Grava estado em arquivos texto"]
    salvar --> fim(["Fim"])
```

> 👉 Veja os fluxogramas separados por módulo e a **Máquina de Estados (`EstadoPlayer`)** em **[`docs/fluxogramas.md`](docs/fluxogramas.md)**.

---

## 🚀 Como Trabalhar no Seu Módulo (Fluxo Git Recomendado)

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/icaro-arch/CineTrinta.git
   cd CineTrinta
   ```
2. **Criar uma branch para o seu módulo (opcional, mas recomendado):**
   ```bash
   git checkout -b feature/rf01-catalogo
   ```
3. **Abrir a pasta do seu pacote:**
   * Leia os comentários `// TODO:` dentro dos arquivos `.java` do seu pacote.
   * Implemente os atributos, construtores, getters/setters e a lógica dos métodos indicados.
4. **Enviar suas alterações:**
   ```bash
   git add .
   git commit -m "feat: implementa classes do modulo RF01"
   git push origin main
   ```

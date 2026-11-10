# Contador de Truco

Aplicativo Android desenvolvido em Java para auxiliar na contagem de pontos de partidas de truco. O projeto permite identificar os jogadores, definir a pontuação máxima da partida, atualizar o placar durante o jogo e consultar partidas registradas.

## Funcionalidades

- **Identificação dos jogadores:** informe o nome dos dois jogadores. Caso os nomes não sejam preenchidos, o aplicativo utiliza nomes padrão.
- **Definição da pontuação máxima:** configure a quantidade de pontos necessária para encerrar a partida.
- **Contagem de pontos:** adicione ou remova pontos individualmente para cada jogador.
- **Validação do placar:** impede pontuações negativas e limita a contagem à pontuação máxima definida.
- **Identificação do vencedor:** ao atingir a pontuação máxima, o aplicativo informa quem venceu e bloqueia os controles de pontuação.
- **Finalização da partida:** salva data, jogadores, placar e vencedor.
- **Histórico de partidas:** apresenta os registros salvos, incluindo data, resultado e vencedor.

## Tecnologias utilizadas

- **Java 11** — linguagem utilizada no desenvolvimento da aplicação.
- **Android SDK** — plataforma de desenvolvimento Android.
- **SQLite** — banco de dados local para armazenamento das partidas.
- **AndroidX AppCompat e Activity** — componentes de compatibilidade e gerenciamento de telas.
- **Material Components** — componentes visuais, incluindo mensagens com `Snackbar`.
- **ConstraintLayout** — organização dos elementos das interfaces.
- **Gradle** — sistema de build e gerenciamento de dependências.

## Estrutura do projeto

```text
calculadoraSimples/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/calculadorasimples/
│       │   │   ├── activity/
│       │   │   │   └── MainActivity.java
│       │   │   ├── DAO/
│       │   │   │   └── PartidaDAO.java
│       │   │   ├── dbHelper/
│       │   │   │   └── ConexaoSQLite.java
│       │   │   └── model/
│       │   │       ├── HistoricoActivity.java
│       │   │       └── Partida.java
│       │   ├── res/       # Layouts, textos, temas e recursos visuais
│       │   └── AndroidManifest.xml
│       └── test/          # Testes unitários
├── gradle/
├── build.gradle
└── settings.gradle
```

## Como executar

1. Instale o [Android Studio](https://developer.android.com/studio).
2. Clone este repositório:

   ```bash
   git clone https://github.com/luisabassi/Contador-de-Truco.git
   ```

3. Abra a pasta do projeto no Android Studio.
4. Aguarde a sincronização do Gradle e a instalação das dependências.
5. Inicie um emulador Android ou conecte um dispositivo físico com a depuração USB habilitada.
6. Execute a configuração `app` pelo botão **Run** do Android Studio.

## Requisitos

- Android Studio com suporte ao Android Gradle Plugin utilizado no projeto.
- Android SDK 36 (compile/target SDK).
- Dispositivo ou emulador com Android 10 (API 29) ou superior.

## Persistência dos dados


As partidas são armazenadas localmente no dispositivo por meio do SQLite. O acesso aos registros é organizado pela classe `PartidaDAO`, enquanto `ConexaoSQLite` gerencia a criação e a conexão com o banco de dados.

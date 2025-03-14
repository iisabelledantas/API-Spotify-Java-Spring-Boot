# API Spotify - Java & Spring Boot

&emsp; Este projeto realiza a integração com a API do Spotify, permitindo a execução 
de requisições relativas a músicas, artistas e suas respectivas popularidades.
Os dados obtidos são, então, armazenados em um banco de dados, possibilitando análises
e consultas posteriores. Além disso, o projeto também se conecta à API do Gemini,
fornecendo ao usuário uma breve descrição sobre o cantor. O desenvolvimento desta aplicação foi realizado utilizando a 
linguagem de programação Java 17, o framework Spring Boot, o gerenciador de 
dependências Maven, o banco de dados PostgreSQL.

&emsp; Importante ressaltar que o projeto foi concebido com o objetivo de promover
o aprendizado e a consolidação de conhecimentos nas áreas de integração de APIs,
desenvolvimento de backend e manipulação de dados.

## Instalação

Para rodar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/seu-projeto.git
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd seu-projeto
   ```
3. Configure as credenciais da API do Spotify no `ObterToken.java`:
   ```java
    private static final String CLIENT_ID = System.getenv("CLIENT_ID_SPOTIFY");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET_SPOTIFY");
   ```
4. Configure as credenciais da API do GEMINI no `ConsultaGemini.java`:
   ```java
    .apiKey(System.getenv("GEMINI_KEY"))
   ```

5. Configure o banco de dados no `application.properties`:
   ```properties
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_SENHA}
   ```

## Ações Disponíveis 

&emsp; Após a execução da aplicação, estarão disponíveis cinco ações para o usuário interagir com a plataforma, conforme descrito abaixo:

1. **Buscar música:** Pesquisar uma música utilizando a API do Spotify e armazenar suas informações no banco de dados.
2. **Listar músicas:** Exibir as músicas salvas no banco de dados.
3. **Listar artistas:** Exibir os artistas salvos no banco de dados.
4. **Buscar músicas por artista:** Com base nas músicas armazenadas no banco de dados, realizar uma busca de músicas relacionadas a um artista específico.
5. **Buscar dados sobre o artista:** Pesquisar a descrição de um cantor utilizando a IA Gemini.



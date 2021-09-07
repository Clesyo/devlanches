# Api - devlanches
<p>API para gerenciamento de pedido de uma Lanchonete</p>

<h4 align="center"> 
	🚧  DevLanches 🚀 Em construção...  🚧
</h4>

### Funcinalidades

- [x] Cadastro de gestor
- [x] Cadastro de clientes
- [x] Cadastro de produtos
- [x] Gerencia pedidos(cria novo, altera status e cancela pedido)

### Como rodar
* Para download do projeto siga o passa abaixo:
`````bash
git clone https://github.com/Clesyo/devlanches.git
`````
* Usando as IDEs Eclipse ou STS - SpringToolSuite, faça a importação do projeto.
* Configura o arquivo **application.properties** com as informação da sua base de dados.

### Testando a aplicação usando a Swagger

* Execute seu projeto F11, após subir a aplicação acesse o endereço abaixo com com seu navegador

`````bash
http://localhost:8080/swagger-ui.html
`````
### Gerando o arquivo JAR

* Abra o CMD no raiz do projeto e execute o seguinte comando:

  ````bash
  mvn clean packge
  ````

* Após a concluir a execução, consulte a pasta **target** para visualizar o arquivo .jar.

### Executando o arquivo JAR

* Acesse a pasta **target**, e execute o comando abaixo:

  ````bash
  java -jar nomedoarquivo.jar
  ````
### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [Swagger2](https://swagger.io/)

## Autor
<a href="https://github.com/Clesyo">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/5548421?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Clesyo Slva</b></sub></a> <a href="href="https://github.com/Clesyo"</a>

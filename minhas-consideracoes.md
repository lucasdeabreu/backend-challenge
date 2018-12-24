O desafio dizia que a companhia estava migrando de um monolítico para micro serviços, então comecei implementando um serviço no caso o de Orders e então comecei a implementar alguns patterns para o micro serviços como Gateway, Registry e Distributed configuration no final implementei o serviço de Stores. 

Não foi possível finalizar/completar o desafio como eu planejei por falta de tempo, ainda falta implementar o serviço de Payment e Refund. Sem mencionar outros patterns para um projeto micro serviços como Circuit Breaker e Distributed Tracing.

#### A respeito da parte Nice to have features

* Asynchronous processing: Não implementei a solução, poderia utilizar @Async feature mas minha ideia era utilizar Spring React para alcançar o resultado.
* Database: Utilizei o Mysql e Flyway para gerenciar as versões, no futuro seria trocado pelo MongoDB por trabalhar melhor com o Spring React.
* Docker: Configurei o projeto para ser possível ser executado em um ambiente de container.
* AWS: Não planejei nenhum uso da AWS mas poderíamos usar o DynamoDB ou SQS por exemplo.
* Security: Não implementei nenhuma solução de segurança, mas iria utilizar o Spring Cloud Security.
* Swagger: Implementei a integração básica com o Spring.
* Clean Code: Tentei integrar o código mais legível possível.

#### Detalhes que não implementei
* Não implementei nenhum handler de exceptions para melhorar como são retornadas ao usuário.
* Gostaria de incrementar os testes para cobrir mais casos.
* Não implementei um versionamento de API.
* A integração com o Swagger precisa ser melhorada para disponibilizar mais informações na documentação.

**Por falta de tempo não foi possível entregar tudo que planejei, mas espero ter agradado.** 

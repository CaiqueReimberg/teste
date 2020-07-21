# Teste Prestadores de Serviços

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<p>Este projeto visa apresentar uma solução paliativa para um caso de busca prestadores de serviços próximos da localização fornecida.</p>
<p>O projeto foi feito utilizando: SpringBoot, Lombok, Java, WireMock</p>
<p>Para a utilização da api de geolocalização não foi utilizada a do google maps, apenas foi utilizado uma api externa para que fosse feita uma integração utilizando uma api rest simples</p>
<p>O wiremock inicializa assim que a aplicação é inicia, porém é apenas para demonstrar um mock e os possíveis json que seriam retornados caso fosse implementado testes ou fosse feito a implementação em cima dos resultados do mock</p>
<p>Para teste da requisição foi testado apenas pela requisição.
Exemplo: http://localhost:8080/obterPrestadoresSaude/1/1/OFTALMOLOGIA</p>
</body>
</html>

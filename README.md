# TDD – Desenvolvimento de Software Guiado por Testes - ITA

* Repositório referente ao conteúdo de estudos do curso TDD – Desenvolvimento de Software Guiado por Testes
oferecido pelo Instituto Tecnológico de Aeronáutica (ITA)

### Semana 1

*What if we could test our steps before actually making our decisions?*

<p align="justify">
Na vida real, infelizmente, não é possível utilizar dessa estratégia para tomar nossas decisões. Porém, quando se trata no desenvolvimento/escrita de códigos é possível aplicar essa estratégia.
<br><br>
O TDD na sua essência nada mais significa escrever o teste antes mesmo do desenvolvimento do código, o que na verdade serve como orientação de como o código precisa se "comportar".
</p>
<br>

**E como seria um ciclo de desenvolvimento utilizando TDD?**

<p align="center">
  <img src="https://media.licdn.com/dms/image/C5612AQEgUhEh3bsfEA/article-cover_image-shrink_600_2000/0/1602842572875?e=2147483647&v=beta&t=8SIZbd5dIYb3rJ4fpZMyX5ZYiYsBKKShfiJycEl7Db0" alt="">
</p>

<p align="justify">
De forma simplificada, o primeiro passo é escrever testes que irão falhar e está tudo bem, até por que não temos o corpo do código para que os testes de fato possam avaliar.
<br><br>
O segundo passo é começar a escrever o corpo do código para que os testes passem.
<br><br>
O terceiro passo é refatorar buscando a melhoria do código e verificando o comportamento dos testes a cada alteração.
Esse ciclo irá se repetir até que o código alcance o nível de satisfação, apresentando uma boa estrutura e preferencialmente seguindo as boas práticas de um código/arquitetura limpa.
</p>


[Hands on!](https://github.com/Lukasveiga/curso-tdd-ita/tree/main/src/tdd/ita/semana01/handson)

### Semana 2

<div align="center">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHkH69dUHyhfycQNJcI_Dk_nz3vC5-yneQMg&usqp=CAU" alt="">
</div>
<p align="center"><i>O chapéu do TDD</i></p>

<p align="justify">
A ideia principal do chapéu é a de realizar as etapas do TDD de cada vez. Ou seja, você não pode/deveria usufruir da "sombra" de todas as abas do chapéu de uma só vez.
<br><br>
Relembrando:
<br><br>
🔴 Na etapa vermelha temas a construção de testes que irão falhar, nesse momento é iniciada a construção das "diretrizes" que as funções/classes precisam respeitar.
<br><br>
🟢 Já na etapa verde, temos a construção do código mais simples possível e consiga passar nos testes estabelecidos na etapa vermelha.
<br><br>
🔵 Na etapa azul, que não necessariamente representa o fim do ciclo, temos a refatoração do código, que a princípio não foi desenvolvido seguindo boas práticas de código/arquitetura limpa. O que faz sentido, pois na etapa verde o objetivo principal é de que o código respeite os comportamentos estabelecidos pelos testes, sem se importar com a qualidade do código.
Nesse momento é realizada toda refatoração necessária para se alcançar um código de qualidade e tendo os testes como segurança de que o comportamento esperado permaneça a cada alteração.
</p>

[Hands on!](https://github.com/Lukasveiga/curso-tdd-ita/tree/main/src/tdd/ita/semana02/handson)











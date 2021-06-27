# Desafio de 1 fim de semana Let´s Code

## Star Wars Resistence Social Network
### Descrição do problema

O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e 
eliminar os rebeldes. <BR>
Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar 
recursos entre os rebeldes. <BR>
### Requisitos <BR>
Você irá desenvolver uma API REST (sim, nós levamos a arquitetura da aplicação a sério mesmo no meio 
de uma guerra), ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles 
possuem. <BR>
#### • Adicionar rebeldes
Um rebelde deve ter um nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao 
qual faz parte). <BR>
Um rebelde também possui um inventário que deverá ser passado na requisição com os recursos em sua 
posse. <BR>
#### • Atualizar localização do rebelde <BR>
Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova 
latitude/longitude/nome (não é necessário rastrear as localizações, apenas sobrescrever a última é o 
suficiente). <BR>
#### • Reportar o rebelde como um traidor <BR>
Eventualmente algum rebelde irá trair a resistência e se aliar ao império. Quando isso acontecer, nós 
precisamos informar que o rebelde é um traidor. <BR>
Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário, nem 
ser exibido em relatórios. <BR>
Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes reportarem a traição.
Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis (eles não podem ser 
negociados com os demais). <BR>
#### • Rebeldes não podem Adicionar/Remover itens do seu inventário <BR>
Seus pertences devem ser declarados quando eles são registrados no sistema. Após isso eles só poderão 
mudar seu inventário através de negociação com os outros rebeldes. <BR>
#### • Negociar itens <BR>
Os rebeldes poderão negociar itens entre eles. <BR>
Para isso, eles devem respeitar a tabela de preços abaixo, onde o valor do item é descrito em termo de 
pontos. <BR>
Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 
2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3). <BR>
A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro. <BR>
  <BR>
  Item    Pontos <BR>
1 Arma    4 pontos <BR>
1 Munição 3 pontos <BR>
1 Água    2 pontos <BR>
1 Comida  1 ponto <BR><BR>
### • Relatórios <BR>
A API deve oferecer os seguintes relatórios:<BR>
1. Porcentagem de traidores. <BR>
2. Porcentagem de rebeldes. <BR>
3. Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde). <BR>
4. Pontos perdidos devido a traidores. <BR><BR>
### Notas <BR><BR>
1. Deverá ser utilizado Java, Spring boot, Spring Data, Hibernate (pode ser usado o banco de dados H2) 
e como gerenciador de dependência Maven ou Gradle. <BR>
2. Não será necessário autenticação. <BR>
3. Nós ainda nos preocupamos com uma programação adequada (código limpo) e técnicas de 
arquitetura, você deve demonstrar que é um digno soldado da resistência através das suas 
habilidades. <BR>
4. Não esqueça de minimamente documentar os endpoints da sua API e como usa-los. <BR>
5. Sua API deve estar minimamente coberta por testes (Unitários e/ou integração). <BR>
6. Da descrição acima você pode escrever uma solução básica ou adicionar requisitos não descritos. 
Use seu tempo com sabedoria; Uma solução ótima e definitiva pode levar muito tempo para ser 
efetiva na guerra, então você deve trazer a melhor solução possível, que leve o mínimo de tempo, 
mas que ainda seja capaz de demonstrar suas habilidades e provar que você é um soldado valioso 
para a resistência. <BR>
7. Comente qualquer dúvida e cada decisão tomada <BR>

# Manipulação de matrizes utilizando Master/Worker
Exercício proposto na aula de Engenharia de Software III sobre aplicação do padrão Master/Worker
## Proposta
Realizar a leitura de 2 arquivos, que contêm matrizes de ordem 1000x1000 e efetuar o seguinte cálculo:
#### C = B * (A + Bt)
onde * indica multiplicação de matrizes, + a soma e Bt a matriz transposta de B. A matriz resultado C deve ser gravada em um novo arquivo
## Arquitetura
Foi utilizada a arquitetura Master/Worker, que oferece suporte a computação paralela, onde o trabalho é dividido em subtarefas que
são processadas independentemente. Neste exercício, a divisão de tarefas foi feita na soma das matrizes A e Bt. Para realizar a
multiplicação inicialmente foi utilizado o algoritmo ijk, a implementação simples e direta para multiplicação de matrizes.
```
for (int i =0; i < matrizB.size(); i++) {
		for (int j = 0; j < matrizB.get(i).size(); j++) {
		    int soma = 0;
		    for (int k = 0; k < matrizB.get(i).size(); k++) {
		        soma += (Integer.parseInt(matrizB.get(i).get(k)) * matrizSoma.get(k).get(j));
		    }
		    matrizC[i][j] = soma;
		}
}
```
## Testes
Para a realização dos testes abaixo, foi utilizada uma máquina com com um processor Intel® Core™ i7-7500U uma CPU de @ 2.70Ghz,
2901 Mhz possuindo 2 núcleos e 4 processadores lógicos.

Levando em consideração que o tempo de execução do programa sem a implementação do Master/Worker foi de 65,459 segundos, o gráfico
a seguir mostra o tempo de execução utilizando Master/Worker com o algoritmo ijk, de acordo com o número de Workers aplicados:

![grafico1](https://user-images.githubusercontent.com/40436803/59147563-9f95a500-89d3-11e9-9d0e-15a78bc0566b.PNG)

Posteriormente, foi utilizado o algoritmo ikj para otimização da multiplicação de matrizes. O algoritmo ikj é como o algoritmo ijk mas com 
2 dos 3 loops for trocados.
```
for (int i = 0; i < matrizB.size(); i++) {
    for (int k = 0; k < matrizB.get(i).size(); k++) {
        for (int j = 0; j < matrizB.get(i).size(); j++) {
            matrizC[i][j] += (Integer.parseInt(matrizB.get(i).get(k)) * matrizSoma.get(k).get(j));
        }
    }
}
```
Os resultados obtidos com a substituição do algoritmo foram: 

![grafico2](https://user-images.githubusercontent.com/40436803/59147628-89d4af80-89d4-11e9-90cd-93347d6ec7ac.PNG)

Como é possível observar, utilizando o algoritmo ikj, houve uma redução de 17,244 segundos na execução(levando em consideração
o primeiro resultados de ambos os testes, utilizando 2 workers), ou seja, aproximadamente 50% a menos do tempo de execução.

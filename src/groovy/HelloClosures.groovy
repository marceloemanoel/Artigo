//-Definição como parâmetro de método+
def forEach(int i, Closure yield){
    for(x in 1..i) yield(x)
}

/*-Definição como último argumento do método\\number é um argumento da closure\\o sinal \url{->}determina o início das chamadas na closure+*/
forEach(3) {number -\url{->}+ println number} // imprime 1, 2, 3

//-Definição da closure e armazenamento em uma variável+
def elevarAoQuadrado = { println it * it }
//-Repassar a closure para o método+
forEach(3, elevarAoQuadrado) //imprime 1, 4, 9
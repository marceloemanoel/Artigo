def mapaVazio = [:]
println mapaVazio.size() //0

def mapaComValores = ["filme":"Star Wars", "jogo":"Legend of Zelda"]
println mapaComValores.size() //2
println mapaComValores."jogo" //"Legend of Zelda"
println mapaComValores["jogo"] //"Legend of Zelda"

mapaComValores["jogo"] = "Resident Evil"
println mapaComValores["jogo"] //"Resident Evil"
println mapaComValores.get("jogo") //"Resident Evil"

mapaComValores.linguagem = "Groovy"
println mapaComValores.size() //3
println mapaComValores['linguagem'] //Groovy
println mapaComValores.get("livro", "Clean Code") //Clean Code
println mapaComValores.size() //4
println mapaComValores //[filme:"Star Wars", jogo: "Resident Evil", linguagem: "Groovy", livro: "Clean Code"]

//iteracao num mapa

mapaComValores.each { entry ->
    println "${entry.key}=${entry.value}" //filme=Star Wars
                                          //jogo=Resident Evil
                                          //linguagem=Groovy
                                          //livro=Clean Code
}

mapaComValores.each { key, value ->
    println "${key}=${value}" //filme=Star Wars
                              //jogo=Resident Evil
                              //linguagem=Groovy
                              //livro=Clean Code
}

for (par in mapaComValores) {
    println "${par.key}=${par.value}"       //filme=Star Wars
                                          //jogo=Resident Evil
                                          //linguagem=Groovy
                                          //livro=Clean Code
}
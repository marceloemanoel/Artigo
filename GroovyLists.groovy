def listaVazia = []

def listaComElementos = [ 1, 2, 3, 4]
println listaComElementos.class.name // java.util.ArrayList
println listaComElementos.size // 4

listaComElementos << 5 // Adiciona o numero 5 a lista
println listaComElementos //[1, 2, 3, 4, 5]
println listaComElementos.size // 5

listaComElementos.add 6 // Adiciona o numero 6 a lista
println listaComElementos //[1, 2, 3, 4, 5, 6]
println listaComElementos.size // 6


// Iterar sobre a lista
listaComElementos.each {println it} // 1
                                    // 2
                                    // 3
                                    // 4
                                    // 5
                                    // 6
// Utilizacao atraves de indices
println listaComElementos[1] // 2
println listaComElementos.get(1) // 2
listaComElementos[1] = 3
println listaComElementos // [1, 3, 3, 4, 5, 6]

listaComElementos.remove 1
println listaComElementos // [1, 3, 4, 5, 6]
listaComElementos -= 6
println listaComElementos // [1, 3, 4, 5]

listaComElementos += 7
println listaComElementos // [1, 3, 4, 5, 7]

println listaComElementos[-1] // 7
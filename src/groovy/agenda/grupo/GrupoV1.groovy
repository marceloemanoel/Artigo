package agenda

class Grupo {
    
    String nome

    static constraints = {
        nome(blank:false, nullable:false)
    }
}

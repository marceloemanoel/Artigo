package agenda

class PropriedadeContato {
    
    String nome;
    String valor;
    
    static constraints = {
        nome(nullable: false, blank: false)
    }
}

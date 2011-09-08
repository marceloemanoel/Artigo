package agenda

class Grupo {
    
    String nome
    
	static hasMany = [contatos : Contato]

    static constraints = {
        nome(blank:false, nullable:false)
    }
}

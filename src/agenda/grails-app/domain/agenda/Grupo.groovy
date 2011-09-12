package agenda

class Grupo {
    
    String nome
    
	/**
	* Relacionamento entre Grupo e contatos
	*/
    static hasMany = [contatos : Contato]

    static constraints = {
        nome(blank:false, nullable:false)
    }
}

package agenda

class Contato {

    String nome;
    String email;

    static hasMany = [propriedades : PropriedadeContato]

    static constraints = {
        nome(blank:false, nullable:false)
        email(email:true, blank:false, nullable:false, unique:true)
    }
}

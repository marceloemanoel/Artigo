package agenda

class NumeroCelular extends PropriedadeContato{
    
    public NumeroCelular(){
        this.nome = "celular"
    }
    
    static constraints = {
        valor(blank:false, nullable: false, matches: /[+]?\d{2}\s[(]?\d{2}[)]?\s?\d{4}\s?-?\s?\d{4}/)
    }
}

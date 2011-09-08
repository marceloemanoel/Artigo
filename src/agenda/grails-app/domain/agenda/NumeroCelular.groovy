package agenda

class NumeroCelular extends PropriedadeContato{
    
	public NumeroCelular(){
		this.nome = getName();
	}

    public String getName(){
        "celular"
    }
    
    public String setName(String name){
        //Devemos ignorar qualquer alteração.
    }
    
    static constraints = {
        valor(blank:false, nullable: false, matches: /[+]?\d{2}\s[(]?\d{2}[)]?\s?\d{4}\s?-?\s?\d{4}/)
    }
}

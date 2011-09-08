package agenda

class WebSite extends PropriedadeContato{
    
	public WebSite(){
		this.nome = getName()
	}

    public String getName(){
        "website"
    }
    
    public String setName(String name){
        //Devemos ignorar qualquer alteração.
    }
    
    static constraints = {
        valor(blank:false, nullable: false, url: true)
    }
}

package agenda

class WebSite extends PropriedadeContato{
    
    public WebSite(){
        this.nome = getNome()
    }
    
    public String setNome(String nome){
        //Devemos ignorar qualquer alteração.
    }
    
    static constraints = {
        valor(blank:false, nullable: false, url: true)
    }
}

package agenda

class WebSite extends PropriedadeContato{

    public WebSite(){
        this.nome = "website"
    }
    
    static constraints = {
        valor(blank:false, nullable: false, url: true)
    }

}

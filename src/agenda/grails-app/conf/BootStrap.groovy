import agenda.Contato;
import agenda.NumeroCelular;
import agenda.WebSite;
import agenda.Grupo;

class BootStrap {

    def init = { servletContext ->
        def todosContatos = new Grupo(nome: "todos")
        todosContatos.save()
        
        15.times{index ->
            def contato = new Contato(nome: "contato${index}",
                                      email: "contato${index}@servidor.com")
            contato.addToPropriedades(new WebSite(valor: "http://contato${index}.com"))
            contato.addToPropriedades(new NumeroCelular(valor: "+55 (85) 8888-8888"))
            contato.save()
            todosContatos.addToContatos(contato);
        }
        
        todosContatos.save()
    }
    def destroy = {
    }
}

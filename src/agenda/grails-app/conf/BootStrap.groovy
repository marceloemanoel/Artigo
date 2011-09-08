import agenda.Contato;
import agenda.NumeroCelular;
import agenda.WebSite;
import agenda.Grupo;

class BootStrap {

    def init = { servletContext ->
		def todosContatos = new Grupo(nome: "todos")
		todosContatos.save()
		
		def contato1 = new Contato(nome: "contato1",
								   email: "contato1@servidor.com"
								  )
		contato1.addToPropriedades(new WebSite(valor: "http://contato1.com"))
		contato1.addToPropriedades(new NumeroCelular(valor: "+55 (85) 8888-8888"))
		contato1.save()
		
		todosContatos.addToContatos(contato1);
		todosContatos.save()
    }
    def destroy = {
    }
}

package agenda

class GrupoController {
    ...
    
    def associarContato = {
        def contato = Contato.get(params.contato.id)
        def grupo = Grupo.get(params.grupo.id)
        
        grupo.addToContatos(contato)
        grupo.save()
    }    
    ...
}

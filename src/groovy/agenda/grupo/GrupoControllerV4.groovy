def associarContato = {
    def contato = Contato.get(params.contato.id)
    def grupo = Grupo.get(params.grupo.id)
    
    if(grupo) {
      if(grupo.contatos.contains(contato)){
        grupo.removeFromContatos(contato)
      }
      else{
        grupo.addToContatos(contato)
      }
      grupo.save()
    }
    render (status: 200)
}

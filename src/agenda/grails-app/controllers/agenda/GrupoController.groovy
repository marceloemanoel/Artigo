package agenda

class GrupoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
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
    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [grupoInstanceList: Grupo.list(params), grupoInstanceTotal: Grupo.count()]
    }

    def create = {
        def grupoInstance = new Grupo()
        grupoInstance.properties = params
        return [grupoInstance: grupoInstance]
    }

    def save = {
        def grupoInstance = new Grupo(params)
        if (grupoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.id])}"
            redirect(action: "show", id: grupoInstance.id)
        }
        else {
            render(view: "create", model: [grupoInstance: grupoInstance])
        }
    }

    def show = {
        def grupoInstance = Grupo.get(params.id)
        if (!grupoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
            redirect(action: "list")
        }
        else {
            [grupoInstance: grupoInstance]
        }
    }

    def edit = {
        def grupoInstance = Grupo.get(params.id)
        if (!grupoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [grupoInstance: grupoInstance]
        }
    }

    def update = {
        def grupoInstance = Grupo.get(params.id)
        if (grupoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (grupoInstance.version > version) {
                    
                    grupoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'grupo.label', default: 'Grupo')] as Object[], "Another user has updated this Grupo while you were editing")
                    render(view: "edit", model: [grupoInstance: grupoInstance])
                    return
                }
            }
            grupoInstance.properties = params
            if (!grupoInstance.hasErrors() && grupoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.id])}"
                redirect(action: "show", id: grupoInstance.id)
            }
            else {
                render(view: "edit", model: [grupoInstance: grupoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def grupoInstance = Grupo.get(params.id)
        if (grupoInstance) {
            try {
                grupoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])}"
            redirect(action: "list")
        }
    }
}

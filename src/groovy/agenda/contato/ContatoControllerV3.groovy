package agenda

class ContatoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [contatoInstanceList: Contato.list(params), contatoInstanceTotal: Contato.count()]
    }

    def create = {
        def contatoInstance = new Contato()
        contatoInstance.properties = params
        return [contatoInstance: contatoInstance]
    }

    def save = {
        def contatoInstance = new Contato(params)
        if (contatoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'contato.label', default: 'Contato'), contatoInstance.id])}"
            redirect(action: "show", id: contatoInstance.id)
        }
        else {
            render(view: "create", model: [contatoInstance: contatoInstance])
        }
    }

    def show = {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
            redirect(action: "list")
        }
        else {
            [contatoInstance: contatoInstance]
        }
    }

    def edit = {
        def contatoInstance = Contato.get(params.id)
        if (!contatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [contatoInstance: contatoInstance]
        }
    }

    def update = {
        def contatoInstance = Contato.get(params.id)
        if (contatoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (contatoInstance.version > version) {
                    
                    contatoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'contato.label', default: 'Contato')] as Object[], "Another user has updated this Contato while you were editing")
                    render(view: "edit", model: [contatoInstance: contatoInstance])
                    return
                }
            }
            contatoInstance.properties = params
            if (!contatoInstance.hasErrors() && contatoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contato.label', default: 'Contato'), contatoInstance.id])}"
                redirect(action: "show", id: contatoInstance.id)
            }
            else {
                render(view: "edit", model: [contatoInstance: contatoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def contatoInstance = Contato.get(params.id)
        if (contatoInstance) {
            try {
                contatoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contato.label', default: 'Contato'), params.id])}"
            redirect(action: "list")
        }
    }
}

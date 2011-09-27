package agenda

class PropriedadeContatoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [propriedadeContatoInstanceList: PropriedadeContato.list(params), propriedadeContatoInstanceTotal: PropriedadeContato.count()]
    }

    def create = {
        def propriedadeContatoInstance = new PropriedadeContato()
        propriedadeContatoInstance.properties = params
        return [propriedadeContatoInstance: propriedadeContatoInstance]
    }

    def save = {
        def propriedadeContatoInstance = new PropriedadeContato(params)
        if (propriedadeContatoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), propriedadeContatoInstance.id])}"
            redirect(action: "show", id: propriedadeContatoInstance.id)
        }
        else {
            render(view: "create", model: [propriedadeContatoInstance: propriedadeContatoInstance])
        }
    }

    def show = {
        def propriedadeContatoInstance = PropriedadeContato.get(params.id)
        if (!propriedadeContatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
            redirect(action: "list")
        }
        else {
            [propriedadeContatoInstance: propriedadeContatoInstance]
        }
    }

    def edit = {
        def propriedadeContatoInstance = PropriedadeContato.get(params.id)
        if (!propriedadeContatoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [propriedadeContatoInstance: propriedadeContatoInstance]
        }
    }

    def update = {
        def propriedadeContatoInstance = PropriedadeContato.get(params.id)
        if (propriedadeContatoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (propriedadeContatoInstance.version > version) {
                    
                    propriedadeContatoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'propriedadeContato.label', default: 'PropriedadeContato')] as Object[], "Another user has updated this PropriedadeContato while you were editing")
                    render(view: "edit", model: [propriedadeContatoInstance: propriedadeContatoInstance])
                    return
                }
            }
            propriedadeContatoInstance.properties = params
            if (!propriedadeContatoInstance.hasErrors() && propriedadeContatoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), propriedadeContatoInstance.id])}"
                redirect(action: "show", id: propriedadeContatoInstance.id)
            }
            else {
                render(view: "edit", model: [propriedadeContatoInstance: propriedadeContatoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def propriedadeContatoInstance = PropriedadeContato.get(params.id)
        if (propriedadeContatoInstance) {
            try {
                propriedadeContatoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'propriedadeContato.label', default: 'PropriedadeContato'), params.id])}"
            redirect(action: "list")
        }
    }
}

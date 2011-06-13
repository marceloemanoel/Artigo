package clubemais

import grails.converters.JSON

import org.compass.core.engine.SearchEngineQueryParseException

class LogradouroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def pesquisaLogradouroPorCep = {
        def logradouro = Logradouro.findByCep(params.cep)
        def dados = []

        if (logradouro) {
            dados = [
                     '''
   						logradouro: logradouro.nome,
                        bairro: logradouro.bairro.nome,
                        bairro_id: logradouro.bairro.id,
                        municipio: logradouro.bairro.municipio.nome
					'''
                    ]

            dados = [result: dados]
            render dados as JSON
        }
    }

    def pesquisaLogradouroPorNomeNoMunicipio ={
        def results = []
        if(params.municipio && params.logradouro){
            def bytes = params.logradouro.getBytes("utf-8");
            params.logradouro = new String(bytes, "utf-8");
            def resultado = search(params)

            def municipio = Municipio.findByCodigo(params.municipio)
            def bairros = Bairro.findAllByMunicipio(municipio);
            
            if(resultado){
                results = Logradouro.findAll("from Logradouro as log where log.id in (:idList) and log.bairro.municipio.id = :municipio_id",[idList:resultado.collect {it.id}, municipio_id:municipio.id])
            }
        }
        render results.collect { ["id": it.id, logradouro: it.nome, cep:it.cep, bairro:it.bairro.nome, bairroId:it.bairro.id, municipio:it.bairro.municipio.nome] } as JSON
    }

    def search(Map params) {
        def results = [];
        try {
            println params.logradouro
            results = Logradouro.search(params.logradouro, params)
            return results.results
        } catch (SearchEngineQueryParseException ex) {
            [parseException: true]
        }
        return results;
    }

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [logradouroInstanceList: Logradouro.list(params), logradouroInstanceTotal: Logradouro.count()]
    }

    def create = {
        def logradouroInstance = new Logradouro()
        logradouroInstance.properties = params
        return [logradouroInstance: logradouroInstance]
    }

    def save = {
        def logradouroInstance = new Logradouro(params)
        if (logradouroInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), logradouroInstance.id])}"
            redirect(action: "show", id: logradouroInstance.id)
        }
        else {
            render(view: "create", model: [logradouroInstance: logradouroInstance])
        }
    }

    def show = {
        def logradouroInstance = Logradouro.get(params.id)
        if (!logradouroInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
            redirect(action: "list")
        }
        else {
            [logradouroInstance: logradouroInstance]
        }
    }

    def edit = {
        def logradouroInstance = Logradouro.get(params.id)
        if (!logradouroInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [logradouroInstance: logradouroInstance]
        }
    }

    def update = {
        def logradouroInstance = Logradouro.get(params.id)
        if (logradouroInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (logradouroInstance.version > version) {

                    logradouroInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
                        message(code: 'logradouro.label', default: 'Logradouro')]
                    as Object[], "Another user has updated this Logradouro while you were editing")
                    render(view: "edit", model: [logradouroInstance: logradouroInstance])
                    return
                }
            }
            logradouroInstance.properties = params
            if (!logradouroInstance.hasErrors() && logradouroInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), logradouroInstance.id])}"
                redirect(action: "show", id: logradouroInstance.id)
            }
            else {
                render(view: "edit", model: [logradouroInstance: logradouroInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def logradouroInstance = Logradouro.get(params.id)
        if (logradouroInstance) {
            try {
                logradouroInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logradouro.label', default: 'Logradouro'), params.id])}"
            redirect(action: "list")
        }
    }
}

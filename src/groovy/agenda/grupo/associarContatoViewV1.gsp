<tr class="prop">
  <td valign="top" class="name">
    <label for="contatos">
      <g:message code="grupo.contatos.label" default="Contatos" />
    </label>
  </td>
  <td valign="top" 
      class="value ${hasErrors(bean: grupoInstance,
                                      field:  'contatos',
                                      'errors')}">
    <ul>
      <g:each in="${agenda.Contato.list()}" var="contato">
      <li>
        <g:checkBox name="" 
                  checked="${grupoInstance.contatos.contains(contato)}" 
                  onclick="associarContato(${contato.id})"/>
        <g:link controller="contato" action="show" id="${contato.id}">
          ${contato?.nome}
        </g:link>
      </li>
      </g:each>
    </ul>
  </td>
</tr>
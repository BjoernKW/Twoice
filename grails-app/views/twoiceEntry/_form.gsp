<div class="dialog">
    <table>
        <tbody>
        
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="filename"><g:message code="twoiceEntry.filename.label" default="Filename" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: twoiceEntryInstance, field: 'filename', 'errors')}">
                    <g:textField name="filename" value="${twoiceEntryInstance?.filename}" />
                </td>
            </tr>
        
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="person"><g:message code="twoiceEntry.person.label" default="Person" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: twoiceEntryInstance, field: 'person', 'errors')}">
                    <g:select name="person.id" from="${twoice.Person.list()}" optionKey="id" value="${twoiceEntryInstance?.person?.id}"  />
                </td>
            </tr>
        
        </tbody>
    </table>
</div>

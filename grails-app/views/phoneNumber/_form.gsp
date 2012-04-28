<div class="dialog">
    <table>
        <tbody>
        
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="person"><g:message code="phoneNumber.person.label" default="Person" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: phoneNumberInstance, field: 'person', 'errors')}">
                    <g:select name="person.id" from="${twoice.Person.list()}" optionKey="id" value="${phoneNumberInstance?.person?.id}"  />
                </td>
            </tr>
        
            <tr class="prop">
                <td valign="top" class="name">
                    <label for="value"><g:message code="phoneNumber.value.label" default="Value" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: phoneNumberInstance, field: 'value', 'errors')}">
                    <g:textField name="value" value="${phoneNumberInstance?.value}" />
                </td>
            </tr>
        
        </tbody>
    </table>
</div>

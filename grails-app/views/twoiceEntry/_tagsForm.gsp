<g:formRemote name="tagsForm" url="[controller:'twoiceEntry', action:'updateTags', id:twoiceEntryInstance.id]" method="post" update="tags_${twoiceEntryInstance.id}">
	<g:textField name="tagsField_${twoiceEntryInstance.id}" value="${twoiceEntryInstance.tags.join(',')}"/>
</g:formRemote>

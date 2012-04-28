<div class="timeline" id="timeline_${element}">
    <g:each in="${twoiceEntryInstanceList}" status="i" var="twoiceEntryInstance">
        <g:render template="/timeline/entry" model="[twoiceEntryInstance:twoiceEntryInstance]"/>
    </g:each>
</div>

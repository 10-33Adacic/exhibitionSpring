
<form action="/logout" method="post">
    <!-- security_token only to post-methods -->
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <button class="btn btn-primary" type="submit">
        <#if user??>
            <@spring.message "nav.link.logout"/>
        <#else>
            <@spring.message "nav.link.login"/>
        </#if>
    </button>
</form>

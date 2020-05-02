<#import "parts/page.ftl" as p>
<#import "parts/inputData.ftl" as login>

<@p.page>

    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <@login.inputData "/login" false/>

</@p.page>
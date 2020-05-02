<#import "parts/page.ftl" as p>

<@p.page>

    <div>
        <b><@spring.message "label.user_editor"/></b>
    </div>

    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">

        <#list roles as role>
            <div>
                <label>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                    ${role}
                </label>
            </div>
        </#list>

        <input type="hidden" value="${user.id}" name="userId">

        <input type="hidden" value="${_csrf.token}" name="_csrf">

        <button type="submit">
            <@spring.message "button.save"/>
        </button>
    </form>
</@p.page>
<#import "parts/page.ftl" as c>

<@c.page>

    <h3><@spring.message "label.username"/>${userChannel.username}</h3>

    <div>${type}</div>

    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a href="/user-exhibitions/${user.id}">
                    ${user.getUsername()}
                </a>
            </li>
        </#list>
    </ul>

</@c.page>
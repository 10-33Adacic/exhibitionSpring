<#import "parts/page.ftl" as p>

<@p.page>
    <div align="center">
        <h4><@spring.message "label.list_of_users"/></h4>
    </div>

    <div align="center" class="mt-3">
        <table class="table" style="max-width: 50%">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><@spring.message "label.user.name"/></th>
                <th scope="col"><@spring.message "label.user.role"/></th>
                <th scope="col">#</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td scope="row">${user.username}</td>
                    <td>
                        <#list user.roles as role>
                            ${role}
                            <#sep>___
                        </#list>
                    </td>
                    <td>
                        <a href="/user/${user.id}">
                            <@spring.message "button.edit"/>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@p.page>
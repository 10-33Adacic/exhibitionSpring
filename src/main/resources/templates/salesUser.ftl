<#import "parts/page.ftl" as p>

<@p.page>

    <h5><@spring.message "label.username"/>${user.username}</h5>

    <h5><@spring.message "label.tickets"/></h5>

    <#if tickets??>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col"><@spring.message "label.exhibition_name"/></th>
                <th scope="col"><@spring.message "label.exhibition_showroom"/></th>
                <th scope="col"><@spring.message "label.exhibition_description"/></th>
                <th scope="col"><@spring.message "label.exhibition_price"/></th>
                <th scope="col"><@spring.message "label.exhibition_date"/></th>
            </tr>
            </thead>
            <tbody>
            <#list tickets as ticket>
                <tr>
                    <td style="max-width: 25px" scope="row">${ticket.name}</td>
                    <td style="max-width: 25px">${ticket.showroom}</td>
                    <td style="max-width: 150px">${ticket.description}</td>
                    <td style="max-width: 25px">${ticket.price}</td>
                    <td style="max-width: 25px">${ticket.date}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>

</@p.page>
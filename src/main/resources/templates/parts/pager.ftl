<#macro pager url page>
    <div class="container mt-3">
        <div class="row">
            <ul class="pagination  col justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">
                        <@spring.message "pager.label.pages"/>
                    </a>
                </li>
                <#list 1..page.getTotalPages() as p>
                    <#if (p - 1) == page.getNumber()>
                        <li class="page-item active">
                            <a class="page-link" href="#" tabindex="-1">${p}</a>
                        </li>
                        <#else>
                         <li class="page-item">
                             <a class="page-link" href="${url}?page=${p - 1}" tabindex="-1">${p}</a>
                         </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</#macro>
<#import "parts/page.ftl" as p>
<#import "parts/logut.ftl" as logout>
<#include "parts/security.ftl">

<@p.page>

    <!-- Search -->
    <div class="form-rom">
         <form action="main" method="get" class="form-inline">
             <input type="text" name="showroom" class="form-control" value="${showroom!}"
                    placeholder="<@spring.message "placeholder.exhibition_showroom"/>">
             <button type="submit" class="btn btn-primary ml-2">
                  <@spring.message "button.search"/>
             </button>
         </form>
    </div>

    <#if isAdmin>
        <#include "parts/exhibitionEdit.ftl"/>
    </#if>

        <#include "parts/exhibitionList.ftl"/>

</@p.page>
<#import "parts/page.ftl" as p>
<#import "parts/inputData.ftl" as registration>

<@p.page>

    <div class="mb-1">
        <b><@spring.message "label.registration"/></b>
    </div>

    <@registration.inputData "/registration" true/>

</@p.page>
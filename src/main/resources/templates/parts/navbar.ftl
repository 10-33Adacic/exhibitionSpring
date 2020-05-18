<#include "security.ftl">
<#import "logout.ftl" as logout>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">
        <@spring.message "nav.link.home"/>
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/main">
                        <@spring.message "nav.link.exhibitions"/>
                    </a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user-exhibitions/${currentUserId}">
                        <@spring.message "nav.link.my_exhibitions"/>
                    </a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">
                        <@spring.message "nav.link.profile"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/bought-tickets/${currentUserId}">
                        <@spring.message "nav.link.tickets"/>
                    </a>
                </li>
            </#if>
            <#if isSuperAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">
                        <@spring.message "nav.link.user_list"/>
                    </a>
                </li>
            </#if>
        </ul>


        <div class="navbar-text mr-3">
            <#if user??>
                ${name}
            <#else>
                <@spring.message "nav.label.please_login"/>
            </#if>
        </div>

        <a class="btn btn-info mr-1" href="?lang=ua">
            <@spring.message "language.ua"/>
        </a>

        <a class="btn btn-info mr-3" href="?lang=en">
            <@spring.message "language.en"/>
        </a>

        <#include "logout.ftl"/>
    </div>
</nav>
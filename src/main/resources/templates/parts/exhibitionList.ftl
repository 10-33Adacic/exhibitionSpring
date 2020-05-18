<#include "security.ftl">
<#import "pager.ftl" as p>

<#if page??>
    <div class="card-columns mt-3">
        <#list page.content as exhibition>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">
                        ${exhibition.name}
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        <@spring.message "label.showroom"/>${exhibition.showroom}
                    </h6>
                    <h6 class="card-subtitle mb-2 ">
                        <@spring.message "label.price"/>${exhibition.price}
                    </h6>
                    <p class="card-text">
                        ${exhibition.description}
                    </p><br/>
                    <h6 class="card-subtitle mb-2 ">
                        <@spring.message "label.date"/>${exhibition.date}
                    </h6>
                    <form action="/sales/${user.id}" method="post">
                        <input type="hidden" value="${exhibition.id}" name="exhibitionId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-link">
                            <@spring.message "button.buy_ticket"/>
                        </button>
                    </form>
                </div>
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center" href="/user-exhibitions/${exhibition.author.id}">
                            ${exhibition.authorName}
                        </a>

                        <#if exhibition.author.id == currentUserId>
                            <#if deleteFactor??>
                                <form class="col align-self-center" action="/user-exhibitions/delete" method="post">
                                    <input type="hidden" value="${exhibition.id}" name="exhibitionId">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <button type="submit" class="btn btn-secondary">
                                        <@spring.message "button.delete"/>
                                    </button>
                                </form>
                            <#else><p class="col align-self-center"></p>
                            </#if>
                        </#if>

                        <#if exhibition.author.id == currentUserId>
                            <a class="col align-self-center btn btn-secondary"
                               href="/user-exhibitions/${exhibition.author.id}/${exhibition.id}">
                                <@spring.message "button.edit"/>
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#if>

<@p.pager url page/>

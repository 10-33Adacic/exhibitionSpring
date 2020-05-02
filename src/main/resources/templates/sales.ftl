<#import "parts/page.ftl" as p>
<#include "parts/security.ftl">

<@p.page>

    <#if buyError??>
        <div class="alert alert-info" role="alert">
            ${buyError}
        </div>
    </#if>

    <h5><@spring.message "label.username"/>${username}</h5>
    <h5><@spring.message "label.balance"/>${balance}</h5>

    <#include "parts/addMoney.ftl"/>

    <#if exhibition??>
        <div class="card-columns mt-3">
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
                <div class="card-footer text-muted">
                    <a href="/user-exhibitions/${exhibition.author.id}">
                        ${exhibition.authorName}
                    </a>
                </div>
            </div>
        </div>
    </#if>

    <#if !buyError??>
        <div>
            <form action="/bought-tickets/${user.id}" method="post">

                <input type="hidden" name="ticketId" value="${exhibition.id}" />

                <!-- security_token only to post-methods -->
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <button class="btn btn-success mt-2" type="submit">
                    <@spring.message "button.buy"/>
                </button>
            </form>
        </div>
    </#if>

</@p.page>
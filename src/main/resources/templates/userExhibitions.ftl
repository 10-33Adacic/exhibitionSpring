<#import "parts/page.ftl" as p>

<@p.page>
    <h3><@spring.message "label.username"/>${userChannel.username}</h3>

    <#if !isCurrentUser>
        <#if isSubscriber>
            <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">
                <@spring.message "label.unsubscribe"/>
            </a>
        <#else>
            <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">
                <@spring.message "label.subscribe"/>
            </a>
        </#if>
    </#if>

    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">
                            <@spring.message "label.subscriptions"/>
                        </div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">
                                ${subscriptionsCount}
                            </a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">
                            <@spring.message "label.subscribers"/>
                        </div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">
                                ${subscribersCount}
                            </a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#if isCurrentUser>
        <#include "parts/exhibitionEdit.ftl"/>
    </#if>
    <#include "parts/exhibitionList.ftl"/>

</@p.page>
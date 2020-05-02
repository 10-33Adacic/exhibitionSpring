<#import "parts/page.ftl" as p>

<@p.page>

    <h5><@spring.message "label.username"/>${username}</h5>

    <form  method="post">
        <div>
            <b><@spring.message "label.edit_username"/></b>
        </div>

        <!-- Username -->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <@spring.message "label.username"/>
            </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control"  value="${username}"
                       placeholder="<@spring.message "placeholder.username"/>"/>
            </div>
        </div>

        <div>
            <b><@spring.message "label.edit_password"/></b>
        </div>

        <!-- Password -->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <@spring.message "label.password"/>
            </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control"
                       placeholder="<@spring.message "placeholder.password"/>" />
            </div>
        </div>

        <!-- security_token only to post-methods -->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button class="btn btn-primary" type="submit">
            <@spring.message "button.save"/>
        </button>
    </form>

</@p.page>
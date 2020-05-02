<#macro inputData path isRegisterForm>

<form action="${path}" method="post">
    <!-- Username -->
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">
            <@spring.message "label.username"/>
        </label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   value="<#if user??>${user.username}</#if>"
                   placeholder="<@spring.message "placeholder.username"/>" />
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                 </div>
            </#if>
        </div>
    </div>

    <!-- Password -->
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">
            <@spring.message "label.password"/>
        </label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="<@spring.message "placeholder.password"/>" />
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>

    <#if isRegisterForm>
    <!-- Password  Confirmation-->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <@spring.message "label.confirm_password"/>
            </label>
            <div class="col-sm-6">
                <input type="password" name="passwordConfirm" class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}"
                       placeholder="<@spring.message "placeholder.password"/>" />
                <#if passwordConfirmError??>
                    <div class="invalid-feedback">
                        ${passwordConfirmError}
                    </div>
                </#if>
            </div>
        </div>

    <!-- reCaptcha-->
        <div class="col-sm-6">
            <div class="g-recaptcha" data-sitekey="6LdXr7QUAAAAAIpZ4oi0pn4jH65MMb_HcSH4vFIS"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
        </div>
    </#if>

    <!-- security_token only to post-methods -->
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <button class="btn btn-primary mt-3" type="submit">
        <#if isRegisterForm>
             <@spring.message "label.registration"/>
        <#else>
            <@spring.message "label.login"/>
        </#if>
    </button>
</form>

    <#if !isRegisterForm>
    <div>
        <a href="/registration" class="btn btn-link mt-3">
            <@spring.message "label.registration"/>
        </a>
    </div>
    </#if>
</#macro>
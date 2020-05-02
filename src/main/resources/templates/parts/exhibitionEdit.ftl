
 <a class="btn btn-primary mt-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
     <@spring.message "label.exhibition_editor"/>
 </a>

    <div class="collapse <#if exhibition??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <div class="col-sm-6">
                <form action="/main" method="post">

                    <!-- Name -->
                    <div class="form-group">
                        <label for="exhibition">
                            <@spring.message "label.exhibition_name"/>
                        </label>
                        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}" required
                               id="exhibition" value="<#if exhibition??>${exhibition.name}</#if>"
                               placeholder="<@spring.message "placeholder.exhibition_name"/>"/>
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>

                    <!-- Showroom -->
                    <div class="form-group">
                        <label for="showroom">
                            <@spring.message "label.exhibition_showroom"/>
                        </label>
                        <input type="text" name="showroom" class="form-control ${(showroomError??)?string('is-invalid', '')}" required
                               id="showroom" value="<#if exhibition??>${exhibition.showroom}</#if>"
                               placeholder="<@spring.message "placeholder.exhibition_showroom"/>"/>
                        <#if showroomError??>
                            <div class="invalid-feedback">
                                    ${showroomError}
                            </div>
                        </#if>
                    </div>

                    <!-- Description -->
                    <div class="form-group">
                        <label for="description">
                            <@spring.message "label.exhibition_description"/>
                        </label>
                        <input type="text" name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}" required
                               id="description" value="<#if exhibition??>${exhibition.description}</#if>"
                               placeholder="<@spring.message "placeholder.exhibition_description"/>"/>
                        <#if descriptionError??>
                                    <div class="invalid-feedback">
                                        ${descriptionError}
                                    </div>
                        </#if>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="price">
                            <@spring.message "label.exhibition_price"/>
                        </label>
                        <input type="text" name="price" class="form-control ${(priceError??)?string('is-invalid', '')}"
                               required pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{1}[0-9]{1}|^1000"
                               id="price" value="<#if exhibition??>${exhibition.price}</#if>" placeholder="0"/>
                        <#if priceError??>
                                    <div class="invalid-feedback">
                                        ${priceError}
                                    </div>
                        </#if>
                    </div>

                    <!-- Date -->
                    <div class="form-group">
                        <label for="date">
                            <@spring.message "label.exhibition_date"/>
                        </label>
                        <input type="date" name="date" class="form-control ${(dateError??)?string('is-invalid', '')}" required
                               id="date" value="<#if exhibition??>${exhibition.date}</#if>" />
                        <#if dateError??>
                                    <div class="invalid-feedback">
                                        ${dateError}
                                    </div>
                        </#if>
                    </div>

                    <!-- security_token only to post-methods -->
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <input type="hidden" name="id" value="<#if exhibition??>${exhibition.id}</#if>" />

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">
                            <@spring.message "button.save"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
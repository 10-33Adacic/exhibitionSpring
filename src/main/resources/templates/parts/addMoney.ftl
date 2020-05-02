<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                <@spring.message "link.add_money"/>
</a>
            <div class="collapse" id="collapseExample">
                <div class="form-group mt-3">
                    <div class="col-sm-4">
                        <form action="/sales/${user.id}" method="post">
                            <!-- Add money -->
                            <div class="form-group">
                                <label for="exhibition">
                                    <@spring.message "label.replenish_balance"/>
                                </label>
                                <input type="text" name="money" class="form-control ${(moneyError??)?string('is-invalid', '')}"
                                       pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{1}[0-9]{1}|^1000"
                                       id="exhibition"  placeholder="0"/>
                                    <#if moneyError??>
                                        <div class="invalid-feedback">
                                            ${moneyError}
                                        </div>
                                    </#if>

                                <!-- security_token only to post-methods -->
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                                <input type="hidden" name="exhibitionId" value="${exhibition.id}" />

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary mt-2">
                                        <@spring.message "button.add"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
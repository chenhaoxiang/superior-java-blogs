<!DOCTYPE html>
<html lang="zh">

<head>
    <!-- Bootstrap -->
    <#include "include/head.ftl"/>

    <title>登录-huijava</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "include/navbar.ftl"/>
        </div>

        <div class="col-md-12 column">
            <div class="col-md-8 column">
                <div class="row clearfix">
                    <div class="col-md-3 column">
                    </div>
                    <div class="col-md-6 column">
                        <div class="row clearfix">
                            <div class="col-md-12 column" style="text-align: center">
                                <h3>
                                    <a href="/">huijava-登录</a>
                                </h3>
                            </div>
                        </div>

                        <#if message?exists && message?length gt 0>
                            <div class="col-md-12 column">
                                <div class="alert alert-dismissable alert-danger">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                                    </button>
                                    ${message!""}
                                </div>
                            </div>
                        </#if>


                        <div class="col-md-12 column">
                            <form role="form" action="/toLogin" method="post">
                                <div class="form-group">
                                    <label for="username">用户名</label><input type="text" placeholder="用户名"
                                                                            class="form-control"
                                                                            name="username" id="username"/>
                                </div>
                                <div class="form-group">
                                    <label for="password">密码</label><input type="password" placeholder="密码"
                                                                           class="form-control"
                                                                           name="password" id="password"/>
                                </div>
                                <#--<button style="width: 30%" type="submit" class="btn btn-success"> 登录 </button>-->
                                <#--<button style="float: right;width: 30%" type="button" class="btn btn-danger">忘记密码</button>-->
                                <button style="width: 30%" type="submit"
                                        class="btn btn-success"> 登录
                                </button>
                                <button onclick="alert('开发中.谢谢您的支持')" style="float: right;width: 30%" type="button"
                                        class="btn btn-danger">忘记密码
                                </button>
                            </form>
                        </div>
                    </div>

                    <div class="col-md-3 column">
                    </div>
                </div>

            </div>


            <div class="col-md-4 column" style="margin-top: 20px">

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <#include "include/tabbable.ftl"/>
                    </div>
                </div>

            </div>


        </div>
    </div>
</div>

<#include "include/footer.ftl"/>

<#include "include/base-js.ftl"/>

</body>

</html>
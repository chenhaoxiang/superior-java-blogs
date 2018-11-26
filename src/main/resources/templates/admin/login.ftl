<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <title>管理员登录-huijava</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <h3>
                                HJ后台登录
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
                    <form role="form" action="/admin/loginRequest" method="post">
                        <div class="form-group">
                            <label for="username">用户名</label><input type="text" placeholder="用户名" class="form-control"
                                                                    name="username" id="username"/>
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label><input type="password" placeholder="密码" class="form-control"
                                                                   name="password" id="password"/>
                        </div>
                        <button type="submit" class="btn btn-success">登录</button>
                    </form>
                </div>
                <div class="col-md-4 column">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/js/jquery.1.12.4.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="/bootstrap3.3.7/js/bootstrap.min.js"></script>
</body>

</html>
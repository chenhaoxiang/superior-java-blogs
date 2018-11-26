<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <!-- 描述：错误页面的样式      -->
    <link rel="stylesheet" href="/css/error.css"/>

    <title>404-对不起！您访问的页面不存在-huijava</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "../include/navbar.ftl"/>
        </div>


        <div class="col-md-12 column error404">
            <p class="text-error"><span>4</span><span>0</span><span>4</span></p>
            <p>该页面不存在(´･ω･`)</p>
            <p>将在<span class="count-down" id="count-down"></span>秒内跳转回首页</p>
        </div>

    </div>

</div>

<#include "../include/footer.ftl"/>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<#include "../include/base-js.ftl"/>
<script type="text/javascript" src="/js/base.js"></script>
<!--描述：错误页面跳转的倒计时js-->
<script type="text/javascript" src="/js/error.js"></script>
</body>

</html>
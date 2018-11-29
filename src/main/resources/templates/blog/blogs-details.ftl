<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
<#--代码高亮 方式一，后面还有两个js配置-->
<#--<link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet">-->
<#--蓝色开头-->
<#--<link href="/prism/prism-coy.css" rel="stylesheet">-->
<#--黑色 绿字-->
    <link href="/prism/prism-okaidia.css" rel="stylesheet">

    <title>${blogs.title!""}-huijava</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "../include/navbar.ftl"/>
        </div>


        <div class="col-md-12 column">
        <#--<#include "../include/breadcrumb.ftl"/>-->
            <ul class="breadcrumb">
                <li>
                    <a href="/">首页</a>
                </li>
                <li>
                    <a href="/blogs/0/1">博客</a>
                </li>
                <li>
                    <a href="/blogs/${blogs.categoryId}/1">${blogs.category.name!""}</a>
                </li>
                <li class="active">
                ${blogs.title!""}
                </li>
            </ul>


        </div>

        <div class="col-md-12 column">

            <div class="row clearfix">
                <div class="col-md-8 column">

                    <div class="col-md-12 column text-center">
                        <h2 class="color-black">${blogs.title!""}</h2>
                    </div>

                    <div class="col-md-12 column text-center">
                        <div class="col-md-12 column">
                            <#if blogs.original==1>
                                <button type="button" class="btn btn-xs btn-info">原创</button>
                            <#elseif blogs.original==0>
                                <button type="button" class="btn btn-xs btn-default">转载</button>
                            </#if>
                            <a class="page-header-more">${blogs.nikeName!""}</a>
                            <#if blogs.createTime??>
                                ${blogs.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </#if>
                            <span class="badge">阅读数:${blogs.viewCount!""}</span>
                            <span class="badge text-decoration-none">评论数:${blogs.commentCount!""}</span>
                            <span class="badge">收藏数:${blogs.collectCount!""}</span>
                        </div>

                    </div>


                    <div class="col-md-12 column">
                        <hr color="#6f5499"/>
                    </div>

                    <div class="col-md-12 column">

                        <div class="row clearfix">
                            <#if blogs.original==1>
                                版权声明：本文为[<span style="color: #b92c28">${blogs.nikeName!""}</span>]原创文章，
                                转载请附上原文链接：${blogs.originalUrl!""}，谢谢。
                            <#elseif blogs.original==0>
                                版权声明：本文为${blogs.nikeName!""}转载[<span style="color: #b92c28">${blogs.author!""}</span>]文章，
                                原文链接为：${blogs.originalUrl!""}
                            </#if>
                        </div>

                        <div class="row clearfix"
                        ">
                        <hr color="#6f5499"/>
                    </div>

                    <div id="markdown" class="row">
                    </div>


                </div>

            </div>

            <div class="col-md-4 column">

                <div class="row clearfix margin-bottom15px">
                    <div class="col-md-12 column">
                        <div class="row clearfix">
                            <div class="col-md-12 column text-center content">
                                <div class="row clearfix">
                                    <a href="/users/${usersDTO.username}" class="page-header-more">
                                        <img style="height: 80px;" alt="140x140"
                                             src="<#if usersDTO.headImage??><#if usersDTO.headImage==''>/images/default.png<#else >${usersDTO.headImage!'/images/default.png'}</#if><#else >/images/default.png</#if>"
                                             class="img-circle"/>
                                    </a>
                                    <a href="/users/${usersDTO.username}"
                                       class="h4 page-header-more">${usersDTO.nikeName!""}</a>
                                </div>
                                <div class="row clearfix">
                                    <div class="row clearfix margin-bottom10px">
                                        <span class="color-darkgrey">${usersDTO.sign!""}</span>
                                    </div>
                                <#--TODO 关注尚未做的-->
                                <#--<button type="button" class="btn btn-default btn-success">关注</button>-->
                                    <!--<button type="button" class="btn btn-default active">已关注</button>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--在该div中展示,如有初始化的数据可以放在textarea中-->
                <div id="testEditorMdview">
                    <textarea id="append-content" style="display:none;">${blogs.content}</textarea>
                </div>


                    <#include "../include/tabbable.ftl"/>

            </div>

        </div>
    </div>


</div>

</div>

<#include "../include/footer.ftl"/>

<#--防止markdown出现\r\n的情况,会出现html注入攻击-->
<#--<div style="display: none">-->
<#--<pre id="blogsContent">${blogs.content}</pre>-->
<#--</div>-->

<#include "../include/base-js.ftl"/>
<!--将markdown 转换为 html 方式一-->
<script src="/js/showdown.1.3.0.min.js"></script>
<!--将markdown 转换为 html 方式二-->
<#--<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>-->
<script type="text/javascript">
    var content = $("#append-content").val();
    // var content = jQuery("#blogsContent").html();
    //使用el表达式获取后台返回的markdown内容";
    <!--将markdown 转换为 html 方式一-->
    var converter = new showdown.Converter(); //初始化转换器
    var htmlContent = converter.makeHtml(content); //将MarkDown转为html格式的内容
    $("#markdown").html(htmlContent); //添加到 div 中 显示出来
    <!--将markdown 转换为 html 方式二-->
    // document.getElementById('markdown').innerHTML =marked(content.getMutilines());
</script>

<#--代码高亮，方式一-->
<#--<script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>-->
<#--<script >hljs.initHighlightingOnLoad();</script>-->
<#--蓝色开头-->
<#--<script src="/prism/prism-coy.js"></script>-->
<#--黑色 绿字-->
<script src="/prism/prism-okaidia.js"></script>

</body>

</html>
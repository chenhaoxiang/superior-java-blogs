<!DOCTYPE html>
<html lang="en">

<head>
    <#include "include/head.ftl"/>
    <title>会Java</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "include/navbar.ftl"/>
        </div>

        <div class="col-md-12 column">
            <div class="col-md-8 column">
            <#--轮播图-->
                <div class="row clearfix">
                    <div class="carousel slide" id="carousel-444044">
                        <ol class="carousel-indicators">
                            <li class="active" data-slide-to="0" data-target="#carousel-444044">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-444044">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img alt="欢迎来到HJ-Java生态圈"
                                     src="/images/index/1.png"/>
                                <div class="carousel-caption">
                                    <#--<h4>-->
                                    <#--欢迎来到HJ-Java生态圈-->
                                    <#--</h4>-->
                                    <#--<p>-->
                                    <#--一起沉淀Java知识，立志打造国内一流的Java生态圈-->
                                    <#--</p>-->
                                </div>
                            </div>
                            <div class="item">
                                <img alt="网站正在建设中"
                                     src="/images/index/3.png"/>
                                <div class="carousel-caption">
                                    <#--<h4>-->
                                    <#--网站正在建设中，HJ生态系统欢迎有志之士一起开发（会有审核，活跃于其他社区、论坛的开发者优先考虑）-->
                                    <#--</h4>-->
                                    <#--<p>-->
                                    <#--登录、注册、评论、排行、论坛、问答、开源项目、积分系统、兑换系统、招聘系统等等将逐步开发并进行开放。-->
                                    <#--若您有好的建议，可以联系网站管理员，建议被采纳，开放注册后可定制专有贡献者高亮昵称/图标。-->
                                    <#--</p>-->
                                </div>
                            </div>
                        </div>
                        <a class="left carousel-control" href="#carousel-444044" data-slide="prev"><span
                                class="glyphicon glyphicon-chevron-left"></span></a>
                        <a class="right carousel-control" href="#carousel-444044" data-slide="next"><span
                                class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                </div>
            <#--轮播图end-->

                <div class="row clearfix">
                    <#if newBlogsList??>
                        <#list newBlogsList as blogs>
                    <div class="col-md-12 column">
                        <h3>
                            <a href="/blogs-details/${blogs.blogToken!""}" class="page-header-more">
                                ${blogs.title!""}
                            </a>
                        </h3>
                        <p class="color-light-grey">
                                <#if blogs.summary??>
                                    <#if blogs.summary?length gt 150>
                                        ${blogs.summary?substring(0,150)}...
                                    <#else >
                                        ${blogs.summary}
                                    </#if>
                                </#if>
                        </p>
                        <p class="color-darkgrey">
                            ${blogs.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            <span class="badge">阅读数:${blogs.viewCount!'0'}</span>
                            <span class="badge">评论数:${blogs.commentCount!'0'}</span>
                            <a class="text-decoration-none color-dimgray">作者:${blogs.author!''}</a>
                        </p>
                    </div>
                        </#list>
                    </#if>

                </div>
            </div>

            <div class="col-md-4 column">
                <div class="row clearfix">
                    <div class="col-md-12 column text-center">
                        <h3 class="media-heading">
                            HJ生态圈贡献排行
                        </h3>
                    </div>
                    <div class="col-md-12 column margin-bottom15px">
                        <div class="media">
                            <a href="/users/admin" class="pull-left"><img
                                    src="/images/index/user.jpg"
                                    class="media-object img-circular img-height-width75" alt='谙忆'/></a>
                            <div class="media-body">
                                <#--<h4 class="media-heading">-->
                                <#--<a href="/users/admin">谙忆</a>-->
                                <#--</h4>-->
                                <#--HJ生态圈创办者，Java从业者-->

                            <#--<div class="media">-->
                            <#--<a href="#" class="pull-left"><img-->
                            <#--src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/64/64/default.jpg"-->
                            <#--class="media-object" alt=''/></a>-->
                            <#--<div class="media-body">-->
                            <#--<h4 class="media-heading">-->
                            <#--Nested media heading-->
                            <#--</h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque-->
                            <#--ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus-->
                            <#--viverra turpis.-->
                            <#--</div>-->
                            <#--</div>-->
                            </div>
                        </div>
                    </div>
                </div>

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
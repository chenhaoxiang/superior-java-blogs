<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>${usersDTO.nikeName}-huijava</title>
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
                <li class="active">
                    <a>用户信息</a>
                </li>
            </ul>

        </div>

        <div class="col-md-12 column">

            <div class="row clearfix">

                <div class="col-md-8 column">

                    <div class="col-md-12 column text-center">
                        <div class="row clearfix">
                            <div class="col-md-12 column text-center content">
                                <div class="col-md-12 clearfix">
                                    <a class="page-header-more">
                                        <img style="height: 80px;" alt="140x140"
                                             src="<#if usersDTO.headImage??><#if usersDTO.headImage==''>/images/default.png<#else >${usersDTO.headImage!'/images/default.png'}</#if><#else >/images/default.png</#if>"
                                             class="img-circle"/>
                                    </a>
                                    <a class="h4 page-header-more">${usersDTO.nikeName}</a>
                                <#--<span class="color-darkgrey">-->
                                <#--湖南-->
                                <#--</span>-->
                                </div>
                                <div class="col-md-12 clearfix margin-bottom10px">
                                    <div class="row clearfix">
                                        贡献量：${usersDTO.contributeWeight}
                                    </div>
                                </div>
                                <!--注册人数贡献度排名的前20%-->
                                <div class="col-md-12 column">
                                    <div class="progress progress-striped active">
                                        贡献占比：10.1999%
                                        <div style="width:40.1999%" aria-valuemin="0" aria-valuemax="100"
                                             class="progress-bar progress-bar-danger">
                                            贡献占比：10.1999%
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 clearfix">
                                    <div class="row clearfix margin-bottom10px">
                                        <span class="color-darkgrey">
                                            <#if usersDTO.sign??>
                                                <#if usersDTO.sign==''>
                                                    这人很懒，还没写签名...
                                                <#else >
                                                    ${usersDTO.sign!"这人很懒，还没写签名..."}
                                                </#if>
                                            <#else >
                                                这人很懒，还没写签名...
                                            </#if>
                                        </span>
                                    </div>
                                    <button type="button" class="btn btn-default btn-success">关注</button>
                                    <!--<button type="button" class="btn btn-default active">已关注</button>-->
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 column">
                        <hr color="#6f5499"/>
                    </div>

                    <div class="col-md-12 column">
                        <ul class="nav nav-tabs">
                            <li id="blogs" class="<#if fieldName=='blogs'>active</#if>">
                                <a href="/users/${usersDTO.username}">
                                    博客
                                    <span class="badge pull-right" style="background-color: green;">${blogsCount}</span>
                                </a>
                            </li>
                            <li>
                                <a href="">粉丝</a>
                            </li>
                            <li>
                                <a href="">评论</a>
                            </li>
                        </ul>
                    </div>

                <#--博客-->
                    <div class="col-md-12 column">
                        <#if blogsDTOList??>
                            <#list blogsDTOList as blogs>
                                <div class="col-md-12 column">
                                    <h3>
                                        <a href="/blogs-details/${blogs.blogToken!""}" class="page-header-more">
                                            ${blogs.title!""}
                                        </a>
                                    </h3>
                                    <p>
                                        <a href="/blogs-details/${blogs.blogToken!""}"
                                           class="color-black text-decoration-none">
                                            <#if blogs.summary??>
                                                <#if blogs.summary?length gt 150>
                                                    ${blogs.summary?substring(0,150)}...
                                                <#else >
                                                    ${blogs.summary}
                                                </#if>
                                            </#if>
                                        </a>
                                    </p>
                                    <p class="color-darkgrey">
                                        ${blogs.createTime?string('yyyy-MM-dd HH:mm:ss')}
                                        阅读数:${blogs.viewCount!'0'}
                                        评论数:${blogs.commentCount!'0'}
                                        <a class="text-decoration-none color-dimgray">作者:${blogs.author!''}</a>
                                    </p>
                                </div>
                            </#list>
                        </#if>
                        <div class="col-md-12 column text-center">
                            <ul class="pagination pagination-lg ">
                                <#if pageNum gt 1>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pageNum-1}">Prev</a>
                                    </li>
                                </#if>

                                <#if pageNum gt 3>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=1">1</a>
                                    </li>
                                    <#if pageNum gt 4>
                                        <li class="disabled">
                                            <a>...</a>
                                        </li>
                                    </#if>
                                </#if>

                                <#if pageNum gt 2>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pageNum-2}">${pageNum-2}</a>
                                    </li>
                                </#if>
                                <#if pageNum gt 1>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pageNum-1}">${pageNum-1}</a>
                                    </li>
                                </#if>
                                <li class="disabled">
                                    <a class="active">${pageNum}</a>
                                </li>

                                <#if pageNum lt pages>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=/${pageNum+1}">${pageNum+1}</a>
                                    </li>
                                </#if>
                                <#if pageNum+1 lt pages>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pageNum+2}">${pageNum+2}</a>
                                    </li>
                                </#if>
                                <#if pageNum+2 lt pages>
                                    <#if pageNum+3 lt pages>
                                        <li class="disabled">
                                            <a>...</a>
                                        </li>
                                    </#if>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pages}">${pages}</a>
                                    </li>
                                </#if>
                                <#if pageNum lt pages>
                                    <li>
                                        <a href="/users/${usersDTO.username}?pageNum=${pageNum+1}">Next</a>
                                    </li>
                                </#if>

                            </ul>
                        </div>

                    </div>

                <#--粉丝-->

                <#--评论-->
                </div>

                <div class="col-md-4 column">

                    <#include "../include/tabbable.ftl"/>

                </div>

            </div>
        </div>


    </div>

</div>

<#include "../include/footer.ftl"/>

<#include "../include/base-js.ftl"/>
</body>

</html>
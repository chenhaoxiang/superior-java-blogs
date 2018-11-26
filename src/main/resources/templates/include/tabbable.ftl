<div class="tabbable" id="tabs-624145">
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#panel-656376" data-toggle="tab">最热博客</a>
        </li>
        <li>
            <a href="#panel-935493" data-toggle="tab">最棒博客</a>
        </li>
        <li>
            <a href="#" data-toggle="tab">推荐博客</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="panel-656376">
            <p>
                <a class="page-header-more">180天内浏览量最高博客排行</a>
            </p>
            <ol>
                <#if viewBlogsList??>
                    <#list viewBlogsList as blogs>
                    <li>
                        <a class="page-header-more"
                           href="/blogs-details/${blogs.blogToken}">${blogs.title}</a>（${blogs.viewCount}）
                    </li>
                    </#list>
                </#if>
            </ol>
        </div>
        <div class="tab-pane" id="panel-935493">
            <p>
                <a class="page-header-more">180天内点赞最高博客排行</a>
            </p>

            <ol>
                <#if topBlogsList??>
                    <#list topBlogsList as blogs>
                    <li>
                        <a class="page-header-more"
                           href="/blogs-details/${blogs.blogToken}">${blogs.title}</a>（${blogs.topCount}）
                    </li>
                    </#list>
                </#if>
            </ol>
        </div>
    </div>
</div>

<#--<div class="panel panel-default">-->
<#--<div class="panel-heading">-->
<#--<h3 class="panel-title">-->
<#--最新评论：-->
<#--</h3>-->
<#--</div>-->
<#--<!-- 最新的10条评论 &ndash;&gt;-->
<#--<div class="panel-body">-->
<#--<a class="page-header-more">陈浩翔：</a>这个博客不错个博客不错个博客不错个博客不错个博客不错-->
<#--<a class="page-header-more">********文章</a>-->
<#--</div>-->
<#--<div class="panel-footer">-->
<#--<a class="page-header-more">陈浩翔：</a>这个博客不错-->
<#--<a class="page-header-more">********文章</a>-->
<#--</div>-->
<#--</div>-->

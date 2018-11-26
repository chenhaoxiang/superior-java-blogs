<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Wysiwyg &amp; Markdown Editor - Ace Admin</title>
		<#include "../include/head.ftl"/>
</head>

<body class="no-skin">

		<#include "../include/navbar.ftl"/>

<div class="main-container ace-save-state" id="main-container">

		<#include "../include/sidebar.ftl"/>

    <div class="main-content">
        <div class="main-content-inner">

					<#include "../include/breadcrumbs.ftl"/>
            <div class="page-content">

						<#include "../include/ace-settings-container.ftl"/>

                <div class="page-header">
                    <h1>发表博客</h1>
                </div>
                <!-- /.page-header -->


                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">

                            <form class="form-horizontal" name="blogForm" role="form" action="/admin/blogs/insertPost"
                                  method="post">
                                <div class="form-group col-xs-12">
                                    <h4 class="header green">博客标题（不超过254个字符）</h4>
                                    <input type="text" class="form-control" id="title" name="title"
                                           placeholder="博客标题"/>
                                </div>

                                <div class="form-group col-xs-12">
                                    <h4 class="header green">博客摘要（不填写将取博客内容的前面500个字符）</h4>
                                    <input type="text" class="form-control" id="summary" name="summary"
                                           placeholder="博客摘要"/>
                                </div>

                                <div class="form-group col-xs-12">
                                    <h4 class="header green">博客内容（点击保存,自动保存到本地）</h4>
                                    <div class="widget-box widget-color-blue">
                                        <div class="widget-header widget-header-small"></div>

                                        <div class="widget-body">
                                            <div class="widget-main no-padding">
                                                <textarea name="content" id="markdown" data-provide="markdown"
                                                          data-iconlibrary="fa" rows="10"> </textarea>
                                            </div>

                                            <div class="widget-toolbox padding-4 clearfix">
                                                <div class="btn-group pull-left">
                                                    <button type="button" class="btn btn-sm btn-info"
                                                            onclick="confirm(cleanMarkDown,'','提示','确认清空文章吗？')">
                                                        <i class="ace-icon fa fa-times bigger-125"></i>
                                                        清空
                                                    </button>
                                                </div>

                                                <div class="btn-group pull-right">
                                                    <button type="button" class="btn btn-sm btn-purple"
                                                            onclick="saveToLocal()">
                                                        <i class="ace-icon fa fa-floppy-o bigger-125"></i>
                                                        保存到本地
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr/>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div class="tabbable" id="tabs-597737">
                                            <ul class="nav nav-tabs">
                                                <li class="active">
                                                    <a href="#panel-888939" data-toggle="tab">原创</a>
                                                </li>
                                                <li>
                                                    <a href="#panel-971914" data-toggle="tab">转载</a>
                                                </li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="panel-888939">
                                                    <p>
                                                        确认为本人原创文章
                                                    </p>
                                                </div>
                                                <div class="tab-pane" id="panel-971914">
                                                    <p>
                                                        转载文章请填写原作者和原文链接。若为原创文章，请不要填写。若填写则默认文章为转载
                                                    </p>
                                                    <div class="form-group">
                                                        <div class="col-xs-12">
                                                            <input type="text" class="form-control" id="author"
                                                                   name="author" placeholder="原作者"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-xs-12">
                                                            <input type="text" class="form-control" id="originalUrl"
                                                                   name="originalUrl" placeholder="原文地址"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr/>
                                <!--<div class="form-group">
                                    <div class="col-xs-4">
                                        原文发表时间(yyyyMMdd HHmmss)
                                    </div>
                                    <div class="col-xs-8">
                                        <input type="text" class="form-control" id="publishTime" name="publishTime" placeholder="不填默认为发表日期" />
                                    </div>
                                </div>
                                <hr />-->

                                <div class="form-group">
                                    <div class="col-xs-3">
                                        多个标签以;分隔
                                    </div>
                                    <div class="col-xs-9">
                                        <input type="text" class="form-control" name="tags" id="tags"
                                               placeholder="有效标签只取前面5个"/>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="col-xs-3">
                                        选择分类
                                    </div>
                                    <div class="col-xs-9">
                                        <!--分类下拉框选择-->
                                        <select class="combobox" name="categoryId">
													<#if categoryExtList?? && (categoryExtList?size > 0) >
													<#list categoryExtList as category>
														<option value="${category.id}">${category.name}</option>
                                                    </#list>
                                                    </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-xs-12">
                                        <button id="submit" class="btn btn-success">发表</button>
                                    </div>
                                </div>

                            </form>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>


                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->

			<#include  "../include/footer.ftl"/>

</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script src="/assets/js/bootstrap.min.js"></script>

<script src="/assets/js/markdown.min.js"></script>
<script src="/assets/js/bootstrap-markdown.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<#include  "../include/base-js.ftl"/>

<!-- inline scripts related to this page -->
<script type="text/javascript" src="/js/admin/blog-insert.js"></script>
<script>
    jQuery(function ($) {
        //获取LocalStorage中文章设置到md中
        var content = LocalStorageUtil.getItem(markdownLocal);
        console.log("获取LocalStorage中文章设置到md中,content=" + content);
        $("#markdown").text(content);

        $('textarea[data-provide="markdown"]').each(function () {
            var $this = $(this);
            if ($this.data('markdown')) {
                $this.data('markdown').showEditor();
            } else $this.markdown();
            $this.parent().find('.btn').addClass('btn-white');
        })
    });
</script>
</body>

</html>
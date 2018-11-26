<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>User Profile Page - Ace Admin</title>

    <#include "../include/head.ftl"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="/assets/css/jquery.gritter.min.css"/>
    <link rel="stylesheet" href="/assets/css/select2.min.css"/>
    <link rel="stylesheet" href="/assets/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="/assets/css/bootstrap-editable.min.css"/>

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
                    <h1>
                        User Profile Page
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            3 styles with inline editable feature
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="clearfix">
                            <div id="remindDiv" class="pull-left alert alert-danger no-margin alert-dismissable"
                                 style="display: none">
                                <button type="button" class="close" onclick="closeRemindDiv('#remindDiv')">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                <i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
                                <span id="remind"></span>
                            </div>

                            <div class="pull-right">
                                <span class="green middle bolder">&nbsp;</span>

                                <div class="btn-toolbar inline middle no-margin">
                                    <div data-toggle="buttons" class="btn-group no-margin">
                                        <label class="btn btn-sm btn-yellow active">
                                            <span class="bigger-110">信息</span>

                                            <input type="radio" value="1"/>
                                        </label>

                                        <label class="btn btn-sm btn-yellow">
                                            <span class="bigger-110">修改</span>

                                            <input type="radio" value="3"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="hr dotted"></div>

                        <#include  "user-profile/user-profile-1.ftl"/>
                        <#include  "user-profile/user-profile-3.ftl"/>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


    <#include  "../include/footer.ftl"/>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="/assets/js/jquery-ui.custom.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="/assets/js/jquery.gritter.min.js"></script>
<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/jquery.easypiechart.min.js"></script>
<script src="/assets/js/bootstrap-datepicker.min.js"></script>
<script src="/assets/js/jquery.hotkeys.index.min.js"></script>
<script src="/assets/js/bootstrap-wysiwyg.min.js"></script>
<script src="/assets/js/select2.min.js"></script>
<script src="/assets/js/spinbox.min.js"></script>
<script src="/assets/js/bootstrap-editable.min.js"></script>
<script src="/assets/js/ace-editable.min.js"></script>
<script src="/assets/js/jquery.maskedinput.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<#include  "../include/base-js.ftl"/>
<!-- inline scripts related to this page -->
<script src="/js/admin/profile.js"></script>

<script>
    function savePassword() {
        var url = "/admin/info/updatePassword";
        var oldPassword = jQuery("#form-field-pass").val();
        var newPassword = $("#form-field-pass1").val();
        var newPassword1 = $("#form-field-pass2").val();
        if (newPassword != newPassword1) {
            $("#remindDiv").attr("style", "display:display");
            $("#remindDiv").removeClass();
            $("#remindDiv").addClass("pull-left alert alert-danger no-margin alert-dismissable");
            $("#remind").text("两次输入的密码不一致，请重新填写。");
            return;
        }
        var parameter = "oldPassword=" + oldPassword + "&newPassword=" + newPassword;
        $.ajax({
            type: "POST",
            dataType: "json", //返回数据的格式 json
            url: url,
            data: parameter,
            async: false, //开启异步请求
            success: function (response) {
                successfun(response, param);
            },
            error: function (response) {
                confirm("", "", "请求提示", "请求失败，异常信息:" + response.message)
            }
        });
    }

</script>

</body>
</html>

<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>邀请关注</title>
</head>

<body>

<div class="container-fluid container" style="text-align: center">
    <div class="row-fluid row">
        <div class="span12">
            <img alt="${wxUsersDTO.nickname!''}" src="${wxUsersDTO.headimgurl!''}" class="img-circle"/>
            <p>${wxUsersDTO.nickname !''}</p>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <span class="label label-primary">邀请人数:${wxUsersDTOList?size}</span>
                    <#if myPName??>
                        <span class="label label-danger">上级昵称:${myPName!''}</span>
                    <#else >
                        <span class="label label-success">上级昵称:${myPName!'无'}</span>
                    </#if>
                </div>
            </div>

            <div class="row clearfix" style="margin-top: 5px;">
                <div class="col-md-12 column">

                    <a href="/wechat/getRedPacket" type="button" class="btn btn-default btn-info">领红包</a>
                    <a href="/wechat/myInfo" style="margin-left: 2px;margin-right: 2px;" type="button"
                       class="btn btn-warning">个人信息</a>
                    <a href="/wechat/userInfo?openid=${wxUsersDTO.openid}" type="button" class="btn btn-success">首页</a>
                </div>
            </div>

            <#if newUser??>
                <#if newUser==1>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" action="/wechat/submitInvitationCode">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">邀请码:</label>
                                    <input type="text" class="form-control" id="invitationCode" name="invitationCode"/>
                                </div>
                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </#if>
            </#if>

            <#if message??>
                <div style="color: red">
                    <p>${message}</p>
                </div>
            </#if>

            <div class="page-header">
                <h1>
                    我的邀请信息
                </h1>
            </div>
            <table class="table table-condensed table-hover table-bordered"
                   style="word-break:break-all; word-wrap:break-all;">
                <thead>
                <tr>
                    <th>
                        昵称
                    </th>

                    <th>
                        openid
                    </th>

                    <th>
                        上次更新时间
                    </th>
                </tr>
                </thead>
                <tbody>

                <#if wxUsersDTOList??>
                    <#list wxUsersDTOList as wxUsersDTO>
                        <tr>
                            <td>
                                ${wxUsersDTO.nickname}
                            </td>
                            <td>
                                ${wxUsersDTO.openid}
                            </td>
                            <td>
                                ${wxUsersDTO.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                        </tr>
                    </#list>
                </#if>

                </tbody>
            </table>
        </div>
    </div>
</div>

<#include "../include/footer.ftl"/>

<#include "../include/base-js.ftl"/>
<script>

</script>
</body>

</html>
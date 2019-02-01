<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>我的信息</title>
</head>

<body>

<div class="container-fluid container" style="text-align: center">
    <div class="row-fluid row">
        <div class="span12">
            <img alt="${wxUsersDTO.nickname!''}" src="${wxUsersDTO.headimgurl!''}" class="img-circle"/>
            <p>${wxUsersDTO.nickname !''}</p>

            <div class="row clearfix" style="margin-top: 5px;">
                <div class="col-md-12 column">
                    <a href="/wechat/getRedPacket" type="button" class="btn btn-default btn-info">领红包</a>
                    <a href="/wechat/userInfo?openid=${wxUsersDTO.openid}" style="margin-left: 2px;margin-right: 2px;"
                       type="button" class="btn btn-warning">首页</a>
                    <a href="/wechat/invitation" type="button" class="btn btn-success">邀请</a>
                </div>
            </div>

            <div class="page-header">
                <h1>
                    个人信息
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
                        ${wxUsersDTO.nickname}
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        openid
                    </td>
                    <td>
                        ${wxUsersDTO.openid}
                    </td>
                </tr>


                <tr>
                    <td>
                        性别
                    </td>
                    <td>
                        <#if wxUsersDTO.sex==0>
                            未知
                        <#elseif wxUsersDTO.sex==1>
                            男
                        <#elseif wxUsersDTO.sex==2>
                            女
                        </#if>
                    </td>
                </tr>

                <tr>
                    <td>
                        国家
                    </td>
                    <td>
                        ${wxUsersDTO.country}
                    </td>
                </tr>

                <tr>
                    <td>
                        省份
                    </td>
                    <td>
                        ${wxUsersDTO.province}
                    </td>
                </tr>

                <tr>
                    <td>
                        头像地址
                    </td>
                    <td>
                        ${wxUsersDTO.headimgurl}
                    </td>
                </tr>

                <tr>
                    <td>
                        邀请人昵称
                    </td>
                    <td>
                        ${invitationName !'无邀请人'}
                    </td>
                </tr>

                <tr>
                    <td>
                        信息最后更新时间
                    </td>
                    <td>
                        ${wxUsersDTO.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                    </td>
                </tr>
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
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
                    <span class="label label-primary">邀请人数:${size}个</span>
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

            <#if isOldUser??>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/wechat/submitInvitationCode">
                            <div class="form-group">
                                <label for="exampleInputEmail1">邀请码:</label>
                                <input type="text" class="form-control" id="invitationCode"/>
                            </div>
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
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
                        领取金额
                    </th>
                    <th>
                        最后登录时间
                    </th>
                </tr>
                </thead>
                <tbody>

                <#if redPacketDetailsDTOList??>
                    <#list redPacketDetailsDTOList as redPacketDetailsDTO>
                        <tr>
                            <td>
                                ${redPacketDetailsDTO.password}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.money}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                            <td>
                                ${redPacketDetailsDTO.getTime}
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
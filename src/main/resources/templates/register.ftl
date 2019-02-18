<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <#include "include/head.ftl"/>
    <link href="/css/register.css" rel="stylesheet">
    <title>注册-huijava</title>
</head>

<body>

<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "include/navbar.ftl"/>
        </div>


        <div class="col-md-12 column">
            <div class="col-md-8 column">

                <div class="col-md-6 col-md-offset-3">

                    <div class="col-md-12 column" style="text-align: center">
                        <h3>
                            <a href="/">huijava-注册(尚在开发中.)</a>
                        </h3>
                    </div>

                    <form action="/" class="">

                        <div class="form-group has-feedback">
                            <label for="username">用户名</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input name="form" id="username" class="form-control form-input" placeholder="请输入用户名"
                                       maxlength="32"
                                       minlength="3"
                                       type="text">
                            </div>

                            <span style="color:red;display: none;" class="tips"></span>
                            <span style="display: none;"
                                  class=" glyphicon glyphicon-remove form-control-feedback"></span>
                            <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                        </div>

                        <div class="form-group has-feedback">
                            <label for="password">密码</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input name="form" id="password" class="form-control form-input" placeholder="请输入密码"
                                       maxlength="32"
                                       minlength="6"
                                       type="password">
                            </div>

                            <span style="color:red;display: none;" class="tips"></span>
                            <span style="display: none;"
                                  class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                        </div>

                        <div class="form-group has-feedback">
                            <label for="passwordConfirm">确认密码</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input name="form" id="passwordConfirm" class="form-control form-input"
                                       placeholder="请再次输入密码" maxlength="32"
                                       minlength="6"
                                       type="password">
                            </div>
                            <span style="color:red;display: none;" class="tips"></span>
                            <span style="display: none;"
                                  class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                        </div>


                        <div class="row">
                            <div class="col-xs-7">
                                <div class="form-group has-feedback">
                                    <label for="idcode-btn">验证码</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span>
                                        <input name="form" id="idcode-btn" class="form-control form-input"
                                               placeholder="请输入验证码" maxlength="4"
                                               type="text">
                                    </div>
                                    <span style="color:red;display: none;" class="tips"></span>
                                    <span style="display: none;"
                                          class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    <span style="display: none;"
                                          class="glyphicon glyphicon-ok form-control-feedback"></span>
                                </div>
                            </div>
                            <div class="col-xs-5" style="padding-top: 30px">
                                <div id="idcode" style="background: transparent;"></div>
                            </div>
                        </div>

                        <div class="form-group has-feedback">
                            <label for="email">邮箱</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-open-file"></span></span>
                                <input name="form" id="email" class="form-control form-input" placeholder="请输入邮箱"
                                       type="email">
                            </div>
                            <span style="color:red;display: none;" class="tips"></span>
                            <span style="display: none;"
                                  class="glyphicon glyphicon-remove form-control-feedback"></span>
                            <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                        </div>

                        <div class="row">
                            <div class="col-xs-7">
                                <div class="form-group has-feedback">
                                    <label for="emailCode">邮箱校验码</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span>
                                        <input name="form" id="emailCode" class="form-control form-input"
                                               placeholder="校验码" maxlength="6"
                                               type="text">
                                    </div>
                                    <span style="color:red;display: none;" class="tips"></span>
                                    <span style="display: none;"
                                          class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    <span style="display: none;"
                                          class="glyphicon glyphicon-ok form-control-feedback"></span>
                                </div>
                            </div>
                            <div class="col-xs-5 text-center" style="padding-top: 26px">
                                <button type="button" id="loadingButton" class="btn btn-primary" autocomplete="off">
                                    获取邮箱校验码
                                </button>
                            </div>
                        </div>

                        <div class="form-group">
                            <input class="form-control btn btn-primary" id="submit"
                                   value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册"
                                   type="submit">
                        </div>

                        <div class="form-group">
                            <input value="重置" id="reset" class="form-control btn btn-danger" type="reset">
                        </div>
                    </form>
                </div>

            </div>

            <div class="col-md-4 column" style="margin-top: 20px">

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

<script>
    var settings = {
        e: 'idcode',
        codeType: {
            name: 'follow',
            len: 4
        }, //len是修改验证码长度的
        codeTip: '看不清?',
        inputID: 'idcode-btn' //验证元素的ID
    };

    var _set = {
        storeLable: 'codeval',
        store: '#ehong-code-input',
        codeval: '#ehong-code'
    }
    $.idcode = {
        getCode: function (option) {
            _commSetting(option);
            return _storeData(_set.storeLable, null);
        },
        setCode: function (option) {
            _commSetting(option);
            _setCodeStyle("#" + settings.e, settings.codeType.name, settings.codeType.len);

        },
        validateCode: function (option) {
            _commSetting(option);
            var inputV;
            if (settings.inputID) {
                inputV = $('#' + settings.inputID).val();

            } else {
                inputV = $(_set.store).val();
            }
            if (inputV.toUpperCase() == _storeData(_set.storeLable, null).toUpperCase()) { //修改的不区分大小写
                return true;
            } else {
                _setCodeStyle("#" + settings.e, settings.codeType.name, settings.codeType.len);
                return false;
            }
        }
    };

    function _commSetting(option) {
        $.extend(settings, option);
    }

    function _storeData(dataLabel, data) {
        var store = $(_set.codeval).get(0);
        if (data) {
            $.data(store, dataLabel, data);
        } else {
            return $.data(store, dataLabel);
        }
    }

    function _setCodeStyle(eid, codeType, codeLength) {
        var codeObj = _createCode(settings.codeType.name, settings.codeType.len);
        var randNum = Math.floor(Math.random() * 6);
        var htmlCode = ''
        if (!settings.inputID) {
            htmlCode = '<span><input id="ehong-code-input" type="text" maxlength="4" /></span>';
        }
        htmlCode += '<div id="ehong-code" class="ehong-idcode-val ehong-idcode-val';
        htmlCode += String(randNum);
        htmlCode += '" href="#" onblur="return false" onfocus="return false" oncontextmenu="return false" onclick="$.idcode.setCode()">'
            + _setStyle(codeObj) + '</div>' + '<span id="ehong-code-tip-ck" class="ehong-code-val-tip" onclick="$.idcode.setCode()">'
            + settings.codeTip + '</span>';
        $(eid).html(htmlCode);
        _storeData(_set.storeLable, codeObj);
    }

    function _setStyle(codeObj) {
        var fnCodeObj = new Array();
        var col = new Array('#BF0C43', '#E69A2A', '#707F02', '#18975F', '#BC3087', '#73C841', '#780320', '#90719B', '#1F72D8', '#D6A03C', '#6B486E', '#243F5F', '#16BDB5');
        var charIndex;
        for (var i = 0; i < codeObj.length; i++) {
            charIndex = Math.floor(Math.random() * col.length);
            fnCodeObj.push('<font color="' + col[charIndex] + '">' + codeObj.charAt(i) + '</font>');
        }
        return fnCodeObj.join('');
    }

    function _createCode(codeType, codeLength) {
        var codeObj;
        if (codeType == 'follow') {
            codeObj = _createCodeFollow(codeLength);
        } else if (codeType == 'calc') {
            codeObj = _createCodeCalc(codeLength);
        } else {
            codeObj = "";
        }
        return codeObj;
    }

    function _createCodeCalc(codeLength) {
        var code1, code2, codeResult;
        var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        var charIndex;
        for (var i = 0; i < codeLength; i++) {
            charIndex = Math.floor(Math.random() * selectChar.length);
            code1 += selectChar[charIndex];

            charIndex = Math.floor(Math.random() * selectChar.length);
            code2 += selectChar[charIndex];
        }
        return [parseInt(code1), parseInt(code2), parseInt(code1) + parseInt(code2)];
    }

    function _createCodeFollow(codeLength) {
        var code = "";
        var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

        for (var i = 0; i < codeLength; i++) {
            var charIndex = Math.floor(Math.random() * selectChar.length);
            if (charIndex % 2 == 0) {
                code += selectChar[charIndex].toLowerCase();
            } else {
                code += selectChar[charIndex];
            }
        }
        return code;
    }

    var regUsername = /^[a-zA-Z_][a-zA-Z0-9_]{2,31}$/;
    var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;
    var regPasswordAlpha = /[a-zA-Z]/;
    var regPasswordNum = /[0-9]/;
    var password;
    var check = [false, false, false, false, false, false];

    //校验成功函数
    function success(Obj, counter) {
        Obj.parent().parent().removeClass('has-error').addClass('has-success');
        $('.tips').eq(counter).hide();
        $('.glyphicon-ok').eq(counter).show();
        $('.glyphicon-remove').eq(counter).hide();
        check[counter] = true;

    }

    // 校验失败函数
    function fail(Obj, counter, msg) {
        Obj.parent().parent().removeClass('has-success').addClass('has-error');
        $('.glyphicon-remove').eq(counter).show();
        $('.glyphicon-ok').eq(counter).hide();
        $('.tips').eq(counter).text(msg).show();
        check[counter] = false;
    }

    // 用户名匹配
    $('#username').change(function () {


        if (regUsername.test($(this).val())) {
            success($(this), 0);
        } else if ($(this).val().length < 3) {
            fail($(this), 0, '用户名太短，不能少于3个字符');
        } else {
            fail($(this), 0, '用户名只能为英文数字和下划线,且不能以数字开头')
        }

    });


    // 密码匹配
    // 匹配字母、数字、特殊字符至少两种的函数
    function atLeastTwo(password) {
        var a = regPasswordSpecial.test(password) ? 1 : 0;
        var b = regPasswordAlpha.test(password) ? 1 : 0;
        var c = regPasswordNum.test(password) ? 1 : 0;
        return a + b + c;

    }

    $('#password').change(function () {
        password = $(this).val();
        if ($(this).val().length < 6) {
            fail($(this), 1, '密码太短，不能少于6个字符');
        } else if ($(this).val().length > 32) {
            fail($(this), 1, '密码太长，不能大于32个字符');
        } else {
            if (atLeastTwo($(this).val()) < 2) {
                fail($(this), 1, '密码中至少包含字母、数字、特殊字符的两种')
            } else {
                success($(this), 1);
            }
        }
    });


    // 再次输入密码校验
    $('#passwordConfirm').change(function () {
        if ($(this).val() == password) {
            success($(this), 2);
        } else {
            fail($(this), 2, '两次输入的密码不一致');
        }

    });


    // 验证码
    $.idcode.setCode();
    $('#idcode-btn').change(function () {
        var IsBy = $.idcode.validateCode();
        if (IsBy) {
            success($(this), 3);
        } else {
            fail($(this), 3, '验证码输入错误');
        }
    });

    // 邮箱
    var regPhoneNum = /^([A-Za-z0-9_\-\.\u4e00-\u9fa5])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,8})$/;
    $('#email').change(function () {
        if (regPhoneNum.test($(this).val())) {
            success($(this), 4);
        } else {
            fail($(this), 4, '邮箱格式不对');
        }
    });

    //邮箱验证码
    var regMsg = '1234';
    $('#emailCode').change(function () {
        if (check[4]) {
            if (regMsg == $(this).val()) {
                success($(this), 5);
            } else {
                fail($(this), 5, '邮箱验证码错误');
            }
        } else {
            $('#email').parent().parent().removeClass('has-success').addClass('has-error');
        }

    });


    $('#loadingButton').click(function () {

        if (check[4]) {
            $(this).removeClass('btn-primary').addClass('disabled');

            $(this).html('<span class="red">59</span> 秒后重新获取');
            var secondObj = $('#loadingButton').find('span');
            var secondObjVal = secondObj.text();

            function secondCounter() {

                var secondTimer = setTimeout(function () {
                    secondObjVal--;
                    secondObj.text(secondObjVal);
                    secondCounter();
                }, 1000);
                if (secondObjVal == 0) {
                    clearTimeout(secondTimer);
                    $('#loadingButton').text('重新获取校验码');
                    $('#loadingButton').removeClass('disabled').addClass('btn-primary');

                }
            }

            secondCounter();
        } else {
            $('.form-input').eq(4).parent().parent().removeClass('has-success').addClass('has-error');
        }

    });

    $('#submit').click(function (e) {
        if (!check.every(function (value) {
            return value == true
        })) {
            e.preventDefault();
            for (key in check) {
                if (!check[key]) {
                    $('.form-input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
                }
            }
        }
    });

    $('#reset').click(function () {
        $('input').slice(0, 6).parent().parent().removeClass('has-error has-success');
        $('.tips').hide();
        $('.glyphicon-ok').hide();
        $('.glyphicon-remove').hide();
        check = [false, false, false, false, false, false,];
    });
</script>

</body>

</html>
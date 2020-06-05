<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>重庆市丽笙网络科技</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${base}/finance/static/images/logo.ico">
    <link rel="stylesheet" href="${base}/finance/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/finance/static/css/login.css?t=${.now?long}" media="all"/>
    <style type="text/css">
        .loginTip {
            height: 50px;
            width: 260px;
            padding: 20px;
            border-radius: 4px;
            position: absolute;
            left: 50%;
            top: 85%;
            margin: -150px 0 0 -150px;
            z-index: 99;
        }

        .loginTip span {
            text-align: center;
            color: #fff;
            font-size: 16px;
        }

        .login_btn1 {
            background: #4AA2FF;
        }
    </style>
</head>
<body>
<div id="bg-body"></div>
<div class="login">
    <h1>丽笙网络科技</h1>
    <form class="layui-form" action="${base}/finance/userInfo/login" method="post">
        <div class="layui-form-item">
            <input class="layui-input" name="mobile" placeholder="手机号" lay-verify="required" type="text"
                   autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password"
                   autocomplete="off">
        </div>
        <#--<div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text"
                   autocomplete="off">
            <div class="code"><img src="${base}/finance/genCaptcha" width="116" height="36" id="mycode"></div>
        </div>-->
        <div class="layui-form-item">
            <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
        </div>
        <button class="layui-btn login_btn login_btn1" lay-submit="" lay-filter="login">登录</button>
    </form>
</div>
<div class="loginTip">
    <span>用户名:test &nbsp;&nbsp;&nbsp;密码:1</span>
</div>
<script type="text/javascript" src="${base}/finance/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/finance/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/finance/static/js/jquery.bcat.bgswitcher.js"></script>
<script>
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        $(document).ready(function () {
            /*var srcBgArray = ["http://static.mysiteforme.com/chun.jpg",
                "http://static.mysiteforme.com/xia.jpg",
                "http://static.mysiteforme.com/qiu.jpg",
                "http://static.mysiteforme.com/dong.jpg"];*/
            var srcBgArray = ["${base}/finance/static/images/login1.jpg",
                "${base}/finance/static/images/login2.jpg",
                "${base}/finance/static/images/login3.jpg",
                "${base}/finance/static/images/login4.jpg"];
            $('#bg-body').bcatBGSwitcher({
                timeout: 5000,
                urls: srcBgArray,
                alt: 'Full screen background image'
            });
        });

        $("#mycode").on('click', function () {
            var t = Math.random();
            $("#mycode")[0].src = "${base}/finance/genCaptcha?t= " + t;
        });

        form.on('submit(login)', function (data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            if ($('form').find('input[type="checkbox"]')[0].checked) {
                data.field.rememberMe = true;
            } else {
                data.field.rememberMe = false;
            }
            $.ajax({
                url: data.form.action,
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code == 0) {
                        //缓存信息
                        localStorage.setItem('userinfo', JSON.stringify(res.content));

                        //跳转首页
                        location.href = "${base}/finance/index";
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
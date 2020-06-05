<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>字典编辑--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- 页面描述 -->
    <meta name="description" content="${site.description}"/>
    <!-- 页面关键词 -->
    <meta name="keywords" content="${site.keywords}"/>
    <!-- 网页作者 -->
    <meta name="author" content="${site.author}"/>
    <link rel="stylesheet" href="${base}/finance/static/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .layui-form-item .layui-inline {
            width: 33.333%;
            float: left;
            margin-right: 0;
        }

        @media (max-width: 1240px) {
            .layui-form-item .layui-inline {
                width: 100%;
                float: none;
            }
        }

        .layui-form-item .role-box {
            position: relative;
        }

        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${product.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input layui-disabled" name="type" value="${product.title}"
                   lay-verify="required" placeholder="请输入产品名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属机构</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="label" value="${product.mechanism}" lay-verify="required"
                   placeholder="请输入所属机构">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">办公地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="value" value="${product.workAddress}" lay-verify="required"
                   placeholder="请输入办公地址">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" name="sort" value="${product.tel}" lay-verify="required|number"
                   placeholder="请输入联系方式">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品介绍</label>
        <div class="layui-input-block">
            <textarea name="description" class="layui-textarea"
                      placeholder="可以输入产品介绍">${product.introduce}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/finance/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;

        form.on("submit(save)", function (data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.ajax({
                url: "${base}/finance/financeProduct/updateStockProduct",
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(data.field),
                success: function (res) {
                    layer.close(loadIndex);
                    if (res.code == 0) {
                        parent.layer.msg("产品信息保存成功!", {time: 1000}, function () {
                            //刷新父页面
                            parent.location.reload();
                        });
                    } else {
                        layer.msg(res.message);
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
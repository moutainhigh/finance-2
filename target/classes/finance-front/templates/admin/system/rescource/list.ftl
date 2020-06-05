<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统资源列表--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/finance/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/finance/static/css/user.css" media="all"/>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>资源检索</legend>
    <div class="layui-field-box">
        <form class="layui-form">
            <div class="layui-inline" style="margin-left: 15px">
                <label>资源来源:</label>
                <div class="layui-input-inline">
                    <select name="s_source">
                        <option value="">请选择资源来源</option>
                        <@my type="sys_rescource_source">
                            <#list result as r>
                                <option value="${r.value}">${r.label}</option>
                            </#list>
                        </@my>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>资源类型:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_type" placeholder="请输入类型(.jpg/.png)"
                           class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>hash值:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_hash" placeholder="请输入hash值" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger" data-type="deleteSome">批量删除</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>

    <script type="text/html" id="source">
        <@my type="sys_rescource_source">
            <#list result as r>
                {{#  if(d.source == '${r.value}'){ }}
                <span>${r.label}</span>
                {{#  } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="webUrl">
        {{# if(d.fileType.indexOf("gif")>=0 || d.fileType.indexOf("jpg")>=0  || d.fileType.indexOf("png")>=0 ){ }}
        <span class="layer-photos-demo" id="pic_{{d.id}}"><img src="{{ d.webUrl }}" layer-src="{{ d.webUrl }}"
                                                               style="width: 40px" lay-event="showPic"></span>
        {{# }else{ }}
        <span>{{ d.webUrl }}</span>
        {{# } }}
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<div id='jsonContainer' class="Canvas" style="display: none"></div>
<script>
    var baseDir = '${base}/finance';
</script>
<script type="text/javascript" src="${base}/finance/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/finance/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'table'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            t;//表格数据变量
        t = {
            elem: '#test',
            url: '${base}/finance/financeProduct/list',
            method: 'post',
            data: $('.layui-form').serialize(),
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            width: $(parent.window).width() - 223,
            cols: [[
                {type: 'checkbox'},
                {field: 'title', title: '产品名称'},
                {field: 'mechanism', title: '所属机构'},
                {field: 'industryDirect', title: '行业方向'},
                {field: 'financeQuota', title: '融资额度'},
                {fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑字典",
                    type: 2,
                    content: "${base}/finance/product/queryProduct?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回字典列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if (obj.event === "del") {
                layer.confirm("你确定要删除该资源么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        alert("1");
                        $.ajax({
                            url: "${base}/finance/financeProduct/deleteProduct",
                            type: 'post',
                            contentType: 'application/json',
                            data: JSON.stringify({"productIds": data.id}),
                            success: function (res) {
                                if (res.code == 0) {
                                    layer.msg("删除成功", {time: 1000}, function () {
                                        table.reload('test', t);
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            },
                            error: function (res) {
                                console.log(res);
                            }
                        });
                    }
                )
            }
            if (obj.event === "showPic") {
                layer.photos({
                    photos: '#pic_' + data.id,
                    anim: 5
                });
            }
        });

        //功能按钮
        var active = {
            deleteSome: function () {                        //批量删除
                var checkStatus = table.checkStatus('test'),
                    data = checkStatus.data;
                if (data.length > 0) {
                    layer.confirm("你确定要删除这些资源么？", {btn: ['是的,我确定', '我再想想']},
                        function () {
                            var d = [];
                            for (var i = 0; i < data.length; i++) {
                                d.push(data[i].id);
                            }
                            $.ajax({
                                url: "${base}/finance/financeProduct/deleteProduct",
                                type: 'post',
                                contentType: 'application/json',
                                data: JSON.stringify({"productIds": d.join(',')}),
                                success: function (res) {
                                    if (res.code == 0) {
                                        layer.msg("删除成功", {time: 1000}, function () {
                                            table.reload('test', t);
                                        });
                                    } else {
                                        layer.msg(res.message);
                                    }
                                },
                                error: function (res) {
                                    console.log(res);
                                }
                            });
                        }
                    )
                } else {
                    layer.msg("请选择需要删除的资源", {time: 1000});
                }
            }
        };

        $('.layui-inline .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //搜索
        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });
</script>
</body>
</html>
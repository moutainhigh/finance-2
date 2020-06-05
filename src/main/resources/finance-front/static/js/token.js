var boolExpire = get("userinfo");
if (boolExpire) {
    set("userinfo");
} else {
    localStorage.clear();
    layui.use(['layer'], function () {
        var layer = layui.layer;//弹层
        layer.msg("登录信息已过期，请重新登录！", {time: 1000}, function () {
            //跳转首页
            location.assign(location.origin + "/finance/loginh");
        });
    });
}

function set(key) {
    var expireTime = Math.floor((new Date().getTime() + 1800000) / 1000);

    var userInfo = localStorage.getItem(key);
    var userInfoJSON = JSON.parse(userInfo);
    userInfoJSON["time"] = expireTime;

    localStorage.setItem(key, JSON.stringify(userInfoJSON));
}

function get(key) {
    var data = localStorage.getItem(key);
    if (!data) {
        return false;
    }
    var dataObj = JSON.parse(data);
    console.log(Math.floor(new Date().getTime() / 1000) - dataObj.time);
    if (Math.floor(new Date().getTime() / 1000) - dataObj.time < 0) {
        return true;
    } else {
        return false;
    }
}
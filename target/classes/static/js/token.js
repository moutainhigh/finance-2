function set(key, value) {
    var curTime = new Date().getTime();
    localStorage.setItem(key, JSON.stringify({data: value, time: curTime}));
}

function get(key, exp) {
    var data = localStorage.getItem(key);
    if (!data) {
        return false;
    }
    var dataObj = JSON.parse(data);
    if (new Date().getTime() - dataObj.time > exp) {
        return true;
    } else {
        return false;
    }
}
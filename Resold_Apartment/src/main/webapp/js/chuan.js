//时间
var wait=60;
function time(o){
    if (wait == 0) {
        o.removeAttribute("disabled");
        o.value="免费获取验证码";
        wait = 60;
    } else {

        o.setAttribute("disabled", true);
        o.value="重新发送(" + wait + ")";
        wait--;
        setTimeout(function() {
                time(o)
            },
            1000)
    }
}

document.getElementById("btn").onclick=function(){time(this);

    var mobile = document.getElementById('mobile').value;//获取页面的手机号
    //通过ajax传递数据到servlet

    $.ajax('/Resold_Apartment/SendSmsServlet', {
        type: 'POST',
        url: '/Resold_Apartment/SendSmsServlet',
        dataType: 'json',
        data: {"mobile": mobile},
        async: true,//禁用异步，变成同步
        success: function (result) {
            alert(result);
            if (result) {
                console.log("ture");
            } else {
                console.log("false");
            }
        }
    });
    alert("验证码发送成功")

}



//传输手机号给后端
function buttonClick() {


}



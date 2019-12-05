<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/11/28
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="js/jquery-3.4.1.js"></script>
    <!--注意导入顺序-->
</head>
<body>
<form class="layui-form" action="http://king.vipgz1.idcfengye.com/Resold_Apartment/RegisterServlet?key=1" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-inline">
            <input type="text" name="mobile" id="mobile" required lay-verify="required" placeholder="请输手机号" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">验证码</label>
        <div class="layui-input-inline">
            <input type="password" name="verifyCode" id="verifyCode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off"
                   class="layui-input">
        </div>
        <input type="submit" id="btn" class="layui-btn layui-btn-radius layui-btn-normal" value="免费获取验证码" />

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input class="layui-btn" type="submit" value="注册并绑定" >

            </div>
        </div>
    </div>

</form>


<script src="js/chuan.js"></script>

<script>

    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
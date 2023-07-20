<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>打分</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css"/>
</head>
<body>
<div class="layui-content">
    <div class="layui-row">
        <div class="layui-card">
            <div class="layui-card-header">打分</div>
            
            <form class="layui-form layui-card-body" action="${pageContext.request.contextPath}/teacher/doPingFen" method="post">


                <input type="hidden" value="${id}" name="id">
                <input type="hidden" value="${stuid}" name="stuid">
                <input type="hidden" value="${teaid}" name="teaid">

                <div class="layui-form-item">
                    <label class="layui-form-label">学生姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" value="${list.name}" readonly="readonly" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">请输入分数：</label>
                    <div class="layui-input-block">
                        <input type="text" name="soure" required    lay-verify="required" placeholder="请请输入分数" autocomplete="off" class="layui-input " style="width:190px">
                    </div>
                </div>



                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemo">提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary" onclick="window.history.go(-1)">返回</button>
                    </div>
                </div>
                
            </form>
            
        </div>
    </div>
</div>
<script  >
function changer(){

    var jb = $("input[name='jb']").val();

    if(jb==undefined||jb==null||jb==''){
        alert('请填写基本工资');
        return;
    }
    var yl = $("input[name='yl']").val((Number(jb)*0.08).toFixed(2));
    var ylbx = $("input[name='ylbx']").val((Number(jb)*0.02).toFixed(2)+3);
    var sy = $("input[name='sy']").val((Number(jb)*0.005).toFixed(2));
    var gjj = $("input[name='gjj']").val((Number(jb)*0.08).toFixed(2));






}


//乘法
function accMul(arg1,arg2) {
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}
</script>
<script src="${pageContext.request.contextPath}/assets/layui.all.js"></script>
<script>
    var form = layui.form
            ,layer = layui.layer;
</script>
<script src="${pageContext.request.contextPath}/assets/jquery-3.4.1.js"></script>
</body>
</html>
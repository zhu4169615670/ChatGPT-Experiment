<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/view.css"/>
    <title>毕业答辩评分系统</title>
</head>
<body class="layui-view-body">
<form method="post" action="${pageContext.request.contextPath}/teacher/findAll" id="listform">
    <!--用于标记当前页数的input，默认为1，跳转时-->
    <input type="hidden" name="pageIndex" value="1">
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <h2 class="title">学生评分信息</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <c:if test="${flag==0}">
                    <div class="form-box">
                        <h6>暂无老师评分信息！</h6>
                    </div>
                    </c:if>
                    <c:if test="${flag==1}">
                    <div class="form-box">

                        <!--表内容放这里-->
                        <table id="table_content" class="layui-table" align="center">
                            <tr>
                                <th>教师姓名</th>
                                <th>教师评分</th>
                                <th>评分时间</th>

                            <c:forEach var="stu" items="${list}">
                                <tr>
                                    <td align="center">${stu.name}</td>
                                    <td align="center">${stu.source}</td>
                                    <td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${stu.date}"/></td>
                                </tr>
                            </c:forEach>
                        </table>

                        <h2 style="color: #0e90d2;font-weight: bolder;font-size: 18px;">打分人数：${count}---->平均分为：${avg}</h2>

                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/assets/layui.all.js"></script>
<script>
    //获取表单并提交，此处也可以使用ajax实现，不过多赘述
    function page_nav(frm, num) {
        //改变name 为 pageIndex的input的值
        frm.pageIndex.value = num;
        frm.submit();
    }
</script>
<script>
    //单个删除
    function Del(id) {
        if (confirm("您确定要删除吗?")) {
            return true;
        } else {
            return false;
        }
    }

    // 核心：拼接完整的html格式文档并填充数据
    //使用outerHTML属性获取整个table元素的HTML代码， 包括根标签<table></table>
    // 自定义封装html格式文档<html><head></head><body></body></html>
    // 设置字符集，告诉浏览器以utf8方式解析，避免乱码<meta charset='utf-8'/>
    // 获取table数据并填充到自定义的html格式文档中
    var table_content = document.querySelector("#table_content").outerHTML;
    var html = "<html><head><meta charset='utf-8' /></head><body>" + table_content + "</body></html>";

    // 实例化一个Blob对象，
    // param1：包含文件内容的数组，
    // param2：包含文件类型属性的对象
    var blob = new Blob([html], {
        type: "application/vnd.ms-excel"
    });
    var output_table = document.querySelector("#output_table");
    // 利用URL.createObjectURL()方法为a元素生成blob URL
    output_table.href = URL.createObjectURL(blob);
    // 设置文件名，低级浏览器不支持
    output_table.download = "学生入住信息表.xls";


</script>


</body>
</html>
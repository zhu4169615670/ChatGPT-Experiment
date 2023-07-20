<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login1.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login2style.css">
</head>

<body>
    <div id="formContainer" class="dwo">
    <!-- 
        <div class="formLeft">
            <img src="${pageContext.request.contextPath}/images/bg.png">
        </div>
         -->
        <div class="formRight" >
            <!-- Login form -->
            
            <form id="reg" method="post" action="${pageContext.request.contextPath}/reg">
                <header>
                    <h1>毕业答辩评分系统</h1>
                </header>
                <section>
                    <label>
                        <p>用户名:</p>
                        <input type="text" placeholder="" name="no">
                        <div class="border"></div>
                    </label>
                    <label>
                        <p>密码:</p>
                        <input type="password" placeholder="" name="password">
                        <div class="border"></div>
                       
                    </label>
                    <label>
                        <p>请选择用户角色:</p>
                        <select name="roleid" onclick="regadd()" id="roleid">
                            <option value="2">学生</option>
                            <option value="3">老师</option>
                            <option value="4">管理员</option>
                        </select>
                        <div class="border"></div>

                    </label>


                     <div id="stuinfo">
                         <label>
                             <p>学生班级:</p>
                             <input type="text" placeholder="" id="bjname" name="name">
                             <div class="border"></div>
                         </label>
                         <label>
                             <p>学生年龄:</p>
                             <input type="text" placeholder="" id="bjage" name="age">
                             <div class="border"></div>

                         </label>
                         <label>
                             <p>学生编号:</p>
                             <input type="text" placeholder="" id="bjbh" name="bianhao">
                             <div class="border"></div>

                         </label>
                     </div>

                    <div id="tacinfo" style="display: none;">
                        <label>
                            <p>教师职称:</p>
                            <input type="text" placeholder="" id="name" name="name">
                            <div class="border"></div>
                        </label>
                        <label>
                            <p>教师年龄:</p>
                            <input type="text" placeholder=""  id="age"name="age">
                            <div class="border"></div>

                        </label>
                        <label>
                            <p>教师姓名:</p>
                            <input type="text" placeholder="" id="bianhao" name="bianhao">
                            <div class="border"></div>

                        </label>
                        <label>
                            <p>教师爱好:</p>
                            <input type="text" placeholder="" id="habby" name="habby">
                            <div class="border"></div>

                        </label>
                    </div>
                    <button type="submit" style="width: 50%">注  册</button>
                </section>
               <%--  <footer>
                <h6 style="text-align: center;color:#fffffb;text-shadow: 5px 5px 10px #f1f1f1;" class="text">${message}</h6>
            </footer> --%>
            </form>

            <!-- Register form -->
            <%--登录提示信息--%>
                <span style="text-align: center;color: red">${message}</span>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/lgjquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/lgscript.js"></script>

<script>
    function regadd(){
       var s = document.getElementById("roleid").value;
       var name
       if(s==2){
            $("#tacinfo").hide();
            $("#habby").val("");
            $("#name").val("");
            $("#age").val("");
            $("#bianhao").val("");
           $("#stuinfo").show();

       }
       if(s==3){
           $("#stuinfo").hide();
           $("#bjname").val("");
           $("#bjage").val("");
           $("#bjbh").val("");
           $("#tacinfo").show();
       }
        if(s==4){
            $("#tacinfo").hide();
            $("#stuinfo").hide();
        }
    }

</script>
</body>
</html>
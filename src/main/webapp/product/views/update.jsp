<%@ page import="app.entities.FailCause" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/6/19
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        .w3-myfont {
            font-family: "Helvetica";
        }
    </style>
</head>

<body class="w3-light-grey w3-myfont">

<div>
    <div class="w3-container w3-padding">
        <%
            if (request.getAttribute("cause") != null) {
                List<FailCause> causes= (List<FailCause>)request.getAttribute("cause");
                out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-grey w3-border w3-border-grey w3-hover-border-grey\">×</span>\n" +
                        "   <h5>Невозможно обновить данные пользователя с именем \"" + request.getAttribute("userName") + "\". Causes:");
                for (FailCause f: causes) {
                    out.println("<p>" + f);
                }
                out.print("</h5>\n</div>");
            } else if (request.getAttribute("userName") != null){
                out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-gray w3-border w3-border-gray w3-hover-border-grey\">×</span>\n" +
                        "   <h5>User '" + request.getAttribute("userName") + "' updated!</h5>\n" +
                        "</div>");
            }
        %>
    </div>

    <div class="w3-container">
        <div class="w3-card-4">
            <div class="w3-container w3-teal w3-padding">
                <h2 class="w3-center">Update user</h2>
            </div>
            <form method="post" class="w3-selection w3-light-grey w3-padding">
                <label>Old name:
                    <input type="text" name="oldName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <label>New name:
                    <input type="text" name="newName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <label>Old password:
                    <input type="password" name="oldPass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <label>New password:
                    <input type="password" name="newPass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <button type="submit" class="w3-btn w3-green w3-hover-light-blue w3-round-large w3-margin-bottom">Update</button>
            </form>
        </div>
    </div>
</div>

<div class="w3-padding">
    <button class="w3-padding w3-btn w3-green w3-hover-light-blue w3-round-large w3-margin-bottom" onclick="location.href='..'">Back to main</button>
</div>
</body>
</html>

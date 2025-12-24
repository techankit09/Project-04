<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.AccountCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.AccountBean"%>
<%@page import="in.co.rays.proj4.util.PropertyReader"%>

<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AccountBean" scope="request"></jsp:useBean>

<html>
<head>
    <title>Account</title>
</head>

<body>

<form action="<%=ORSView.ACCOUNT_CTL%>" method="post">

    <input type="hidden" name="id" value="<%=bean.getId()%>">

    <center>

        <h1>
            <%= (bean.getId() > 0) ? "Update Account" : "Add Account" %>
        </h1>

        <h3>
            <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
            <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
        </h3>

        <table border="0">

            <tr>
                <th align="left">Account No <font color="red">*</font></th>
                <td>
                    <input type="text" name="accountNo"
                        value="<%=DataUtility.getStringData(bean.getAccountNo())%>">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage("accountNo", request)%>
                    </font>
                </td>
            </tr>

            <tr>
                <th align="left">Account Type <font color="red">*</font></th>
                <td>
                    <input type="text" name="accountType"
                        value="<%=DataUtility.getStringData(bean.getAccountType())%>">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage("accountType", request)%>
                    </font>
                </td>
            </tr>

            <tr>
                <th align="left">Bank Name <font color="red">*</font></th>
                <td>
                    <input type="text" name="bankName"
                        value="<%=DataUtility.getStringData(bean.getBankName())%>">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage("bankName", request)%>
                    </font>
                </td>
            </tr>

            <tr>
                <th align="left">Balance <font color="red">*</font></th>
                <td>
                    <input type="text" name="balance"
                        value="<%=DataUtility.getStringData(bean.getBalance())%>">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage("balance", request)%>
                    </font>
                </td>
            </tr>

            <tr>
                <th></th>
                <td>
                    <% if (bean.getId() > 0) { %>
                        <input type="submit" name="operation" value="<%=AccountCtl.OP_UPDATE%>">
                        <input type="submit" name="operation" value="<%=AccountCtl.OP_CANCEL%>">
                    <% } else { %>
                        <input type="submit" name="operation" value="<%=AccountCtl.OP_SAVE%>">
                        <input type="submit" name="operation" value="<%=AccountCtl.OP_RESET%>">
                    <% } %>
                </td>
            </tr>

        </table>

    </center>

</form>

</body>
</html>

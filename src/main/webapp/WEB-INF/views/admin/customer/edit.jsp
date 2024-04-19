<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 2/28/2024
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var = "customerAPI" value ="/api/customer"/>
<html>
<head>
    <title>Thêm hoặc sửa khách hàng</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-contenthàng-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try { ace.settings.check('breadcrumbs', 'fixed')} catch (e) { }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Sửa hoặc thêm khách hàng
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row" style="font-family: Times New Roman, Times, serif;">
                    <form:form modelAttribute = "customerEdit" id = "listForm" method = "GET">
                        <div class="col-xs-12">
                            <form action="" class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-xs-3">Tên khách hàng</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="fullname"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Email</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="email" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="phone" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên công ty</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="companyName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Nhu cầu</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="demand"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tình trạng</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="status" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Cập nhập</button>
                                            <button type="button" class="btn btn-primary" id = "btnCancel">Hủy</button>
                                        </c:if>
                                        <c:if test="${empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Thêm mới</button>
                                            <button type="button" class="btn btn-primary" id = "btnCancel">Hủy</button>
                                        </c:if>
                                    </div>
                                </div>
                                <form:hidden path="id" id = "customerId"/>
                            </form>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<script>

    function addOrUpdateCustomer(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
            },

            error: function (respond) {
                console.log("failed");
                console.log(respond);
                window.location.href="<c:url value = "/admin/customer-list"/>";
            }
        })
    }


    $('#btnCancel').click(function () {
        window.location.href="/admin/customer-list";
    });


    $('#btnAddOrUpdateCustomer').click(function () {
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        addOrUpdateCustomer(data);
    });

</script>
</body>
</html>

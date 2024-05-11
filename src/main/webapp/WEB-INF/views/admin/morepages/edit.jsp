<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 2/28/2024
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var = "buildingAPI" value ="/api/user"/>
<html>
<head>
    <title>Sửa thông tin cá nhân</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
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
                        Sửa thông tin cá nhân
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row" style="font-family: Times New Roman, Times, serif;">
                    <form:form modelAttribute = "UserEditInfo" id = "listForm" method = "GET">
                        <div class="col-xs-12">
                            <form action="" class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-xs-3">Tên đầy đủ</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="fullName" value = "" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Địa chỉ</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path = "address" value = ""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="phone" value = ""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Email</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="email" value = ""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                        <form:input class = "form-control" path="note" value = ""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty UserEditInfo.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Cập nhập</button>
                                            <button type="button" class="btn btn-primary" id = "btnCancel">Hủy</button>
                                        </c:if>
                                    </div>
                                </div>
                                <form:hidden path="id" id = "buildingId"/>
                            </form>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<script>

    var imageBase64 = '';
    var imageName = '';

    function addOrUpdateBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/edit",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
            },

            error: function (respond) {
                console.log("failed");
                console.log(respond);
                window.location.href="<c:url value = "/admin/morepages-profile"/>";
            }
        })
    }


    $('#btnCancel').click(function () {
        window.location.href="/admin/morepages-profile";
    });


    $('#btnAddOrUpdateBuilding').click(function () {
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });

        if ('' !== imageBase64) {
            data['imageBase64'] = imageBase64;
            data['imageName'] = imageName;
        }
        $('loading_image').show();
        if(data != {}) {
            addOrUpdateBuilding(data);
        }
        else
        {
            window.location.href="<c:url value = "/admin/morepages-profile"/>";
        }
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name;
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var = "customerAPI" value ="/api/customer" />
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <%--<spring:message code="label.user.list"/>--%>
        Danh sách khách hàng
    </title>
</head>

<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">
                        <%--<spring:message code="label.home"/>--%>
                        Trang chủ
                    </a>
                </li>
                <li class="active">
                    <%--<spring:message code="label.user.list"/>--%>
                    Danh sách khách hàng
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Danh sách khách hàng
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header ui-sortable-handle">
                            <h5 class="widget-title">Tìm kiếm</h5>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="font-family:'Times New Roman', Times, serif">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearchCustomer" id="listForm" action="${customerListURL}" method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <label class="name">Tên đầy đủ</label>
                                                    <form:input class="form-control" path="fullname"/>
                                                </div>

                                                <div class="col-sm-6">
                                                    <label class="name">Số điện thoại </label>
                                                    <form:input class = "form-control" path="phone"/>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label class="name">Email</label>
                                                    <form:input class = "form-control" path="email"/>
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                <div class="col-sm-2">
                                                    <label class="name">Nhân viên</label>
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="">---Chọn Nhân viên---</form:option>
                                                        <form:options items = "${listStaffs}"/>
                                                    </form:select>
                                                </div>
                                                </security:authorize>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <button class="btn btn-xs btn-danger" id="btnSearchCustomer">Tìm
                                                        kiếm</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>

                    <div class="pull-right">
                        <a href="/admin/customer-edit">
                            <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-info" title="Thêm khách hàng">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                        <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                    </svg>
                            </button>
                            </security:authorize>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                        <button class="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomers">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
                            </svg>
                        </button>
                        </security:authorize>
                    </div>
                </div>
            </div>

            <!-- Bảng danh sách -->
            <div class="row">
                <div class="col-xs-12">
                    <table id="tableList" style="margin: 3em 0 1.5em;"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        </thead>

                        <tbody>
                        <form:form modelAttribute="customerList">
                            <display:table name="customerList.listResult" cellspacing="0" cellpadding="0"
                                           requestURI="${customerListURL}" partialList="true" sort="external"
                                           size="${customerList.totalItems}" defaultsort="2" defaultorder="ascending"
                                           id="tableList" pagesize="${customerList.maxPageItems}"
                                           export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                headerClass="center select-cell">
                                    <fieldset>
                                        <input type="checkbox" name="checkList" value="${tableList.id}"
                                               id="checkbox_${tableList.id}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>
                                <display:column headerClass="text-left" property="fullname" title="Tên khách hàng"/>
                                <display:column headerClass="text-left" property="phone" title="Số điện thoại"/>
                                <display:column headerClass="text-left" property="email" title="Email"/>
                                <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                                <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                                <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                                <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                                <display:column headerClass="col-actions" title="Thao tác">
                                    <security:authorize access="hasRole('MANAGER')">
                                    <a title="Giao khách hàng" class="btn btn-xs btn-success" onclick="assignmentCustomer(${tableList.id});">
                                        <i class="ace-icon glyphicon glyphicon-list"></i>
                                    </a>
                                    </security:authorize>
                                    <a class="btn btn-xs btn-info" title="Sửa thông tin khách hàng"
                                       href='<c:url value="/admin/customer-edit-${tableList.id}"/>'>
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                    <a class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick="deleteCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </a>
                                    </security:authorize>
                                </display:column>
                            </display:table>
                        </form:form>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-container -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table style="margin: 3em 0 1.5em;" class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th>Tên nhân viên</th>

                    </tr>
                    </thead>

                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" name="customerId" id="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" id="" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        loadStaff(customerId);
        $('#customerId').val(customerId);
    }

    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + '/staffs',
            // data: JSON.stringify(data),
            // contentType: "application/json",
            dataType: "JSON",
            success: function (response)
            {
                var row = '';
                $.each(response.data, function (index,item)
                {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class = "check-box-element"'  + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.info("Success");
            },

            error: function (response)
            {
                console.log("failed");
                window.location.href="<c:url value = "/admin/customer-list?message=error"/>";
                console.log(respond);
            }
        })
    }

    $('#btnassignmentCustomer').click(function(e) {
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function() {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(data['staffs'] != '')
        {
            assignment(data);
        }
        console.log("ok");
    })

    function assignment(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/" + 'assignment-customer',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response)
            {
                console.info("Success");
                window.location.href="/admin/customer-list";
                alert("Giao thành công");
            },

            error: function (response)
            {
                console.info("Giao không thành công!");
                window.location.href="<c:url value = "/admin/customer-list?message=erro"/>";
                console.log(respond);
                alert("Giao không thành công");
            }
        })
    }

    $('#btnSearchCustomer').click(function (e)
    {
        e.preventDefault();
        $('#listForm').submit();
    })



    function deleteCustomer(id)
    {
        var customerId = [id];
        deleteCustomers(customerId);
    }

    $('#btnDeleteCustomers').click(function (e) {
        var customerIds = [];
        $('input[name="checkList"]:checked').each(function() {
            customerIds.push($(this).val());
        });
        deleteCustomers(customerIds);
    })

    function deleteCustomers(data)
    {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/" + data,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
                alert("Xóa thành công");
                window.location.href="<c:url value = "/admin/customer-list"/>";
            },
            error: function (respond) {
                console.log("failed");
                console.log(respond);
                window.location.href="<c:url value = "/admin/customer-list"/>";
                alert("Thất bại");
            }
        })
    }
</script>
</body>

</html>
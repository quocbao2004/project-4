<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Profile</title>
</head>

<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>

                <li>
                    <a href="#">More Pages</a>
                </li>
                <li class="active">Profile</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    User Profile Page
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        User Information
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div>
                        <div id="user-profile-1" class="user-profile row">
                            <div class="col-xs-12 col-sm-3 center">
                                <div>
                                    <div class="space-4"></div>
                                    <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                        <div class="inline position-relative">
                                            <a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
                                                <i class="ace-icon fa fa-circle light-green"></i>
                                                &nbsp;
                                                <span class="white">${UserInfo.fullName}</span>
                                            </a>

                                            <ul class="align-left dropdown-menu dropdown-caret dropdown-lighter">
                                                <li class="dropdown-header"> Change Status </li>
                                                <!-- Bổ sung thêm các tuỳ chọn tùy thuộc vào nhu cầu -->
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <div class="space-6"></div>

                                <div class="profile-contact-info">
                                    <!-- Thông tin liên hệ của người dùng -->
                                </div>

                                <div class="hr hr12 dotted"></div>

                                <div class="clearfix">
                                    <div class="grid2">
                                        <span class="bigger-175 blue">45.547</span>
                                        <br />
                                        Followers
                                    </div>

                                    <div class="grid2">
                                        <span class="bigger-175 blue">1</span>
                                        <br />
                                        Following
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-9">
                                <div class="profile-user-info profile-user-info-striped">
                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Username </div>
                                        <div class="profile-info-value">
                                            <span class="editable" id="username">${UserInfo.fullName}</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Location </div>
                                        <div class="profile-info-value">
                                            <i class="fa fa-map-marker light-orange bigger-110"></i>
                                            <span class="editable" id="country">${UserInfo.address}</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Phone </div>
                                        <div class="profile-info-value">
                                            <span class="editable" id="phone">${UserInfo.phone}</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Last Online </div>
                                        <div class="profile-info-value">
                                            <span class="editable" id="login">Online</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Email </div>
                                        <div class="profile-info-value">
                                            <span class="editable" id="email">${UserInfo.email}</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> About Me </div>
                                        <div class="profile-info-value">
                                            <span class="editable" id="about">${UserInfo.note}</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Sửa thông tin </div>
                                        <a class="btn btn-xs btn-info" title="Sửa thông tin"
                                           href='<c:url value="/admin/morepages-edit-${UserInfo.id}"/>'>
                                            <span class="editable">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </span>
                                        </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</body>
</html

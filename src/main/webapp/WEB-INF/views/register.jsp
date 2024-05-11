<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng ký</title>
</head>
<body>
<div class="container">
    <div class="register-form">
        <div class="main-div">
            <div class="container-fluid" >
                <section class="gradient-custom">
                    <div class="page-wrapper">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                                    <div class="card-body p-5">
                                        <div class="mb-md-5 mt-md-4 pb-5 text-center">
                                            <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                                            <form action="register" id="formRegister" method="POST">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="fullName">Full name</label>
                                                    <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Tên đầy đủ">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="userName">User name </label>
                                                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Tên đăng nhập">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="retype_password">retype password</label>
                                                    <input type="password" class="form-control" id="retype_password" name="retype_password" placeholder="Nhắc Lại Mật khẩu">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="phone">Phone number</label>
                                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Số điện thoại">
                                                </div>

                                                <button type="submit" class="btn btn-primary" id = "model-register">Đăng ký</button>
                                            </form>
                                            <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-google fa-lg"></i></a>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0 tex-center account">Do you have an account? <a href="<c:url value='/login'/>" class="text-white-50 fw-bold">Login</a></p>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>

$('#model-register').click(function (e) {
    e.preventDefault();
    var fullName = $('input[name="fullName"]').val();
    var userName = $('input[name="userName"]').val();
    var password = $('input[name="password"]').val();
    var retype_password = $('input[name="retype_password"]').val();
    var phone = $('input[name="phone"]').val();
    var formRegister = {
        fullName: fullName,
        userName: userName,
        phone: phone,
        retype_password: retype_password,
        password: password
    };

    $.ajax({
        url: '/register',
        type: 'POST',
        data: JSON.stringify(formRegister),
        contentType: "application/json",
        success: function(response) {
            alert("Đăng kí thành công");
            window.location.href="<c:url value = "/trang-chu"/>";
        },
        error: function(xhr, status, error) {
            alert("Đăng kí không thành công");
            window.location.href="<c:url value = "/trang-chu"/>";
        }
    });
});

</script>
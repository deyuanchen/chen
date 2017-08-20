/**
 * Created by chen on 2017/8/19.
 */
$(function() {
    // 点击登录按钮
    $('#login-bt').click(function() {
        login();
    });
    // 回车事件
    $('#login-name, #login-pass').keypress(function (event) {
        if (13 == event.keyCode) {
            login();
        }
    });
});
function login() {
    $.ajax({
        url: '/sso/login',
        type: 'POST',
        data: {
            username: $('#login-name').val(),
            password: $('#login-pass').val(),
            rememberMe: $('#rememberMe').is(':checked'),
            
        },
        beforeSend: function() {

        },
        success: function(json){
            if (json.code == 1) {
                location.href = json.data;
            } else {
                alert(json.data);
                if (10101 == json.code) {
                    $('#username').focus();
                }
                if (10102 == json.code) {
                    $('#password').focus();
                }
            }
        },
        error: function(error){
            console.log(error);
        }
    });
}

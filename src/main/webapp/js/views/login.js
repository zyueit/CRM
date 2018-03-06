/** 提交登陆表单 */
function subForm() {
    $.post("/user/login", $("form").serialize(), function (data) {
        if (data.success) {
            window.location.href = "/index";
        } else {
            alert(data.msg);
        }
    });
}

/** 重置登陆表单 */
function resetForm() {
    $("form input[name]").val("");
}
var globalEnv = '_GLOBAL_ENV_'
var serverAPIUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverAPIUrl
(function ($) {

    // 登出
    $("#signout").on("click", function () {
        $.ajax({
            type: "GET",
            url: serverAPIUrl+"api/account/signout"
        }).done(function (data) {
            if (data.success === "true") {
                VISITOR = {};
                getSession();
            }
        });
    });

})(jQuery);

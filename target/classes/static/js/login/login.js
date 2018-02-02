$(function () {

    $("#login").on("click", function (e) {
        e.preventDefault();
        $username = $("#username");
        $password = $("#password");

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/remita/user/validateLogin",
            //url: "https://sdprofectus.herokuapp.com/api/v1/remita/user/validateLogin",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "staffNoOrEmail": $username.val(),
                "password": $password.val()
            }),
            success: function (data) {
                if (data.responseCode && data.responseCode !== "00") {

                    $("#info-message").text(data.responseMessage);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {
                    //success
                    $("#info-message").text(data.responseMessage);
                    $("#infoDiv").addClass("alert-success").show();

                    window.localStorage.setItem("user", JSON.stringify(data.data));
                    window.location = 'home.html'
                }

            },
            error: function () {

                $("#info-message").text("cannot login at this time. Kindly contact Solutions delivery support.");
                $("#infoDiv").addClass("alert-danger").show();

                return false;
            }
        });
    });

});
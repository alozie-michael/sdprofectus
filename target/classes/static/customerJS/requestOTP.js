$('#requestOTP').on('click', function (e) {

    e.preventDefault();
    $.ajax({
        type: "POST",
        //url: "http://localhost:8080/api/v1/remita/sd/requestOtp",
        url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/solutionsdelivery/sd/requestOtp",
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        data: JSON.stringify({

            "mandateId": $("#mandateId").val(),
            "requestId": $("#requestId").val(),

        }),
        success: function (data) {
            if (data.statuscode && data.statuscode !== "00") {

                console.log(data);
                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-danger").show();

            } else {
                //success
                console.log(data);

                window.localStorage.setItem("validateOTP", JSON.stringify(data));

                $.each(data.authParams, function (i, item) {

                    var description1 = item.description1;
                    var description2 = item.description2;

                    $("#description1").text(description1);
                    $("#description2").text(description2);

                });

                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-success").show();

                $('#requestOTPDiv').addClass("requestOTPDiv");
                $('#validateOTPDiv').removeClass("validateOTPDiv");

            }
        },
        error: function (e) {

            $("#info-message").text("error processing");
            $("#infoDiv").addClass("alert-danger").show();
            console.log(e);
            return false;
        }
    });
});

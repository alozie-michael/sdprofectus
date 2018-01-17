$('#validateOTP').on('click', function (e) {

    e.preventDefault();
    var validateOTP = window.localStorage.getItem("validateOTP");
    var remitaTransRef = validateOTP.remitaTransRef;

    $OTP = $("#OTP").val();
    $cardDetails = $("#cardDetails").val();

    $.ajax({
        type: "POST",
        //url: "http://localhost:8080/api/v1/remita/sd/validateOTP",
        url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/validateOTP",
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        data: JSON.stringify({

            "remitaTransRef": remitaTransRef,
            "authParams": [{
                "param1": "OTP",
                "value": $OTP.val()
            },
                {
                    "param2": "CARD",
                    "value": $cardDetails.val()
                }]
        }),
        success: function (data) {
            if (data.statuscode && data.statuscode !== "00") {

                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-danger").show();

            } else {

                //success

                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-success").show();

                //$('#requestOTPDiv').remove("requestOTPDiv");
                $('#validateOTPDiv').addClass("validateOTPDiv");
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

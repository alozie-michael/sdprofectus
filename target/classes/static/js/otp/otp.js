$(function ($) {

    var remitaTransRef = "";

    $('#requestOTP').on('click', function (e) {

        $accountNumber = $('#accountNo');
        $bank = $('#bank');

        e.preventDefault();
        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/OTP/requestOtp",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/OTP/requestOtp",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "accountNo": $accountNumber.val(),
                "bankCode": $bank.val()

            }),
            success: function (data) {
                var response = data.data;

                if (response.responseCode && response.responseCode !== "00") {

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {
                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-success").show();

                    remitaTransRef = response.remitaTransRef;
                    $.each(response.authParams, function (i, item) {

                        $('#description1').text(item.description1);
                        $('#description2').text(item.description2)

                    });

                    $('#requestOTPDiv').addClass('hideDiv');
                    $('#validateOTPDiv').removeClass('hideDiv');
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


    $('#validateOTP').on('click', function (e) {

        $OTP = $('#OTP');
        $cardDetails = $('#cardDetails');

        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/OTP/validateOTP",
            //url: "http://localhost:8080/api/v1/remita/OTP/validateOTP",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "remitaTransRef": remitaTransRef,
                "authParams": [
                    {
                        "param1": "OTP",
                        "value": $OTP.val()
                    },
                    {
                        "param1": "CARD",
                        "value": $cardDetails.val()
                    }
                ]

            }),
            success: function (data) {
                var response = data.data;

                if (response.responseCode && response.responseCode !== "00") {

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {

                    //success
                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-success").show();

                    $('#requestOTPDiv').removeClass('hideDiv');
                    $('#validateOTPDiv').addClass('hideDiv');
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



});
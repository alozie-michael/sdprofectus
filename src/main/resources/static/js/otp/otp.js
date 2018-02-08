$(function ($) {

    var remitaTransRef = "";
    var bankCode = "";
    var accNo = "";

    $('#requestOTP').on('click', function (e) {

        var accountNumber = $('#accountNo').val();
        var bank = $('#bank').val();

        bankCode = bank;
        accNo = accountNumber;

        e.preventDefault();
        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/OTP/requestOtp",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/OTP/requestOtp",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "accountNo": accountNumber,
                "bankCode": bank

            }),
            success: function (data) {
                $('#otpReportDiv').removeClass('hideDiv');

                $('#bankCode').text(bankCode);
                $('#accNo').text(accNo);
                $('#rOtpResponse').html(JSON.stringify(data));

                var response = data.data;

                if (response.responseCode && response.responseCode !== "00") {

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {

                    remitaTransRef = response.remitaTransRef;

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-success").show();

                    if(bankCode === "057")
                        $('#description').removeClass('hideDiv');

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

        var jsonData = "";

        if(bankCode === "057"){
            jsonData = {

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
            }
        }else {

            jsonData = {

                "remitaTransRef": remitaTransRef,
                "authParams": [
                    {
                        "param1": "OTP",
                        "value": $OTP.val()
                    }
                ]
            }
        }

        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/OTP/validateOTP",
            //url: "http://localhost:8080/api/v1/remita/OTP/validateOTP",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify(jsonData),
            success: function (data) {

                $('#vOtpResponse').html(JSON.stringify(data));

                var response = data.data;
                if (response.responseCode && response.responseCode !== "00") {

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {

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
$(function ($) {

    $('#showAddAccount').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showSinglePayment').removeClass('btn-inverse');
        $('#requestOTPDiv').removeClass('hideDiv');
        $('#validateOTPDiv').addClass('hideDiv');
        $('#singlePayment').addClass('hideDiv');

    });

    $('#showSinglePayment').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showAddAccount').removeClass('btn-inverse');
        $('#singlePayment').removeClass('hideDiv');
        $('#requestOTPDiv').addClass('hideDiv');
        $('#validateOTPDiv').addClass('hideDiv');

    });

    /*$('#showBulkPayment').on('click', function (e) {

        e.preventDefault();
        $('#requestOTPDiv').addClass('hideDiv');
        $('#validateOTPDiv').removeClass('hideDiv');

    });

    $('#showPaymentStatus').on('click', function (e) {

        e.preventDefault();
        $('#requestOTPDiv').addClass('hideDiv');
        $('#validateOTPDiv').removeClass('hideDiv');

    });*/

    $('#paySingle').on('click', function (e) {

        e.preventDefault();
        $beneficiaryName = $('#beneficiaryName');
        $email = $('#email');
        $phone = $('#phone');
        $beneficiaryAccount = $('#beneficiaryAccount');
        $toBank = $('#toBank');
        $amount = $('#amount');
        $narration = $('#narration');

        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/sd/stopMandate",
            url: "http://localhost:8080/api/v1/remita/RPG/singlePayment",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "toBank": $toBank.val(),
                "creditAccount": $beneficiaryAccount.val(),
                "narration": $narration.val(),
                "amount": $amount.val(),
                "beneficiaryEmail": $email.val()

            }),
            success: function (data) {
                var response = data.data;

                if (response.responseCode && response.responseCode !== "00") {

                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {
                    $("#info-message").text(response.responseDescription);
                    $("#infoDiv").addClass("alert-success").show();

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
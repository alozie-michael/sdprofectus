$(function ($) {

    $('#passDebitModal').on('shown.bs.modal', function (e) {
        $('#totalAmount').val('');
    });

    //SHOW MANDATE DIV
    $('#showMandates').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showDebits').removeClass('btn-inverse');
        $('#mandateDiv').removeClass('hideDiv');
        $('#debitDiv').addClass('hideDiv');
        $('#requestOTPDiv').addClass("hideDiv");


    });

    //SHOW DEBIT DIV
    $('#showDebits').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showMandates').removeClass('btn-inverse');
        $('#debitDiv').removeClass('hideDiv');
        $('#mandateDiv').addClass('hideDiv');
        $('#requestOTPDiv').addClass("hideDiv");

    });

    //STOP MANDATE
    $('#mandateTable').on('click', '[data-action="stopMandate"]', function () {

        var response = confirm("Are you sure you want to stop this mandate?");

        if (response) {

            var $row = jQuery(this).closest('tr');
            var $columns = $row.find('td');

            var mandateID = $columns[0].innerHTML.trim();
            var requestId = $columns[6].innerHTML.trim();

            $.ajax({
                type: "POST",
                //url: "http://localhost:8080/api/v1/remita/directdebit/stopMandate",
                url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/stopMandate",
                dataType: 'json',
                contentType: 'application/json',
                crossDomain: true,
                data: JSON.stringify({

                    "mandateId": mandateID,
                    "requestId": requestId

                }),
                success: function (data) {
                    if (data.statuscode && data.statuscode !== "00") {
                        $("#info-message").text(data.status);
                        $("#infoDiv").addClass("alert-danger").show();
                    } else {
                        $("#info-message").text(data.status);
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
        }

    });

    //STOP DEBIT
    $('#debitTable').on('click', '[data-action="stopDebit"]', function () {

        var response = confirm("Are you sure you want to stop this debit?");

        if (response) {

            var $row = jQuery(this).closest('tr');
            var $columns = $row.find('td');

            var mandateID = $columns[0].innerHTML.trim();
            var requestId = $columns[6].innerHTML.trim();

            $.ajax({
                type: "POST",
                //url: "http://localhost:8080/api/v1/remita/directdebit/stopMandate",
                url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/stopDebit",
                dataType: 'json',
                contentType: 'application/json',
                crossDomain: true,
                data: JSON.stringify({

                    "mandateId": mandateID,
                    "requestId": requestId

                }),
                success: function (data) {
                    if (data.statuscode && data.statuscode !== "00") {
                        $("#info-message").text(data.status);
                        $("#infoDiv").addClass("alert-danger").show();
                    } else {
                        $("#info-message").text(data.status);
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
        }

    });


    //ACTIVATE MANDATE --> SHOW OTP REQUEST DIV
    $('#mandateTable').on('click', '[data-action="activateMandate"]', function () {

        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');

        $("#mandateId").val($columns[0].innerHTML);
        $("#requestId").val($columns[6].innerHTML);

        $('#mandateDiv').addClass('hideDiv');
        $('#requestOTPDiv').removeClass("hideDiv");

    });

    //PASS DEBIT --> SHOW DEBIT FORM
    $('#debitTable').on('click', '[data-action="passDebit"]', function () {

        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');

        var mandateId = $columns[0].innerHTML;
        mandateId = mandateId.replace('Mandate ID:', '').trim();

        $("#debitMandateId").val(mandateId);

        $('#passDebitModal').modal('toggle');

    });

    var remitaTransRef = '';

    //REQUEST OTP FOR MANDATE ACTIVATION
    $('#requestOTP').on('click', function (e) {

        e.preventDefault();
        $mandateId = $('#mandateId');
        $requestId = $('#requestId');

        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/directdebit/requestOtp",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/requestOtp",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "mandateId": $mandateId.val(),
                "requestId": $requestId.val()

            }),
            success: function (data) {

                if (data.statuscode && data.statuscode !== "00") {

                    $("#info-message").text(data.status);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {

                    $("#info-message").text(data.status);
                    $("#infoDiv").addClass("alert-success").show();

                    remitaTransRef = data.remitaTransRef;

                    $.each(data.authParams, function (i, item) {

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

    //VALIDATE OTP FOR MANDATE ACTIVATION
    $('#validateOTP').on('click', function (e) {

        e.preventDefault();
        console.log(remitaTransRef);
        $OTP = $('#OTP');
        $cardDetails = $('#cardDetails');

        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/directdebit/validateOTP",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/validateOTP",
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
                        "param2": "CARD",
                        "value": $cardDetails.val()
                    }
                ]

            }),
            success: function (data) {

                if (data.statuscode && data.statuscode !== "00") {

                    $("#info-message").text(data.status);
                    $("#infoDiv").addClass("alert-danger").show();

                } else {

                    $("#info-message").text(data.status);
                    $("#infoDiv").addClass("alert-success").show();

                    getMandates();
                    $('#validateOTPDiv').addClass('hideDiv');
                    $('#mandateDiv').removeClass('hideDiv');
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


}(jQuery));

//SETUP MANDATE
$('#setup').on('click', function (e) {
    e.preventDefault();
    $payerName = $("#payerName");
    $payerEmail = $("#payerEmail");
    $payerPhone = $("#payerPhone");
    $payerBankCode = $("#payerBankCode");
    $payerAccount = $("#payerAccount");
    $amount = $("#amount");
    $startDate = $("#startDate");
    $endDate = $("#endDate");
    $maxNoOfDebits = $("#maxNoOfDebits");

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/remita/directdebit/mandateSetup",
        //url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/mandateSetup",
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        data: JSON.stringify({

            "payerName": $payerName.val(),
            "payerEmail": $payerEmail.val(),
            "payerPhone": $payerPhone.val(),
            "payerBankCode": $payerBankCode.val(),
            "payerAccount": $payerAccount.val(),
            "amount": $amount.val(),
            "startDate": $startDate.val(),
            "endDate": $endDate.val(),
            "maxNoOfDebits": $maxNoOfDebits.val()
        }),
        success: function (data) {
            if (data.statuscode && data.statuscode !== "040") {

                $('#mandateSetupModal').modal('toggle');
                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-danger").show();

            } else {
                //success
                getMandates();

                $('#mandateSetupModal').modal('toggle');
                $("#info-message").text("Mandate setup was successful");
                $("#infoDiv").addClass("alert-success").show();

            }
        },
        error: function (e) {

            $('#mandateSetupModal').modal('toggle');
            $("#info-message").text("error processing");
            $("#infoDiv").addClass("alert-danger").show();
            console.log(e);
            return false;
        }
    });
});

//PASS DEBIT ON MANDATE
$('#passDebit').on('click', function (e) {
    e.preventDefault();
    $debitMandateId = $("#debitMandateId");
    $totalAmount = $("#totalAmount");

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/remita/directdebit/debitInstruction",
        //url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/debitInstruction",
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        data: JSON.stringify({

            "mandateId": $debitMandateId.val(),
            "totalAmount": $totalAmount.val()

        }),
        success: function (data) {
            if (data.statuscode && data.statuscode !== "069") {

                $('#passDebitModal').modal('toggle');
                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-danger").show();

            } else {
                //success
                getDebits();
                $('#passDebitModal').modal('toggle');
                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-success").show();
            }
        },
        error: function (e) {

            $('#passDebitModal').modal('toggle');
            $("#info-message").text("error processing");
            $("#infoDiv").addClass("alert-danger").show();
            console.log(e);
            return false;
        }
    });
});

function getMandates() {

    $.ajax({
        type: "GET",
        //url: "http://localhost:8080/api/v1/remita/directdebit/getMandates",
        url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/getMandates",
        contentType: 'application/json',
        crossDomain: true,
        success: function (data) {
            // success
            if (data) {

                if ($.isEmptyObject(data)) {

                    $(".hideTable").hide();

                } else {
                    var tData = "";
                    $
                        .each(data, function (i, item) {
                            tData += "<tr>"
                                + "<td>"
                                + item.mandateId
                                + "</td>"
                                + "<td>"
                                + item.payerName
                                + "</td>"
                                + "<td>"
                                + item.payerPhone
                                + "</td>"
                                + "<td>"
                                + item.payerEmail
                                + "</td>"
                                + "<td>"
                                + item.payerBankCode
                                + "</td>"
                                + "<td>"
                                + item.payerAccount
                                + "</td>"
                                + "<td>"
                                + item.requestId
                                + "</td>"
                                + "<td>"
                                + item.startDate
                                + "</td>"
                                + "<td>"
                                + item.endDate
                                + "</td>"
                                + "<td>"
                                + item.amount
                                + "</td>"
                                + "<td>"
                                + item.maxNoOfDebits
                                + "</td>"
                                + "<td>"
                                + item.createdDate
                                + "</td>"
                                + "<td>"
                                + item.activationDate
                                + "</td>"
                                + "<td>"
                                + item.status
                                + "</td>"
                                + "<td>"
                                + "<a href='" + item.mandateLink + "'>View Mandate</a>"
                                + "</td>"
                                + "<td class=\"td-actions text-right\">\n" +
                                "                <button type=\"button\" rel=\"tooltip\" title=\"Activate Mandate\"\n" +
                                "                        class=\"btn btn-success btn-simple btn-xs\" data-action=\"activateMandate\">\n" +
                                "                    Activate Mandate\n" +
                                "                </button>\n" +
                                "</td>"
                                + "<td class=\"td-actions text-right\">\n" +
                                "                <button type=\"button\" rel=\"tooltip\" title=\"Stop Mandate\"\n" +
                                "                        class=\"btn btn-danger btn-simple btn-xs\" data-action=\"stopMandate\">\n" +
                                "                    <i class=\"ti-close\"></i>\n" +
                                "                </button>\n" +
                                "</td>"
                                + "</tr>";
                        });
                    if (tData)
                        $("#mandateTable").find("tbody").html(tData);

                }
            }
        },
        error: function (e) {
            console.log(e);
            return false;
        }
    });
}

function getDebits() {

    $.ajax({
        type: "GET",
        //url: "http://localhost:8080/api/v1/remita/directdebit/getDebits",
        url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/getDebits",
        contentType: 'application/json',
        crossDomain: true,
        success: function (data) {
            // success
            if (data) {

                if ($.isEmptyObject(data)) {

                    $(".hideTable").hide();

                } else {

                    var tMData = '';
                    $.each(data, function (i, item) {

                        var tSData = '';
                        var tData = '';
                        var mandateId = item.mandateId;

                        tSData += "<tr style='background-color: #e7e7e7'>"
                            + "<td colspan='9'>"
                            + "Mandate ID: " + mandateId
                            + "</td>"
                            + "<td class=\"td-actions text-right\">\n" +
                            "                <button type=\"button\" rel=\"tooltip\" title=\"New Debit\"\n" +
                            "                        class=\"btn btn-success btn-simple btn-xs\" data-action=\"passDebit\">\n New Debit \n" +
                            "                </button>\n" +
                            "</td>"
                            + "</tr>";

                        $.each(item.debitInstructions, function (i, item) {

                            tData += "<tr>"
                                + "<td>"
                                + item.requestId
                                + "</td>"
                                + "<td>"
                                + item.transactionRef
                                + "</td>"
                                + "<td>"
                                + item.totalAmount
                                + "</td>"
                                + "<td>"
                                + item.fundingAccount
                                + "</td>"
                                + "<td>"
                                + item.fundingBank
                                + "</td>"
                                + "<td>"
                                + item.dateCreated
                                + "</td>"
                                + "<td>"
                                + item.lastDebitAmount
                                + "</td>"
                                + "<td>"
                                + item.lastDebitDate
                                + "</td>"
                                + "<td>"
                                + item.balance
                                + "</td>"
                                + "<td class=\"td-actions text-right\">\n" +
                                "                <button type=\"button\" rel=\"tooltip\" title=\"Stop Debit\"\n" +
                                "                        class=\"btn btn-danger btn-simple btn-xs\" data-action=\"stopDebit\">\n" +
                                "                    <i class=\"ti-close\"></i>\n" +
                                "                </button>\n" +
                                "</td>"
                                + "</tr>";

                        });

                        if (tData && tSData)
                            tSData += tData;

                        tMData += tSData;
                    });

                    $("#debitTable").find("tbody").html(tMData);
                }
            }
        },
        error: function (e) {
            console.log(e);
            return false;
        }
    });
}

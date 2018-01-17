$(function ($) {

    $('.mandates').on('click', '[data-action="stopMandate"]', function (e) {

        var response = confirm("Are you sure you want to stop this mandate?");

        if (response){

            var $row = jQuery(this).closest('tr');
            var $columns = $row.find('td');

            var mandateID = $columns[0].innerHTML.trim();
            var requestId = $columns[6].innerHTML.trim();

            $.ajax({
                type: "POST",
                //url: "http://localhost:8080/api/v1/remita/sd/stopMandate",
                url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/stopMandate",
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


    $('.mandates').on('click', '[data-action="activateMandate"]', function (e) {

        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');

        $("#mandateId").val($columns[0].innerHTML);
        $("#requestId").val($columns[6].innerHTML);


        $('#mandateListDiv').css('display', 'none');
        $('#requestOTPDiv').removeClass("requestOTPDiv");

    });



    $("#mandateSetupModal").on("show.bs.modal", function () {
/*
        $("#payerName").val("");
        $("#payerEmail").val("");
        $("#payerPhone").val("");
        $("#payerBankCode").val("");
        $("#payerAccount").val("");
        $("#amount").val("");
        $("#maxNoOfDebits").val("");*/

        $.ajax({
            type: "GET",
            url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/getBanks",
            //url: "http://localhost:8080/api/v1/remita/sd/getBanks",
            contentType: 'application/json',
            crossDomain: true,
            success: function (data) {
                //success

                if (data) {
                    var tData = "";
                    $.each(data, function (i, item) {
                        tData += "<option value= " + item.bankCode + ">" + item.bankName + "</option>"
                    });
                    if (tData)
                        $("#payerBankCode").html(tData);
                }
                return;
            },
            error: function (e) {
                console.log(e);
                return false;
            }
        });
    });

}(jQuery));

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
        //url: "http://localhost:8080/api/v1/remita/sd/mandateSetup",
        url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/mandateSetup",
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
                $.ajax({
                    type: "GET",
                    url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/getMandates",
                    //url: "http://localhost:8080/api/v1/remita/sd/getMandates",
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
                                    $(".mandates tbody").html(tData);

                            }
                        }

                    },
                    error: function (e) {

                        $('#mandateSetupModal').modal('toggle');
                        $("#info-message").text(data.status);
                        $("#infoDiv").addClass("alert-danger").show();
                        console.log(e);
                        return false;
                    }
                });

                $('#mandateSetupModal').modal('toggle');
                $("#info-message").text(data.status);
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
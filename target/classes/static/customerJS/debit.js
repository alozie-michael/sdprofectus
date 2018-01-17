$(function ($) {


    $("#passDebitModal").on('show.bs.modal', function () {

        $("#passDebit-message").text("");
        $("#debitAmount").val("");
    });

    $('.debits').on('click', '[data-action="stopDebit"]', function (e) {

        var response = confirm("Are you sure you want to stop this debit?");

        if (response){

            var $row = jQuery(this).closest('tr');
            var $columns = $row.find('td');

            var requestId = $columns[0].innerHTML.trim();
            var transRef = $columns[1].innerHTML.trim();

            $.ajax({
                type: "POST",
                //url: "http://localhost:8080/api/v1/remita/sd/stopDebit",
                url: "http://localhost:8080/api/v1/remita/sd/stopDebit",
                dataType: 'json',
                contentType: 'application/json',
                crossDomain: true,
                data: JSON.stringify({

                    "requestId":requestId,
                    "transactionRef":transRef
                }),
                success: function (data) {
                    if (data.statuscode && data.statuscode !== "00") {

                        $("#info-message").text(data.status);
                        $("#infoDiv").addClass("alert-danger").show();

                    } else {

                        $("#info-message").text(data.status);
                        $("#infoDiv").addClass("alert-success").show();
                        //success

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

    $('.debits').on('click', '[data-action="passDebit"]', function (e) {

        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');

        var mandateID = $columns[0].innerHTML;
        mandateID = mandateID.replace("Mandate ID:", "").trim();

        $("#mandateId").val(mandateID);
        $("#requestId").val("");
        $('#passDebitModal').modal('toggle');
    });

}(jQuery));


$('#debit').on('click', function (e) {

    $debitAmount = $("#debitAmount");
    $mandateId = $("#mandateId");

    $.ajax({
        type: "POST",
        //url: "http://localhost:8080/api/v1/remita/sd/debitInstruction",
        url: "http://localhost:8080/api/v1/remita/sd/debitInstruction",
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        data: JSON.stringify({

            "mandateId": $mandateId.val(),
            "totalAmount": $debitAmount.val(),
        }),
        success: function (data) {
            if (data.statuscode && data.statuscode !== "040") {

                $('#passDebitModal').modal('toggle');
                $("#info-message").text(data.status);
                $("#infoDiv").addClass("alert-danger").show();

            } else {
                //success
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/api/v1/remita/sd/getDebits",
                    //url: "http://localhost:8080/api/v1/remita/sd/getDebits",
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
                                        + "</tr>"

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

                                $(".debits tbody").html(tMData);
                            }
                        }
                    },
                    error: function (e) {
                        console.log(e);
                        return false;
                    },
                });

            }
            $('#passDebitModal').modal('toggle');
            $("#info-message").text(data.status);
            $("#infoDiv").addClass("alert-success").show();

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

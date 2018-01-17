$(function () {
    $("#se-pre-con").addClass("se-pre-con");
    $("#myContent").load("mandates.html", getMandates());

    $("#side-bar li a").click(function (e) {
        $("#se-pre-con").addClass("se-pre-con");
        $("#se-pre-con").css("display", "");
        e.preventDefault();

        $("#side-bar li").each(function () {
            $(this).removeClass('active');
        });
        $(this).parent().addClass("active");
        var page = $(this).attr("href");

        $("#myContent").load(page, null, function () {

            if (page === "mandates.html") {
                getMandates();
            }
            if (page === "debitinstructions.html") {
                getDebits();
            }
        });
        $(".se-pre-con").fadeOut("slow");
    });

    $(".se-pre-con").fadeOut("slow");

});

function getMandates() {
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
            console.log(e);
            return false;
        }
    });

}

function getDebits() {

    $.ajax({
        type: "GET",
        url: "https://solutionsdelivery-directdebit.herokuapp.com/api/v1/remita/sd/getDebits",
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

// Logout function
$("#logout").on("click", function (e) {
    e.preventDefault();
    window.location = '../index.html'
    window.localStorage.clear();
})

$("#addAccount").on("click", function (e) {
    e.preventDefault();
    $('#accountModal').modal('toggle');
});
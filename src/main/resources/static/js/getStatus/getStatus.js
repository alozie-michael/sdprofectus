$(function () {

    $('#showGetStatus').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showViewMerchants').removeClass('btn-inverse');
        $('#getStatusDiv').removeClass('hideDiv');
        $('#viewMerchantDiv').addClass('hideDiv');

    });

    $('#showViewMerchants').on('click', function (e) {

        e.preventDefault();
        $(this).addClass('btn-inverse');
        $('#showGetStatus').removeClass('btn-inverse');
        $('#viewMerchantDiv').removeClass('hideDiv');
        $("#getStatusReportDiv").addClass("hideDiv");
        $('#getStatusDiv').addClass('hideDiv');

    });

    $('#environment').change(function () {

        if($(this).val() === "live")
            $('#showMerchants').removeClass('hideDiv');
        else
            $('#showMerchants').addClass('hideDiv');
    });



    //GET STATUS
    $('#getStatus').on('click', function (e) {

        e.preventDefault();
        var Merchant = $('#Merchant').val();
        var rrr = $('#rrr').val();
        var environment = $('#environment').val();

        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/status/getStatus",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/status/getStatus",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "RRR": rrr,
                "environment":environment,
                "merchDesc": Merchant

            }),
            success: function (data) {

                    $("#info-message").text("SUCCESS");
                    $("#infoDiv").addClass("alert-success").show();

                    $("#rrrVal").text(data.RRR);
                    $("#amount").text(data.amount);
                    $("#status").text(data.status);
                    $("#orderId").text(data.orderId);
                    $("#message").text(data.message);
                    $("#transactionTime").text(data.transactiontime);

                    $("#getStatusReportDiv").removeClass("hideDiv");

            },
            error: function (e) {
                $("#info-message").text("error processing");
                $("#infoDiv").addClass("alert-danger").show();
                console.log(e);
                return false;
            }
        });

    });

    //ADD MERCHANT
    $('#addMerchant').on('click', function (e) {

        e.preventDefault();
        var merchantId = $('#merchantId').val().trim();
        var merchantName = $('#merchantName').val().trim();
        var apiKey = $('#apiKey').val().trim();
        var description = $('#description').val().trim();

        $.ajax({
            type: "POST",
            //url: "http://localhost:8080/api/v1/remita/status/addMerchant",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/status/addMerchant",
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            data: JSON.stringify({

                "merchantId":merchantId,
                "merchantName":merchantName,
                "apiKey":apiKey,
                "description":description

            }),
            success: function (data) {

                if(data.responseCode && data.responseCode !== "00"){

                    $("#info-message").text(data.responseMessage);
                    $("#infoDiv").addClass("alert-danger").show();
                    $("#addMerchantModal").modal("toggle");

                }else {

                    $("#info-message").text(data.responseMessage);
                    $("#infoDiv").addClass("alert-success").show();

                    getMerchants();

                    $("#addMerchantModal").modal("toggle");

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

    function getMerchants() {
        $.ajax({
            type: "GET",
            //url: "http://localhost:8080/api/v1/remita/status/getMerchants",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/status/getMerchants",
            contentType: 'application/json',
            crossDomain: true,
            success: function (response) {
                // success
                if (response) {

                    var data = response.data;
                    var tData = "";
                    $.each(data, function (i, item) {
                        tData += "<tr>"
                            + "<td>"
                            + item.id
                            + "</td>"
                            + "<td>"
                            + item.merchantName
                            + "</td>"
                            + "<td>"
                            + item.merchantId
                            + "</td>"
                            + "<td>"
                            + item.description
                            + "</td>";
                    });
                    if (tData)
                        $("#merchantTable").find("tbody").html(tData);

                }
            },
            error: function (e) {
                console.log(e);
                return false;
            }
        });
    }

});
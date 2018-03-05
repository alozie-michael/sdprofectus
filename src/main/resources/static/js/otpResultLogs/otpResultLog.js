$(function ($) {

    $("#mainData").on('click', '[data-action="otpResponseByTime"]', function () {

        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');
        var startTime = $columns[0].innerHTML;

        $.ajax({
            type: "GET",
            //url: "http://localhost:8080/api/v1/remita/OTP/requestOTPLog/" + startTime,
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/OTP/requestOTPLog/" + startTime,
            contentType: 'application/json',
            crossDomain: true,
            success: function (data) {

                if (data.responseCode && data.responseCode === "00") {

                    var tData = '';
                    if (!data.otpRequestLogs) {
                    } else {

                        $.each(data.otpRequestLogs, function (i, item) {

                            tData = "<tr>"
                                + "<td style='font-weight:bold'>"
                                + item.startTime
                                + "</td>"
                                + mainlogs()
                                + "</tr>";

                            function mainlogs() {

                                var sData = '';
                                $.each(item.requestResponses, function (i, item) {
                                    sData += "<td>" + item.response + "</td>";
                                });

                                return sData;
                            }
                        });

                        $("#responseModalTable").find("tbody").html(tData);
                    }
                }
            },
            error: function (e) {
                console.log(e);
                return false;
            }
        });

        $('#otpResponseModal').modal('toggle');
    });

}(jQuery));

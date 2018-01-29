$(function () {

    var email = "";
    var userName = "";

    var user = window.localStorage.getItem("user");
    if (user) {
        var userData = JSON.parse(user);

        $.each(userData, function (i, item) {

            email = item.email;
            userName = item.firstName + " " + item.lastName;
        });

    } else {
        window.location = "index.html";
    }

    $("#myContent").load("directdebit.html", function () {

        $("#userName").text(userName);
        $("#userEmail").text(email);
        getMandates();
        getDebits();
    });

    $("#sidebarnav").find("li a").click(function (e) {
        $(".preloader").fadeOut();
        e.preventDefault();
        $("#sidebarnav").find("li").each(function () {
            $(this).removeClass('active');
        });
        $(this).parent().addClass("active");

        var page = $(this).attr("href");

        $("#myContent").load(page, null, function () {

            if (page === "directdebit.html") {
                getMandates();
                getDebits();
            }
            if (page === "users.html") {
                getUsers();
            }
        });
        $(".preloader").fadeOut();
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
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/directdebit/getDebits",
            //url: "http://localhost:8080/api/v1/remita/directdebit/getDebits",
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

    function getUsers() {

        $.ajax({
            type: "GET",
            //url: "http://localhost:8080/api/v1/remita/user/getUsers",
            url: "https://sdprofectus.herokuapp.com/api/v1/remita/user/getUsers",
            contentType: 'application/json',
            crossDomain: true,
            success: function (data) {
                // success
                if (data) {

                    var tData = '';
                    $.each(data.data, function (i, item) {

                        tData += "<tr>"
                            + "<td>"
                            + item.staffNo
                            + "</td>"
                            + "<td>"
                            + item.firstName + " " + item.lastName
                            + "</td>"
                            + "<td>"
                            + item.email
                            + "</td>"
                            + "<td>"
                            + item.telephone
                            + "</td>"
                            + "<td class=\"td-actions text-right\">\n" +
                            "                <button type=\"button\" rel=\"tooltip\" title=\"Remove User\"\n" +
                            "                        class=\"btn btn-danger btn-simple btn-xs\" data-action=\"removeUser\">\n" +
                            "                    <i class=\"ti-close\"></i>\n" +
                            "                </button>\n" +
                            "</td>"
                            + "</tr>";

                    });

                    if (tData)
                        $("#usersTable").find("tbody").html(tData);
                }
            },
            error: function (e) {
                console.log(e);
                return false;
            }
        });

    }

    $("#changePasswordBtn").on("click", function (e) {

        e.preventDefault();
        $("#changePasswordModal").modal("toggle");
    });

    //CHANGE PASSWORD
    $("#changePassword").on("click", function () {

        var email = "";

        var user = window.localStorage.getItem("user");
        if (user) {
            var userData = JSON.parse(user);

            $.each(userData, function (i, item) {

                email = item.email;
            });
        }
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var retryPassword = $("#retypeNewPassword").val();

        if (oldPassword === "" || newPassword === "" || retryPassword === "") {

            $("#passinfo-message").text("all fields are required.");
            $("#passinfoDiv").addClass("alert-danger").show();
            return false;

        } else {

            if (newPassword === retryPassword) {

                $.ajax({

                    type: "POST",
                    //url: "http://localhost:8080/api/v1/remita/user/changePassword",
                    url: "https://sdprofectus.herokuapp.com/api/v1/remita/user/changePassword",
                    dataType: 'json',
                    contentType: 'application/json',
                    crossDomain: true,
                    data: JSON.stringify({

                        "email": email,
                        "oldPassword": oldPassword,
                        "newPassword": newPassword
                    }),
                    success: function (data) {
                        if (data.responseCode && data.responseCode !== "00") {

                            $("#passinfo-message").text(data.responseMessage);
                            $("#passinfoDiv").addClass("alert-danger").show();

                        } else {
                            // success
                            $("#passinfo-message").text(data.responseMessage);
                            $("#passinfoDiv").addClass("alert-success").show();
                            $("#changePasswordModal").modal("toggle");

                        }
                    },
                    error: function (e) {
                        console.log(e);
                        $("#passinfo-message").text("Unable to change password. Please try again later.");
                        $("#passinfoDiv").addClass("alert-danger").show();
                        return false;
                    }
                });

            } else {
                $("#passinfo-message").text("passwords do not match");
                $("#passinfoDiv").addClass("alert-danger").show();
                return false;
            }
        }

    });

    $(".logoutBtn").on("click", function (e) {
        e.preventDefault();
        window.localStorage.removeItem("user");
        window.location = "index.html";
    });

});

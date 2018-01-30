$(function ($) {

    var email = "";

    var user = window.localStorage.getItem("user");
    if (user) {
        var userData = JSON.parse(user);
        $.each(userData, function (i, item) {
            email = item.email;
        });

        $('#addUser').on('click', function () {

            var staffNo = $('#staffNo').val();
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
            var email = $('#email').val();
            var phone = $('#phone').val();
            var password = $('#password').val();

            $.ajax({
                type: "POST",
                //url: "http://localhost:8080/api/v1/remita/user/createUser/" + email,
                url: "https://sdprofectus.herokuapp.com/api/v1/remita/user/createUser/" + email,
                dataType: 'json',
                contentType: 'application/json',
                crossDomain: true,
                data: JSON.stringify({

                    "staffNo": staffNo,
                    "firstName": firstName,
                    "lastName": lastName,
                    "email": email,
                    "telephone": phone,
                    "password": password

                }),
                success: function (data) {

                    if (data.responseCode && data.responseCode !== "00") {

                        $("#newUserModal").modal('toggle');
                        $("#info-message").text(data.responseMessage);
                        $("#infoDiv").addClass("alert-danger").show();

                    } else {

                        $("#newUserModal").modal('toggle');
                        getUsers();
                        $("#info-message").text(data.responseMessage);
                        $("#infoDiv").addClass("alert-success").show();

                    }
                },
                error: function (e) {
                    $("#newUserModal").modal('toggle');
                    $("#info-message").text("error processing");
                    $("#infoDiv").addClass("alert-danger").show();
                    console.log(e);
                    return false;
                }
            });

        });

        //STOP MANDATE
        $('#usersTable').on('click', '[data-action="removeUser"]', function () {

            var response = confirm("Are you sure you want to remove this user?");

            if (response) {

                var $row = jQuery(this).closest('tr');
                var $columns = $row.find('td');

                var staffNo = $columns[0].innerHTML.trim();

                $.ajax({
                    type: "POST",
                    //url: "http://localhost:8080/api/v1/remita/user/removeUser/" + email,
                    url: "https://sdprofectus.herokuapp.com/api/v1/remita/user/removeUser/" + email,
                    dataType: 'json',
                    contentType: 'application/json',
                    crossDomain: true,
                    data: JSON.stringify({

                        "staffNo": staffNo

                    }),
                    success: function (data) {
                        if (data.responseCode && data.responseCode !== "00") {
                            $("#info-message").text(data.responseMessage);
                            $("#infoDiv").addClass("alert-danger").show();
                        } else {

                            getUsers();
                            $("#info-message").text(data.responseMessage);
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
    }
});

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
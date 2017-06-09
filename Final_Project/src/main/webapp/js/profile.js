$(document).ready(function(){
    var $url = 'Controller';
    var $getProfileCommand = 'get-user-json';

    getProfile($url, $getProfileCommand);
});

function getProfile(url, command) {
    var userId = $('#userId').val();

    $.ajax({
        url: url,
        data: {command: command, id: userId },
        success: function(user){
            appendUser(user);
        },
        error: function (xhr, str) {
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });
}

function appendUser(user) {
    $('#editRealName').val(user.realName);
    $('#editNickname').val(user.nickname);
    $('#editEmail').val(user.email);
    $('#editLocation').val(user.location);
}


$(document).ready(function(){
    var $url = 'Controller';
    var $getQuestionCommand = 'get-question-json';
    var $getAnswersCommand = 'get-answers-json';

    getQuestion($url, $getQuestionCommand);
    getAnswers($url, $getAnswersCommand);

});

function getQuestion(url, command) {
    var questionId = $('#question-id').val();

    $.ajax({
        url: url,
        data: {command: command, id: questionId },
        success: function(question){
            appendQuestion(question);
        },
        error: function (xhr, str) {
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });
}

function getAnswers(url, command) {
    var questionId = $('#question-id').val();

    $.ajax({
        url: url,
        data: {command: command, questionId: questionId },
        success: function(answers){
            appendAnswers(answers);
        },
        error: function (xhr, str) {
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });
}

function appendAnswers(answers) {
    $.each(answers, function (index, answer) {
        appendAnswer(answer);
    });
}

function appendAnswer(answer) {
    $('#answers-list').append("<div class='col-xs-12 answer-list-item'><h4>" +
        answer.text + "</h4><div class='row'><div class='col-xs-12 col-md-3'><p>" +
        answer.creation_date + "</p></div><div class='col-xs-12 col-md-4'><p>" +
        answer.user.nickname + "</p></div></div></div>");
}

function appendQuestion(question) {
    // var currentUserId = $('#current-user-id').val();
    var tagName = "";
    if (question.tag != null) {
        tagName = question.tag.name;
    }

    // if(currentUserId == question.user.id) {
    //     $('#edit-question-div').append("<input id='question-header' text>" + question.header);
    // } else {
        $('#question-div').append("<h2 id='question-header'>" + question.header + "</h2>");
        $('#question-div').append("<h3 id='question-text'>" + question.text + "</h3>");
        $('#question-div').append("<div class='row'><div class='col-xs-12 col-md-3'>" +
            "<p id='question-user'>" + question.user.nickname + "</p>" +
            "</div><div class='col-xs-12 col-md-4'><p id='question-tag'>" + tagName +
            "</p></div></div>");
    // }
}

function addAnswer() {
    var answerText = $('#inputAnswerText').val();
    var questionId = $('#question-id').val();
    $.ajax({
        type: 'POST',
        url: 'Controller',
        data: {command: 'add-answer-json', text: answerText, questionId: questionId},
        success: function(answer) {
            appendAnswer(answer);
        },
        error:  function(xhr, str){
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });

}

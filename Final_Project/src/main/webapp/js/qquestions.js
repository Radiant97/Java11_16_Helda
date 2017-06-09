$(document).ready(function(){
    var $url = 'Controller';
    var $getQuestionsCommand = 'get-questions-json';
    var $questionsCount = 10;
    var $startFrom = $questionsCount;
    var inLoadProgress = false;

    getQuestions($url, $getQuestionsCommand, $startFrom, $questionsCount);

    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 200 && !inLoadProgress) {
            $.ajax({
                url: $url,
                data: {command: $getQuestionsCommand, startFrom: $startFrom, questionCount: $questionsCount},
                beforeSend: function () {
                    inLoadProgress = true;
                },
                success: function (tags) {
                    appendQuestions(tags);
                    inLoadProgress = false;
                    $startFrom += $questionsCount;
                },
                error: function (xhr, str) {
                    $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
                }
            });
        }
    });


});

function getQuestions(url, command, startFrom, count) {
    $.ajax({
        url: url,
        data: {command: command , startFrom: 0, questionCount: count},
        success: function(questions){
            appendQuestions(questions);
        },
        error: function (xhr, str) {
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });
}

function appendQuestions(questions) {
    $.each(questions, function (index, question) {
        appendQuestion(question);
    });
}

function appendQuestion(question) {
    var questionHref = "/question?id=" + question.id;
    $('#questions-list').append("<div class='questions-list-item col-xs-12'>" +
        "<a href=" + questionHref + "><div class='question-info'><h3 class='question-header'>" + question.header +
        "</h3><h4 class='question-text'>" + question.text + "</h4>" +
        "</div></a></div>");
}

function addQuestion() {
    var questionHeader = $('#inputQuestionHeader').val();
    var questionText = $('#inputQuestionText').val();
    var questionTag = $('#inputQuestionTag').val();
    $.ajax({
        type: 'POST',
        url: 'Controller',
        data: {command: 'add-question', header: questionHeader, text: questionText, tag: questionTag},
        success: function(question) {
            appendQuestion(question);
        },
        error:  function(xhr, str){
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });

}

$(document).ready(function(){
    var $url = 'Controller';
    var $getTagsCommand = 'get-tags-json';
    var $tagCount = 30;
    var $startFrom = $tagCount;
    var inLoadProgress = false;

    getTags($url, $getTagsCommand, $startFrom, $tagCount);

    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 200 && !inLoadProgress) {
            $.ajax({
                url: $url,
                data: {command: $getTagsCommand, startFrom: $startFrom, tagCount: $tagCount},
                beforeSend: function () {
                    inLoadProgress = true;
                },
                success: function (tags) {
                    appendTags(tags);
                    inLoadProgress = false;
                    $startFrom += $tagCount;
                },
                error: function (xhr, str) {
                    $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
                }
            });
        }
    });


});

function getTags(url, command, startFrom, count) {
    $.ajax({
        url: url,
        data: {command: command , startFrom: 0, tagCount: count},
        success: function(tags){
            appendTags(tags);
        },
        error: function (xhr, str) {
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });
}

function appendTags(tags) {
    $.each(tags, function (index, tag) {
        appendTag(tag);
    });
}

function appendTag(tag) {
    $('#tags-list').append("<div class='tags-list-item col-xs-12 col-md-4'>" +
        "<a href='#'><div class='tag-info'><h3 class='tag-name'>" + tag.name +
        "</h3></div></a></div>");
}

function addTag() {
    var tagName = $('#tagName').val();
    $.ajax({
        type: 'POST',
        url: 'Controller',
        data: {command: 'add_tag', name: tagName},
        success: function(tag) {
            appendTag(tag);
        },
        error:  function(xhr, str){
            $('<h2>', { text: xhr.responseText }).appendTo('.error-div');
        }
    });

}

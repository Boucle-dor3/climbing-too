$(document).ready(function () {

    $(".answer-button-js").click(function () {

        var commentId = $(this).data('comment-id');
        $('.answer-form-js[data-comment-id="' + commentId + '"]').css('display', 'block');

    });

});
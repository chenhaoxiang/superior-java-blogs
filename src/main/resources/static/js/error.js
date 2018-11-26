var s = 5;
$(function () {
    time();

});

function time() {
    if (s <= 0) {
        $("#count-down").html(s);
        location.href = getPath();
    } else {
        $("#count-down").html(s);
        s--;
        setTimeout(function () {
                time()
            },
            1000)

    }
}
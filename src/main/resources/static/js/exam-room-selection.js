$(document).ready(function () {
    let roomOptions = $("#roomId>option")
    $("#exam").on('change', function () {
        let examId = this.value
        console.log(this.value)
        roomOptions.each(function () {
            if ($(this).data('exam') != examId) {
                $(this).attr('hidden', '')
            } else {
                $(this).removeAttr('hidden')
            }
        })
    })
})
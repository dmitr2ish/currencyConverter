$(function () {
    updateHistory($('#login').html())
    $('#deleteAllHistroy').on('click', event => deleteAllHistroy(event));
})

function updateHistory(login) {
    $('#history').empty();
    $.ajax({
        type: 'GET',
        url: '/api/history/list/' + login,
        success: function (historyList) {
            historyList.forEach(history =>
                $('#history')
                    .append("<tr>" +
                        "<td>" + history.initialCharCodeCurrency + "</td>" +
                        "<td>" + history.targetCharCodeCurrency + "</td>" +
                        "<td>" + history.initialValueCurrency + "</td>" +
                        "<td>" + history.targetValueCurrency + "</td>" +
                        "<td>" + history.date + "</td>" +
                        "</tr>"))
        }
    });
}

function deleteAllHistroy(e) {
    e.preventDefault();

    let login = $('#login').html();

    $('#history').empty();

    $.ajax({
        url: '/api/history/deleteAll/' + login,
        type: 'DELETE',
        dataType: 'json',
        contentType: 'application/json'
    })

}
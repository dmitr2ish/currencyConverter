$(function () {
    $('#convert').on('click', event => convertingCurrency(event));
})

function convertingCurrency(e) {
    e.preventDefault();
    let initialChar;
    let targetChar;

    let sum = $('#initialValueCurrency').val()
    let firstValueElement = 0;
    let secondValueElement = 0;

    let selindOne = document.getElementById("checkbox1").options.selectedIndex;
    let idOne = document.getElementById("checkbox1").options[selindOne].value;

    //getting first element
    $.ajax({
        url: '/api/currency/' + idOne,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        success: function (currency) {
            initialChar = currency.charCode;
            firstValueElement = currency.value * currency.nominal * sum;
        }
    })

    let selindTwo = document.getElementById("checkbox2").options.selectedIndex;
    let idTwo = document.getElementById("checkbox2").options[selindTwo].value;

    //getting second element
    $.ajax({
        url: '/api/currency/' + idTwo,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        success: function (currency) {
            targetChar = currency.charCode;
            secondValueElement = (1 / currency.value) * currency.nominal;
        }
    })

    // the result of the conversion
    let result = (firstValueElement * secondValueElement).toFixed(2);

    let history = {
        initialCharCodeCurrency: initialChar,
        targetCharCodeCurrency: targetChar,
        initialValueCurrency: sum,
        targetValueCurrency: result,
        date: $('#date').html(),
        login: $('#login').html()
    };

    //save history in data base
    $.ajax({
        url: '/api/history/add',
        method: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(history),
        complete: function () {
            updateHistory(history.login);
        }
    })

    //show result to user
    $('#targetValueCurrency').val(result);
}
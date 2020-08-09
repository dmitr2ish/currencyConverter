$(function () {
    $('#convert').on('click', event => convertingCurrency(event));
})

function convertingCurrency(e) {
    e.preventDefault();

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
            secondValueElement = (1 / currency.value) * currency.nominal;
        }
    })

    let result = firstValueElement * secondValueElement;

    $('#targetValueCurrency').val(result.toFixed(2));
}
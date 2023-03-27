$(document).ready(function () {
    getList();
});

function search() {
    var category = $("#category option:selected").val();
    var product = $("#kinds option:selected").val();
    $('#priceArea').empty();

    if (category == 'fruit') {
        getFruitPrice(product)
    } else if (category == 'vegetable') {
        getVegetablePrice(product)
    }
}

function getList() {
    var selected = $("#category option:selected").val();
    $('#kinds').empty();
    $('#priceArea').empty();
    if (selected === "fruit") {
        getFruitList();
    } else {
        getVegetableList();
    }
}

function getFruitList() {
    $.ajax({
        type: "GET",
        url: "/fruits/token",
        success: function (res) {
            $.ajax({
                type: "GET",
                url: "/fruits/list",
                headers: {
                    "token": res.data.accessToken
                },
                success: function (res) {
                    for (var i = 0; i < res.data.length; i++) {
                        var option = $("<option>" + res.data[i] + "</option>");
                        $('#kinds').append(option);
                    }
                }
            });
        },
    });
}

function getVegetableList() {
    $.ajax({
        type: "GET",
        url: "/vegetables/token",
        success: function (res) {
            $.ajax({
                type: "GET",
                url: "/vegetables/list",
                headers: {
                    "token": res.data.accessToken
                },
                success: function (res) {
                    for (var i = 0; i < res.data.length; i++) {
                        var option = $("<option>" + res.data[i] + "</option>");
                        $('#kinds').append(option);
                    }
                }
            });
        },
    });
}

function getFruitPrice(product) {
    $.ajax({
        type: "GET",
        url: "/fruits/token",
        success: function (res) {
            $.ajax({
                type: "GET",
                url: "/fruits/price",
                headers: {
                    "token": res.data.accessToken
                },
                data: {"name": product},
                success: function (res) {
                    $('#priceArea').append(res.data.price);

                }
            });
        },
    });
}

function getVegetablePrice(product) {
    $.ajax({
        type: "GET",
        url: "/vegetables/token",
        success: function (res) {
            $.ajax({
                type: "GET",
                url: "/vegetables/price",
                headers: {
                    "token": res.data.accessToken
                },
                data: {"name": product},
                success: function (res) {
                    $('#priceArea').append(res.data.price);
                }
            });
        },
    });
}



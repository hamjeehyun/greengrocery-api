$(document).ready(function () {
    getList();
});

function getList() {
    var selected = $("#category option:selected").val();
    $('#products').empty();
    $('#priceArea').empty();
    if (selected === "fruit") {
        getFruitList();
    } else {
        getVegetableList();
    }
}

function getPrice() {
    var category = $("#category option:selected").val();
    var product = $("#products option:selected").val();
    $('#priceArea').empty();

    if (category == 'fruit') {
        getFruitPrice(product)
    } else if (category == 'vegetable') {
        getVegetablePrice(product)
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
                        $('#products').append(option);
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
                        $('#products').append(option);
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
                    $('#priceArea').append(res.data.price + " 원");

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
                    $('#priceArea').append(res.data.price + " 원");
                }
            });
        },
    });
}



let pageSize = 20;
$(function () {
    getGDP();

    $("#new-city").on('click', function () {

        loadNewCityModal();
    });

    $("#new-language").on('click', function () {
        loadNewLanguageModal();
    });

    $("#cities").on('click', '.delete-city', function () {
        const cityId = $(this).data("id");
        const result = confirm("Do you want to delete the city?");
        if (result) {
            $.ajax({
                method: "DELETE",
                url: "/api/cities/" + cityId,
                success: function () {
                    success("City deleted successfully");
                    getCities(1);
                },
                error: function (response) {
                    error("Error occurred while deleting city");
                }
            });
        }
    });

    $("#languages").on('click', '.delete-language', function () {
        const code = $(this).data("code");
        const lang = $(this).data("lang");
        console.log(lang)
        const result = confirm("Do you want to delete the language?");
        if (result) {
            $.ajax({
                method: "DELETE",
                url: "/api/languages/" + code + "/language/" + lang,
                success: function () {
                    success("Language deleted successfully");
                    getLanguages(1);
                },
                error: function (response) {
                    error("Error occurred while deleting language");
                }
            });
        }
    });
    getCities(1);
    getLanguages(1);
});

function loadNewCityModal() {
    loadModal("city-form-template", {});
    /*var html = Mustache.to_html($("#city-form-template").html(), {});
    $("#worldModal").html(html);*/
    setupForm('city-form', function () {
        success("City Added Successfully");
        getCities(1);
    });
}

function loadNewLanguageModal() {
    loadModal("language-form-template", {});
    /*var html = Mustache.to_html($("#language-form-template").html(), {});
    $("#worldModal").html(html);*/
    setupForm('language-form', function () {
        success("Language Added Successfully");
        getLanguages(1);
    });
    $("#isOfficial").on('change', function () {
        if ($(this).is(":checked")) $(this).val(1);
        if (!$(this).is(":checked")) $(this).val(0);
    });
}

function getCities(pageNo) {
    const params = {"pageNo": pageNo};
    $.getJSON("/api/cities/country/" + code, params, function (data) {
        let md = {};
        md['list'] = data;
        if (data.length === pageSize) {
            md['more'] = 1;
        } else {
            md['more'] = 0;
        }
        md['pageNo'] = pageNo + 1;
        let html = Mustache.to_html($("#cities-list-template").html(), md);
        if (pageNo === 1) {
            $("#cities").html(html);
        } else {
            $("#cities button").remove();
            $("#cities").append(html);
        }
    });
}

function getLanguages(pageNo) {
    const params = {"pageNo": pageNo};
    $.getJSON("/api/languages/" + code, params, function (data) {
        var md = {};
        md['list'] = data;
        if (data.length === pageSize) {
            md['more'] = 1;
        } else {
            md['more'] = 0;
        }
        md['pageNo'] = pageNo + 1;
        md['percentage_fmt'] = function () {
            if (this['percentage']) {
                return this['percentage'].toFixed(2);
            }
        }
        md['isOfficial_Bool'] = function () {
            return (this['isOfficial'] === "TRUE");
        }

        var html = Mustache.to_html($("#languages-list-template").html(), md);
        if (pageNo === 1) {
            $("#languages").html(html);
        } else {
            $("#languages button").remove();
            $("#languages").append(html);
        }
    });
}

google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);

function getGDP() {
    $.getJSON("/api/countries/gdp/" + code, function (data) {


        let dataTable = new google.visualization.DataTable();
        dataTable.addColumn('datetime', 'Years');
        dataTable.addColumn('number', 'GDP (current US$)');
        let rows = [];
        //console.log(numeral(646438380568.715).format('($0.00 a)'));
        _.each(data, function (item) {
            rows.push([new Date(String(item.year)), item.value])
        });
        dataTable.addRows(rows);


        const options = {
            hAxis: {
                title: 'Year'
            },
            vAxis: {
                title: 'GDP (current US$)'
            }
        };


        $("#gdp-chart").html('');
        google.charts.setOnLoadCallback(function () {
            let chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));
            chart.draw(dataTable, options);
        });

    });
}

function setupForm(formId, successCallback) {

    $("select").each(function () {
        $(this).val($(this).attr("value"));
    });

    $('#' + formId).validate({
        errorClass: "is-invalid",
        validClass: "is-valid"
    });
    $("#save-btn").on('click', function () {
        if ($('#' + formId).valid()) {
            var requestBody = $('#' + formId).serializeJSON();
            /*$.each($('#'+formId).serializeArray(), function(){
                var obj = $(this)[0];
                if ( obj.name.indexOf(".") >= 0){
                    var nestedObj = obj.name.split(".")[0];
                    var nestedObjProp = obj.name.split(".")[1];
                    if ( !requestBody[nestedObj]){
                        requestBody[nestedObj] = {};
                    }
                    requestBody[nestedObj][nestedObjProp] = obj.value;
                }else{
                    requestBody[obj.name] = obj.value;
                }

            });*/
            $.ajax({
                method: "POST",
                url: $('#' + formId).attr("action"),
                success: function (data) {
                    successCallback(data);
                },
                error: function (response) {
                    error(response.responseText);
                },
                data: JSON.stringify(requestBody),
                contentType: "application/json"
            });
        }
        return false;
    });

}
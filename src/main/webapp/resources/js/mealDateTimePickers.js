/**
 * Created by Родион on 23.09.2017.
 */
var dataTimePickersApi;

$(function () {

    $("#dateTime").datetimepicker({closeOnDateSelect: true, format: 'Y-m-d H:i'});
    $("#startDate").datetimepicker({closeOnDateSelect: true, datepicker: true, timepicker: false, format: 'Y-m-d'});
    $("#startTime").datetimepicker({closeOnDateSelect: true, datepicker: false, timepicker: true, format: 'H:i'});
    $("#endDate").datetimepicker({closeOnDateSelect: true, datepicker: true, timepicker: false, format: 'Y-m-d'});
    $("#endTime").datetimepicker({closeOnDateSelect: true, datepicker: false, timepicker: true, format: 'H:i'});
});

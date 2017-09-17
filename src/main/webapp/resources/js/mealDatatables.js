var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function edit(id) {
    log.writeln("id = " + id);
}

function getBetween() {
    // var filter = {startDate:"",endDate:"",startTime: "",endTime: "" };
    //
    // for (var name in filter) {
    //     filter[name] = $('#'+name).val();
    // };

    var startDate = $('#startDate').val();
    var startTime = $('#startTime').val();
    var endDate = $('#endDate').val();
    var endTime = $('#endTime').val();

    $.get(ajaxUrl + "/filter?startDate=" + startDate + "&startTime=" + startTime + "&endDate=" + endDate + "&endTime=" + endTime
        , function (data) {
            datatableApi.clear().rows.add(data).draw();
        });

}

$("#checkAll").click(function () {
    $("#total-users-table .checkItem").prop('checked', $(this).prop('checked'));
});

$("#checkAll1").click(function () {
    $("#blacklist-table .checkItem").prop('checked', $(this).prop('checked'));
});

$("#toBlacklist").click(function() {
    moveRows("#total-users-table", "#blacklist-table");
    updateCheckAll("#total-users-table", "#checkAll");
    updateCheckAll("#blacklist-table", "#checkAll1");
});

$("#toTotalUsers").click(function() {
    moveRows("#blacklist-table", "#total-users-table");
    updateCheckAll("#blacklist-table", "#checkAll1");
    updateCheckAll("#total-users-table", "#checkAll");
});

function moveRows(fromTable, toTable) {
    $(fromTable + " tbody input:checked").each(function() {
        var row = $(this).closest("tr").clone();
        $(this).closest("tr").remove();
        $(row).find("input").prop("checked", false);
        $(toTable + " tbody").append(row);
    });
}

// 추가된 코드
$("#total-users-table, #blacklist-table").on('change', '.checkItem', function() {
    updateCheckAll("#total-users-table", "#checkAll");
    updateCheckAll("#blacklist-table", "#checkAll1");
});

function updateCheckAll(tableSelector, checkAllSelector) {
    var allChecked = $(tableSelector + " .checkItem").length === $(tableSelector + " .checkItem:checked").length;
    $(checkAllSelector).prop('checked', allChecked);
}

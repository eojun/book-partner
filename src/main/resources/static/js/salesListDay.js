$(document).ready(function () {

    var $container  = $("#container");
    var _searchType = "${requestParam.searchType}";
    var _searchStatusType = "${requestParam.searchStatusType}";
    var _searchStartDate = "";
    var _searchEndDate = "";

    $container.find(".datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

    $('#searchStartDate').datepicker();
    $('#searchStartDate').datepicker("option", "maxDate", $("#searchEndDate").val());
    $('#searchStartDate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#searchEndDate").datepicker( "option", "minDate", selectedDate );
    });

    $('#searchEndDate').datepicker();
    $('#searchEndDate').datepicker("option", "maxDate", 0);
    $('#searchEndDate').datepicker("option", "minDate", $("#searchStartDate").val());
    $('#searchEndDate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#searchStartDate").datepicker( "option", "maxDate", selectedDate );
    });

    var now = new Date();
    var firstDate;

    firstDate = new Date(now.getFullYear(), now.getMonth(), 1);

    $("#searchStartDate").datepicker("setDate", firstDate);
    $('#searchEndDate').datepicker('setDate', 'today');

    $container.find("#searchType").val(_searchType);
    $container.find("#searchStatusType").val(_searchStatusType);
    $container.find("#searchStartDate").val(_searchStartDate);
    $container.find("#searchEndDate").val(_searchEndDate);


});

function chgDate(nowDate) {
    var myDate = new Date(nowDate);
}

$('li.dropdown').on("click", function (){
    $(this).toggleClass('open');
});

$("#searchByDay").on("click", function (){
    var startDate = $("#searchStartDate").val();
    var endDate = $("#searchEndDate").val();
    var period = 31; // 조회기간 31일
    var searchDateCheck = calcPeriodDay(startDate,endDate,period);
    if ( searchDateCheck == false ){
        alert("검색기간의 간격은 31일이 최대 입니다!! 검색기간을 확인해 주십시요");
        return false;
    }
    else{
        $('div.loading-wrap').show();
        var param = {searchStartDate:startDate,searchEndDate:endDate};
        $.post( "/sales/ajaxSalesListDay",param,function( data ) {
            $('#data_body').html(data);
            $('div.loading-wrap').hide();
            return true;
        });
    }
});

$("#searchByOrderList").on("click", function (){
    var startDate = $("#searchStartDate").val();
    var endDate = $("#searchEndDate").val();
    var period = 31; // 조회기간 31일
    var searchDateCheck = calcPeriodDay(startDate,endDate,period);
    if ( searchDateCheck == false ){
        alert("검색기간의 간격은 31일이 최대 입니다!! 검색기간을 확인해 주십시요");
        return false;
    }
    else{
        $('div.loading-wrap').show();
        var param = {searchStartDate:startDate,searchEndDate:endDate};
        $.post( "/sales/ajaxSalesListDayOrderList",param,function( data ) {
            $('#data_body').html(data);
            $('div.loading-wrap').hide();
            return true;
        });

    }
});


$("#searchCalc").on("click", function (){
    var startDate = $("#searchStartDate").val();
    var endDate = $("#searchEndDate").val();
    var period = 31; // 조회기간 31일

    var searchDateCheck = calcPeriodDay(startDate,endDate,period);
    if ( searchDateCheck == false ){
        alert("검색기간의 간격은 31일이 최대 입니다!! 검색기간을 확인해 주십시요");
        return false;
    }
    else{
        $('div.loading-wrap').show();
        var param = {searchStartDate:startDate,searchEndDate:endDate};
        $.post( "/sales/ajaxSalesListCalc",param,function( data ) {
            $('#data_body').html(data);
            $('div.loading-wrap').hide();
            return true;
        });
    }
});

$("#searchRank").on("click", function (){
    var startDate = $("#searchStartDate").val();
    var endDate = $("#searchEndDate").val();
    var period = 31; // 조회기간 31일
    var searchDateCheck = calcPeriodDay(startDate,endDate,period);
    if ( searchDateCheck == false ){
        alert("검색기간의 간격은 31일이 최대 입니다!! 검색기간을 확인해 주십시요");
        return false;
    }
    else{
        $('div.loading-wrap').show();
        var param = {searchStartDate:startDate,searchEndDate:endDate};
        $.post( "/sales/ajaxSalesRankList",param,function( data ) {
            $('#data_body').html(data);
            $('div.loading-wrap').hide();
            return true;
        });

    }
});
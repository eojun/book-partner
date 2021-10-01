/*! jQuery v3.1.1 | (c) jQuery Foundation | jquery.org/license */

//팝업창
function openWindow(Url, Title, Width, Height, Scroll) {
	var Left = Math.ceil( (window.screen.width  - Width) / 2 );
    var Top = Math.ceil( (window.screen.height - Height) / 2 );

	window.open(Url, Title, "scrollbars="+Scroll+",resizable=1,width="+Width+",height="+Height+",top="+Top+",left="+Left);
}

/*
	날짜계산. 기간 제한
	@param startdate : 시작일. 구분자 '-'로 된 (예: 2019-01-01)
	@param enddate : 종료일. 구분자 '-'로 된 (예: 2019-01-31)
	@param period : 제한 기간(day값)
	return Bool
*/
function calcPeriodDay(startdate,enddate,period){
	var return_flag = false;
	
	if ( $.trim(startdate) == "" || typeof startdate  == "undefined" ) startdate = null;
	if ( $.trim(enddate) == "" || typeof enddate  == "undefined" ) enddate = null;
	if ( $.trim(period) == "" || typeof period  == "undefined" ) period = null;
	if ( startdate == null || enddate == null || period == null ){
		return_flag =  false;
	}
	else{
		var stxt = startdate.split("-");
		var etxt = enddate.split("-");
	   	stxt[1] = stxt[1] - 1;
	   	etxt[1] = etxt[1] - 1;
	   	
	   	var sdate = new Date(stxt[0], stxt[1], stxt[2]);
	   	var edate = new Date(etxt[0], etxt[1], etxt[2]);
	   	
	   	var diff = edate - sdate;
	    var currDay = 24 * 60 * 60 * 1000;
	    if(parseInt(diff/currDay) > period){
	        //alert('조회 기간은 ' + period + '일을 초과할 수 없습니다.');
	    	return_flag =  false;
	    }
	    else{
	    	return_flag =  true;
	    }
	}
    return return_flag;
}

$.fn.extend({
	extractData:function(opt) {
		var data = {};
		$(this).find("input,textarea,select").each(function(i, item){
			 if(!!item['name']) {
				 if(item['type'] == 'checkbox'){
						if(!!item.checked){
							if(!!data[item.name]){
								data[item.name] += "," + item.value;	
							} else {
								data[item.name] = item.value;
							}
						} 
				 } else if(item['type'].indexOf("select") > -1){
						data[item.name] = item.value;						
					 var childs = item.children;
					 var selectedVal = "";
					 for(var sIndex = 0 ; sIndex < childs.length ; sIndex++){
						 if(!!childs[sIndex].selected) {
							 selectedVal += ((!!selectedVal ? "," : "") + childs[sIndex].value) 
						 }
					 }
					 data[item.name] = selectedVal;
				 } else {
					 data[item.name] = item.value || '';						 
				 }
			 }
		});
		
		if(!!opt){
			$.extend(data, opt);	
		}
		return data;
	}
});


/**
$("#XXXXXX").validate({
	messageType:"ALERT", //or "PRINT"
	submitHandler:function(form){
		alert("ok");
	}
});	


 */
(function($) {
$.extend($.fn, {
	validate : function(data){	
		
		var validator = new $.validator(this, data);
		
		/*
		this.submit( function( event ) {	//return 값이 false 이면 submit 되지 않는다.
			return validator.execSubmitValidate();			
		});
		*/
		
		if(validator.execSubmitValidate()){
			if(!!data.submitHandler && $.isFunction(data.submitHandler)){
				data.submitHandler(this);
			}
		}
		
	},
	bindValidate : function(data){
		var validator = new $.validator(this);		
		validator.execEventValidator();		
	}
});

$.validator = function(frm, opt){
	$.extend($.validator.option, {
		messageType:"ALERT", // "PRINT"
		submitHandler:function(form){ },
		messageHandler:null,
		message : {}
	}, opt);
	this.init(frm);
};

$.extend($.validator, {
	prototype:{
		option:null,
		init : function(frm){
			this.formObj = frm;
			// Message Box 초기화
			if(frm.find(".message-area").length > 0){ 
				frm.find(".message-area").remove();
			}
			
		},		//init		
		execSubmitValidate : function(){
			var formElements = $(this.formObj).find(":text,:password, :radio, :checkbox, :file, :hidden, select, textarea");	//textarea? select-one, select-multiple
			var result = true;
			formElements.each(function(){
				if(!$.validator.emptyCheck(this)){ result = false;return result;}
				if(!$.validator.passwordConfirmCheck(this)){ result = false;return result;}
				if(!$.validator.emailCheck(this)){ result = false; return result; }
				if(!$.validator.ssnCheck(this)){ result = false; return result; }
				if(!$.validator.checkboxCountCheck(this)){ result = false; return result; }
				if(!$.validator.radioCheck(this)){ result = false; return result; }
				if(!$.validator.selectOneCheck(this)){ result = false; return result; }
				if(!$.validator.selectMultipleCheck(this)){ result = false; return result; }
				if(!$.validator.minlengthCheck(this)){ result = false; return result; }
				if(!$.validator.maxlengthCheck(this)){ result = false; return result; }
				if(!$.validator.onlyNumber(this)){ result = false; return result; }
				if(!$.validator.onlyNumberInteger(this)){ result = false; return result; }

			});
			return result;
		},		//execSubmitValidate
		execEventValidator : function(){
			var formElements = $(this.formObj).find(":text,:password, :radio, :checkbox, :file, :hidden, select");	//textarea? select-one, select-multiple
			formElements.each(function(){
				var elementType = $.validator.getType(this);
				if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
					if($(this).attr("ONLY_NUMBER") == ""){					
						$(this).bind("change", function(){							
							$.validator.onlyNumber(this)
						});
					}
					
					if($(this).attr("ONLY_NUMBER_INTEGER") == ""){					
						$(this).bind("change", function(){							
							$.validator.onlyNumberInteger(this)
						});
					}

					if($(this).attr("ONLY_APLHABET") == ""){
						$(this).bind("change", function(){
							$.validator.onlyAlphabet(this)
						});						
					}

					if($(this).attr("ONLY_SMALLALPHABET") == ""){
						$(this).bind("change", function(){
							$.validator.onlySmallAlphabet(this)
						});
					}

					if($(this).attr("ONLY_CAPITALALPHABET") == ""){
						$(this).bind("change", function(){
							$.validator.onlyCapitalAlphabet(this)
						});
					}

					if($(this).attr("ONLY_NUMBERNALPHABET") == ""){
						$(this).bind("change", function(){
							$.validator.onlyNumberNAlphabet(this)
						});
					}
				}
			});		//form.each						
		}		//execEventValidator
	},		//prototype	
	option : { },
	message : { },
	emptyCheck : function (data){	
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "file" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("required") == "required"){
				var inputValue = "";
				var placeHolderValue = $(data).attr("placeholder");
				
				
					inputValue=$(data).val();

				if(inputValue == "" || inputValue == null || inputValue == placeHolderValue){
					
					this.requiredMessagingProcess(data);
					return false;
				}
			}
		}
		return true;
	},

	passwordConfirmCheck : function(data){
		var elementType = this.getType(data);
		var checkType = "passwordconfirm";
		if(elementType == "password" || elementType == "text"){					
			if($(data).attr(checkType)){
				var pairedId = $(data).attr(checkType);
				if($("#" + pairedId).val() != $(data).val()){
					this.messageProcess(data, checkType, "비밀번호 확인값이 상이합니다.");
					$(data).focus();
					return false;
				}
			}
		}
		return true;
	},
	
	emailCheck : function(data){
		var elementType = this.getType(data);
		var checkType = "EMAIL";
		
		if(elementType == "text" || elementType == "hidden"){
			if($(data).attr(checkType) == ""){
				if($(data).val() != ""){
					var result = 
					(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test($(data).attr("value")))
					
					if(!result){
						this.messageProcess(data, checkType, "올바른 이메일을 입력하세요.");
						if($(data).attr("type") != "hidden")
							$(data).focus();
						return false;
					}
				}
			}
		}
		return true;
	},
	ssnCheck : function(data){
		var elementType = this.getType(data);
		var checkType = "SSN";
		if(elementType == "text" || elementType == "hidden"){
			if($(data).attr(checkType) == ""){
				var val = $(data).attr("value").replace("-", "");
				
				if (/[^0-9-]+/.test(val))
					return false;

				var ssnCheck = 0;
				for (var i = 0; i < 12; i++) {
					ssnCheck += (i % 8 + 2) * val.charAt(i);
				}
				ssnCheck = (11 - ssnCheck % 11) % 10;

				if(ssnCheck != $(data).attr("value").charAt(12)) {				
					this.messageProcess(data, checkType, "주민번호가 올바르지 않습니다.");
					if($(data).attr("type") != "hidden")
						$(data).focus();
					return false;
				}
			}
		}
		return true;
	},

	checkboxCountCheck : function(data){
		var elementType = this.getType(data);
		
		
		if(elementType == "checkbox"){
			
			if($(data).get(0).getAttribute("required") == "required"){
				var checkboxId = $(data).attr("id");
				
				var REQUIREDCOUNT = 1;
				if($(data).attr("REQUIREDCOUNT"))
					REQUIREDCOUNT = $(data).attr("REQUIREDCOUNT")
				
				var checkedCount = $("input:checkbox[id="+checkboxId+"]:checked").length;
				
				if(checkedCount < REQUIREDCOUNT){
					this.checkedMessagingProcess(data, REQUIREDCOUNT);
					
					return false;
				}
			}
		}
		return true;
	},

	radioCheck : function(data){
		var elementType = this.getType(data);
		if(elementType == "radio"){
			if($(data).get(0).getAttribute("required") == "required"){
				var radioId = $(data).attr("name");
				
				var checkedCount = $("input:radio[name="+radioId+"]:checked").length;
				if(checkedCount < 1){
					this.checkedMessagingProcess(data, 1)					
					return false;
				}
			}
		}
		return true;
	},
	
	selectOneCheck : function(data){
		var elementType = this.getType(data);
		//console.log("elementType : " + elementType);
		if(elementType == "select-one"){
			if($(data).get(0).getAttribute("required") == "required"){
				var selectId = $(data).attr("id");
				var selectedCount = $("#" + selectId + " option:selected").length;
				var selectedValue = $("#" + selectId + " option:selected").val();
				//console.log("selectId : " + selectId + ",selectedCount : " + selectedCount + ",selectedValue : " + selectedValue);
				if(selectedCount <= 1 && selectedValue == ""){
					this.selectedMessagingProcess(data, 1);					
					return false;
				}
			}
		}
		
		return true;
		
	},

	selectMultipleCheck : function(data){
		var elementType = this.getType(data);
		if(elementType == "select-multiple"){
			if($(data).get(0).getAttribute("required") == "required"){
				var selectId = $(data).attr("id");

				var REQUIREDCOUNT = 1;
				if($(data).attr("REQUIREDCOUNT"))
					REQUIREDCOUNT = $(data).attr("REQUIREDCOUNT")

				var selectedCount = $("#" + selectId + " option:selected").length;
				var selectedValue = $("#" + selectId + " option:selected").val();
				if(selectedCount < REQUIREDCOUNT){
					this.selectedMessagingProcess(data, REQUIREDCOUNT)
					return false;
				}
			}
		}

		return true;
	},
	
	minlengthCheck : function(data){
		var elementType = this.getType(data);
		
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("minlengthCheck")){
				var minlength = $(data).attr("minlengthCheck");
				var actualLength = $(data).val().length;
				
				if(actualLength < minlength){
					this.lengthMessagingProcess(data, "min");			
					return false;
				}
			}
		}
		return true;
	},

	maxlengthCheck : function(data){
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("maxlengthCheck")){
				var maxlength = $(data).attr("maxlengthCheck");
				var actualLength = $(data).val().length;
				if(actualLength > maxlength){
					this.lengthMessagingProcess(data, "max");
					return false;
				}
			}
		}
		return true;
	},
	onlyNumber : function(data){		
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("ONLY_NUMBER") == ""){	
				var v_normal = /[^0-9]/g;				
				if(v_normal.test($(data).val())){
					this.inputStringCheckMessagingProcess(data, "숫자");
					return false;
				}
			}
		}
		return true;
		
	},
	onlyNumberInteger : function(data){		
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){

			if($(data).attr("ONLY_NUMBER_INTEGER") == ""){	
				var v_normal = /[^-?0-9]/g;			
				//console.log(v_normal.test($(data).val()));
				if(v_normal.test($(data).val())){
					this.inputStringCheckMessagingProcess(data, "정수");
					return false;
				}
			}
		}
		return true;
		
	},
	onlyNumberNAlphabet : function(data){
		var elementType = $.validator.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("ONLY_NUMBERNALPHABET") == ""){	
				var v_normal = /[^a-zA-Z0-9]/g;	
				if(v_normal.test($(data).attr("value"))){			
					this.inputStringCheckMessagingProcess(data, "영문자와 숫자");
					return false;
				}
			}
		}
		return true;
		
	},
	onlyCapitalAlphabet : function(data){
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("ONLY_CAPITALALPHABET") == ""){
				var v_normal = /[^A-Z]/g;	
				if(v_normal.test($(data).attr("value"))){			
					this.inputStringCheckMessagingProcess(data, "영문 대문자");
					return false;
				}	
			}
		}
		return true;
	},
	onlySmallAlphabet : function(data){
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("ONLY_SMALLALPHABET") == ""){
				var v_normal = /[^a-z]/g;	
				if(v_normal.test($(data).attr("value"))){			
					this.inputStringCheckMessagingProcess(data, "영문 소문자");
					return false;
				}	
			}
		}
		return true;
	},
	onlyAlphabet : function(data){	
		var elementType = this.getType(data);
		if(elementType == "text" || elementType == "password" || elementType == "hidden" || elementType == "textarea"){
			if($(data).attr("ONLY_APLHABET") == ""){
				var v_normal = /[^a-zA-Z]/g;	
				if(v_normal.test($(data).attr("value"))){			
					this.inputStringCheckMessagingProcess(data, "영문자");
					return false;
				}
			}
		}
		return true;
	
	},	
	getType : function(data){
		var elementType = $(data).prop("type");
		//console.log("id : " + $(data).attr("name") + ", elementType : " + elementType);
		return elementType;
	},
	messageProcess:function(target, type, msg){
		var message = msg;
		try{
			if(!!$.validator.option.message[target['id']]){		
				message = $.validator.option.message[target['id']][type];
			}
		}catch(e){ /* console.log(e); */ }
		if($.validator.option.messageType=="ALERT"){
			alert(message);
		} else {			
			var _mh = $.validator.option.messageHandler;
			if(!!_mh && $.isFunction(_mh)){
				_mh(message, target);
			} else {
				var $obj = $("[id=" + target['id'] +"]").length > 1 ? 
						$obj = $("[id=" + target['id'] +"]").last() : $("#" + target['id']);
					$obj.after("<span class='message-area'>" + message + "</span>");				
			}
		}
	},
	requiredMessagingProcess : function(data){
		var title = $(data).attr("title");
		var msg = $(data).attr("message");
		
		if(!!msg) {
			this.messageProcess(data, "REQUIRED", msg);
		} else {
			if(!title || title == "") {
				title = $(data).attr("id");
			}
			this.messageProcess(data, "REQUIRED", title + "을(를) 입력하세요.");			
		}
		if($(data).attr("type") != "hidden") {
			$(data).focus();
		}
	},
	selectedMessagingProcess : function(data){
		var title = $(data).attr("title");

		if(!title || title == "")
			title = $(data).attr("id");
		if(data.REQUIREDCOUNT > 1)
			this.messageProcess(data, "REQUIREDCOUNT", title + "은(는) " + $(data).attr("REQUIREDCOUNT") + "개 이상 선택하세요.");			
		else
			this.messageProcess(data, "SELECT", title + "을(를) 선택하세요.");
		$(data).focus();
	},

	checkedMessagingProcess : function(data){
		var title = $(data).attr("title");
		
		if(!title || title == ""){
			title = $(data).attr("id");
		}
		
		if(!!$(data).attr("REQUIREDCOUNT") ? parseInt($(data).attr("REQUIREDCOUNT")) : 0 > 1) {
			this.messageProcess(data, "REQUIREDCOUNT", title + "은(는) " + $(data).attr("REQUIREDCOUNT") + "개 이상 선택하세요.");
		} else {			
			this.messageProcess(data, "REQUIRED", title + "을(를) 선택하세요.");
		}
		$(data).focus();
	},

	lengthMessagingProcess : function(data, cmd){
		var title = $(data).attr("title");
		
		if(!title || title == "")
			title = $(data).attr("id");

		if(cmd == "min"){
			this.messageProcess(data, "MIN", title + "은(는) " + $(data).attr("minlengthCheck") + "자 이상으로 입력되어야 합니다.");
		}else if(cmd == "max"){
			this.messageProcess(data, "MAX", title + "은(는) " + $(data).attr("maxlengthCheck") + "자 이하로 입력되어야 합니다.");
		}

		if($(data).attr("type") != "hidden")
			$(data).focus();
	},

	inputStringCheckMessagingProcess : function(data , validateType){
		var title = $(data).attr("title");		
		var msg = $(data).attr("message");		
		
		if(!title || title == "")
			title = $(data).attr("id");
		
		if($(data).attr("type") != "hidden"){
			$(data).val("");			
			var txt = title + "에는 " + validateType + "만 입력가능 합니다.";
			if(!!msg){
				txt = msg;
			} 
			this.messageProcess(data, "STRING_CHECK", txt);
			$(data).focus();
		}
	}
});



})(jQuery);
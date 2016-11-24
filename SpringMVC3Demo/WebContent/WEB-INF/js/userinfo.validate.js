var userInfoValidate = function() {

	var userInfoValid = function() {
		$('.userinfo-form').validate({
			errorElement : 'span', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "",
			rules : {

				email : {
					required : true,
					email : true
				},
				userName : {
					required : true
				},
				password : {
					required : true,
					minlength : 8
				},
				confirmPassword : {
					equalTo : "#password"
				}

			},

			messages : { // custom messages for radio buttons and checkboxes
				email : {
					required : "请输入邮箱。",
					email : "请输入正确的邮箱地址。",
				},
				userName : {
					required : "请输入用户名。"
				},
				password : {
					required : "请输入密码。",
					minlength : jQuery.validator.format("密码长度不能小于{0}位。"),
				},
				confirmPassword : {
					required : "请再次输入密码。",
					equalTo : "两次输入不一致,请重新输入。",
				}
			},

			invalidHandler : function(event, validator) { // display error
															// alert on form
															// submit

			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
																			// error
																			// class
																			// to
																			// the
																			// control
																			// group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},

			errorPlacement : function(error, element) {
				if (element.attr("name") == "agreement") { // insert checkbox
															// errors after the
															// container
					error.insertAfter($('#register_agreement_error'));
				} else if (element.closest('.input-icon').size() === 1) {
					error.insertAfter(element.closest('.input-icon'));
				} else {
					error.insertAfter(element);
				}
			},

			submitHandler : function(form) {
				form.submit();
			}
		});

		$('.userinfo-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.userinfo-form').validate().form()) {
					$('.userinfo-form').submit();
				}
				return false;
			}
		});

		/*
		 * jQuery('#register-btn').click(function () {
		 * jQuery('.login-form').hide(); jQuery('.userinfo-form').show(); });
		 * 
		 * jQuery('#register-back-btn').click(function () {
		 * jQuery('.login-form').show(); jQuery('.userinfo-form').hide(); });
		 */
	}

	return {
		// main function to initiate the module
		userInfoValid : function() {
			userInfoValid();
		}
	};

}();

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.infrastructure.project.common.extension.UrlHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8" />
<title>用户信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">

<%@ include file="../shared/importCss.jsp"%>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet"
	href="<c:url value='/plugins/data-tables/DT_bootstrap.css'/>"
	type="text/css" />
<!-- END PAGE LEVEL STYLES -->

<%@ include file="../shared/importJs.jsp"%>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript"
	src="<c:url value='/plugins/data-tables/jquery.dataTables.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/plugins/data-tables/DT_bootstrap.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/plugins/uniform/jquery.uniform.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery.toolbarlite.js?ver=10'/>"></script>
<script type="text/javascript" src="<c:url value='/js/app.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery.tableManaged.js'/>"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="<c:url value='/plugins/jquery-validation/dist/jquery.validate.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/userinfo.validate.js'/>" type="text/javascript"></script>  

<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">

	<%@ include file="../shared/pageHeader.jsp"%>

	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">

		<%@ include file="../shared/sidebarMenu.jsp"%>

		<!-- BEGIN PAGE -->
		<div class="page-content">

			<%@ include file="../shared/styleSet.jsp"%>

			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						User Infomation <small>form controls and more</small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li><i class="icon-home"></i> <a
							href="<c:url value='/home/index'/>">首页</a> <i
							class="icon-angle-right"></i></li>
						<li><span>${requestScope.permissionMenu.rootName}</span> <i
							class="icon-angle-right"></i></li>
						<li><span>${requestScope.permissionMenu.subName}</span></li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet ">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-edit"></i>${requestScope.permissionMenu.curName}</div>
						</div>
						<div class="portlet-body form">
							<form:form modelAttribute="userInfoModel" class="userinfo-form" method="POST">
								<div class="form-body">
									<table
										class="table table-striped table-bordered table-hover table-condensed">
										<tbody>
											<tr>
												<td>ID</td>
												<td colspan="2"><form:input path="id" class="form-control" type="text"
													readonly="true" value="${userInfoModel.id}" /></td>
											</tr>
											<tr>
												<td>ACC</td>
												<td><form:input class="form-control" type="text" path="acc"
													readonly="true" value="${userInfoModel.acc}" /></td>
												<td rowspan="3">
													<p>暂无照片</p> <!--img alt="" style="height:120px;width:300px" class="img-responsive" src="<c:url value='/images/gallery/image1.jpg' />"-->
												</td>
											</tr>
											<tr>
												<td>USERNAME</td>
												<td><form:input path="userName" class="form-control"
														type="text" value="${userInfoModel.userName}" />
													<form:errors path="userName"
														class="field-has-error"></form:errors>
												</td>
											</tr>
											<tr>
												<td>PASSWORD</td>
												<td>
													<form:password path="password" class="form-control"
														value="1234567890" />
													<form:errors path="password"
														class="field-has-error"></form:errors>
													<div style="height: 2px"></div> 
													
													<form:password
														path="confirmPassword" class="form-control"
														value="1234567890" /> 
													<form:errors path="confirmPassword"
														class="field-has-error"></form:errors>
												</td>
											</tr>
											<tr>
												<td>EMAIL</td>
												<td colspan="2"><form:input path="email"
														class="form-control form-inline" type="text"
														value="${userInfoModel.email}" />
														<form:errors path="email"
														class="field-has-error"></form:errors>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-6 col-md-6">
										<button type="submit" class="btn btn-success">保存</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<%@ include file="../shared/pageFooter.jsp"%>

	<script type="text/javascript">
		$(function() {
			App.init();
			userInfoValidate.userInfoValid();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
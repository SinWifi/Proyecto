<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JET PERU</title>
	<%@ include file="/WEB-INF/views/template/head.jsp"%>
</head>
<body>
	<div class="site_wrapper">

		<%@ include file="/WEB-INF/views/template/menu.jsp"%>

		<div class="clearfix"></div>

		<div class="page_title">

			<div class="container">
				<div class="title">
					<h1>Cobrar Remesa</h1>
				</div>
				<div class="pagenation">
					<a href="index.html">Home</a> <i>/</i> Cobrar Remesa
				</div>
			</div>
		</div>
		<!-- end page title -->





		<!-- Contant
======================================= -->

		<div class="container">



			<div class="content_fullwidth">

				<div class="one_full">


					<!-- 					<div id="errorHora" style="display: none;" class="notice"> -->
					<!-- 						<div class="message-box-wrap">La informaci&oacute;n mostrada -->
					<!-- 							no puede ser actualizada</div> -->
					<!-- 					</div> -->

					<!-- 					<div id="errorNumeroInvalido" style="display: none;" class="error"> -->
					<!-- 						<div class="message-box-wrap">El n&uacute;mero de -->
					<!-- 							transacci&oacute;n no es v&aacute;lido.</div> -->

					<!-- 					</div> -->

				</div>
				<!-- end styled boxes -->

				<div class="one_half">

					<br />
					<h3>Cobrar Remesa</h3>

					<form action="" method="post">

						<fieldset>
						
							<table class="table-style">
								<tr>
									<td>
										<label for="name" class="blocklabel">Tipo de documento: </label>
										<p class="">
											<select name="tipoDoc" class="input_bg" id="tipoDoc">
												<option value="dni">DNI</option>
												<option value="ruc">RUC</option>
											</select>
										</p>
									</td>
									<td>
										<p>
											<input name="validarPINUsuario" type="button" value="Buscar"
											class="comment_submit" id="validarPINUsuario" />
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<label for="name" class="blocklabel">Número de documento: </label>
										<p class="">
											<input name="numDoc" class="input_bg" type="text"
											id="numDoc"/>
										</p>
									</td>
									<td></td>
								</tr>
								<tr>
									<td>
										<label for="name" class="blocklabel">PIN: </label>
										<p class="">
											<input name="PIN" class="input_bg" type="text"
											id="PIN" value='' />
										</p>
									</td>
									<td></td>
								</tr>
								<tr id="verMontoActual" style="display: none;">
									<td>
										<label for="name" class="blocklabel">Monto: </label>
										<p class="">
											<input name="monto" class="input_bg" type="text" readonly="readonly"
											id="monto" />
										</p>
									</td>
									<td></td>
								</tr>
							</table>
							
							<div class="clearfix"></div>

							<br>
							<p id="mostrarBotonGenerarCobro" style="display: none;">
								<input name="generarCobro" type="button" value="Generar Cobro"
									class="comment_submit" id="generarCobro" />
							</p>

						</fieldset>

					</form>

				</div>
			</div>

		</div>

	</div>

	<%@ include file="/WEB-INF/views/template/footer.jsp"%>
</body>
</html>
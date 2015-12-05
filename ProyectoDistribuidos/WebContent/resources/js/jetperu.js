$(document).ready(function($) {
	
	var urlHome = $('#urlHome').val();

	$('#btnMostarTabla').click(function(e){
		e.preventDefault();
		
		numero = $('#numero').val();
		
		$('#tablaBusqueda').hide();
		$('#errorHora').hide();
		$('#errorNumeroInvalido').hide();
		$('#tBodyBusqueda').html('');
		
		if(numero != ""){
			$.ajax({
				type: 'POST',
				url: urlHome + "remesa/consultar-remesa",
				dataType: 'json',
				data: {"numeroTransaccion" : numero},
				success: function(result){
					if(result.resultado == "0"){
						$('#tablaBusqueda').show();
						 html = '';
					     html += '<tr>';
					     html += 	'<td>'+result.remesa.Numero+'</td>';
					     html += 	'<td>'+result.remesa.PIN+'</td>';
					     html += 	'<td>'+result.remesa.Fecha+'</td>';
					     html += 	'<td>'+result.remesa.NombreAgencia+'</td>';
					     html += 	'<td>'+result.remesa.NombreMoneda+'</td>';
					     html += 	'<td>'+result.remesa.Monto+'</td>';
					     html += 	'<td>'+result.remesa.Estado+'</td>';
					     html += '</tr>';
					     
					     $('#tBodyBusqueda').html(html);
					}else if(result.resultado == "1"){
						$('#errorHora').show();
						$('#errorHora .message-box-wrap').html(result.mensaje);
					}else if(result.resultado == "2"){
						$('#errorNumeroInvalido').show();
					}
				}
			});
		}else{
			$('#errorNumeroInvalido').show();
		}
		
	})
	
	$("#validarPINUsuario").on('click',function(){
		
		tipoDoc = $("#tipoDoc").val();
		numDoc = $("#numDoc").val();
		PIN = $("#PIN").val();
		
		if(tipoDoc != "" && numDoc != "" && PIN != ""){
			$.ajax({
				type: 'POST',
				url: urlHome + "cobrarRemesa/validarPIN",
				dataType: 'json',
				data: {"tipoDoc" : tipoDoc,"numDoc" : numDoc,"PIN" : PIN},
				success: function(result){
					if(result.resultado == "0"){
						alert("Los datos ingresados no se encuentran en nuestro registro.");
					}else if(result.resultado == "1"){
						$("#monto").val(result.remesa.Monto);
						$("#verMontoActual").show();
						$("#mostrarBotonGenerarCobro").show();
					}else if(result.resultado == "2"){
						alert(result.mensaje);
					}else if(result.resultado == "3"){
						alert(result.mensaje);
					}
				}
			});
		}else{
			alert('Algunos de los datos ingresados son incorrectos. Verifica nuevamente');
		}
	});

	$("#generarCobro").on('click',function(){
		tipoDoc = $("#tipoDoc").val();
		numDoc = $("#numDoc").val();
		PIN = $("#PIN").val();
		
		if(tipoDoc != "" && numDoc != "" && PIN != ""){
			$.ajax({
				type: 'POST',
				url: urlHome + "cobrarRemesa/validarPIN",
				dataType: 'json',
				data: {"tipoDoc" : tipoDoc,"numDoc" : numDoc,"PIN" : PIN},
				success: function(result){
					if(result.resultado == "0"){
						alert("Los datos ingresados no se encuentran en nuestro registro.");
					}else if(result.resultado == "1"){
						$("#monto").val(result.remesa.Monto);
						$("#verMontoActual").show();
						alert('Se ha realizado el cobro de remesa de manera correcta.');
					}else if(result.resultado == "2"){
						alert(result.mensaje);
					}else if(result.resultado == "3"){
						alert(result.mensaje);
					}
				}
			});
		}else{
			alert('Algunos de los datos ingresados son incorrectos. Verifica nuevamente');
		}
	});
	
});





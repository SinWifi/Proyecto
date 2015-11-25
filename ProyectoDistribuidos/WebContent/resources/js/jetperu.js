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

});





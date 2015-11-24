package com.jetperu.remesa.controller;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datacontract.schemas._2004._07.HoraExcedidaException;
import org.datacontract.schemas._2004._07.Remesa;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri.IRemesasProxy;

import com.google.gson.Gson;
import com.jetperu.remesa.bean.RemesaBean;

@Controller
@RequestMapping("/remesa")
public class RemesaController {

	@RequestMapping(value = { "", "/" })
	public String defaultView() {

		return "consultar-remesa";
	}

	@RequestMapping(value = "/consultar-remesa", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> consultarEstadoRemesa(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("numeroTransaccion") int numeroTransaccion) throws RemoteException {

		IRemesasProxy iRemesaProxy = new IRemesasProxy();
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		int resultado = 0;
		RemesaBean remesaBean = null;
		String mensaje = "";
		
		try {
			Remesa remesa = iRemesaProxy.obtenerRemesa(numeroTransaccion);
			if (remesa != null) {
				remesaBean = new RemesaBean();
				remesaBean.setNumero(remesa.getNumero());
				remesaBean.setPIN(remesa.getPIN());
				remesaBean.setNombreAgencia(remesa.getAgencia().getNombre());
				remesaBean.setNombreMoneda(remesa.getMoneda().getNombre());
				remesaBean.setMonto(remesa.getMonto().doubleValue());
				remesaBean.setEstado(remesa.getEstado());
				remesaBean.setFecha(remesa.getFecha());
			} else {
				resultado = 2;
			}
		} catch (HoraExcedidaException e) {
			resultado = 1;
			mensaje = e.getDetalleError();
		}
		
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		
		mapResultado.put("remesa", remesaBean);
		mapResultado.put("resultado", resultado);
		mapResultado.put("mensaje", mensaje);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(new Gson().toJson(mapResultado), httpHeaders, HttpStatus.CREATED);
	}

}

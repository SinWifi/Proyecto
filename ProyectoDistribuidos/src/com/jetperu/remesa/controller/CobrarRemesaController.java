package com.jetperu.remesa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datacontract.schemas._2004._07.FechaLimiteException;
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
@RequestMapping("/cobrarRemesa")
public class CobrarRemesaController {

	@RequestMapping(value = { "", "/" })
	public String defaultView() {
		return "cobrar-remesa";
	}

	// Nuevo cambio para registrar cobro de remesa
	@RequestMapping(value = "/validarPIN", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> validarPIN(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("tipoDoc") String tipoDoc,
			@RequestParam("numDoc") String numDoc,
			@RequestParam("PIN") int PIN) throws Exception{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		int resultado = 0 ;
		String mensaje = "";
		
		IRemesasProxy iRemesaProxy = new IRemesasProxy();
		RemesaBean remesaBean = null;
		try {
			Remesa remesa = iRemesaProxy.obtenerRemesaByUsuario(tipoDoc, numDoc, PIN);
			if (remesa != null) {
				remesaBean = new RemesaBean();
				remesaBean.setMonto(remesa.getMonto().doubleValue());
				resultado = 1;
			}
		} catch (HoraExcedidaException e) {
			resultado = 2;
			mensaje = e.getDetalleError();
		} catch (FechaLimiteException e) {
			resultado = 3;
			mensaje = e.getDetalleError();
		}
		
		mapResultado.put("resultado", resultado);
		mapResultado.put("remesa", remesaBean);
		mapResultado.put("mensaje", mensaje);
		
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(new Gson().toJson(mapResultado), httpHeaders, HttpStatus.CREATED);
	}
	
	// Nuevo cambio para registrar cobro de remesa
	@RequestMapping(value = "/generarCobro", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> generarCobro(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("tipoDoc") String tipoDoc,
			@RequestParam("numDoc") String numDoc,
			@RequestParam("PIN") int PIN) throws Exception{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		int resultado = 0 ;
		String mensaje = "";

		IRemesasProxy iRemesaProxy = new IRemesasProxy();
		RemesaBean remesaBean = null;
		try {
			Remesa remesa = iRemesaProxy.generarCobro(tipoDoc, numDoc, PIN);
			if (remesa != null) {
				remesaBean = new RemesaBean();
				remesaBean.setMonto(remesa.getMonto().doubleValue());
				resultado = 1;
			}
		} catch (HoraExcedidaException e) {
			resultado = 2;
			mensaje = e.getDetalleError();
		} catch (FechaLimiteException e) {
			resultado = 3;
			mensaje = e.getDetalleError();
		}

		mapResultado.put("resultado", resultado);
		mapResultado.put("remesa", remesaBean);
		mapResultado.put("mensaje", mensaje);

		response.setContentType(MediaType.APPLICATION_JSON.toString());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<String>(new Gson().toJson(mapResultado), httpHeaders, HttpStatus.CREATED);
	}
}

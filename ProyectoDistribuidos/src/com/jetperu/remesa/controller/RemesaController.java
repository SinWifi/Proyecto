package com.jetperu.remesa.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jetperu.remesa.bean.RemesaRestBean;

@Controller
@RequestMapping("/remesa")
public class RemesaController {
	

	@RequestMapping(value = { "", "/" })
	public String defaultView() {

		return "consultar-remesa";
	}
/*
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
	}*/
	/*
	@RequestMapping(value = "/consultar-remesa", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> consultarEstadoRemesa(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("numeroTransaccion") int numeroTransaccion) throws RemoteException {

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<RemesaRestBean> responseRest = restTemplate.
				getForEntity(URL_REST_REMESA + "{numero}",RemesaRestBean.class,1);
		
		System.out.println(responseRest.getStatusCode());
		System.out.println(responseRest.getHeaders());
		System.out.println(responseRest.getBody());
		
		
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(new Gson().toJson(null), httpHeaders, HttpStatus.CREATED);
	}*/

	@RequestMapping(value = "/consultar-remesa", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> consultarEstadoRemesa(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("numeroTransaccion") int numeroTransaccion) throws Exception {
		
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		int resultado = 0;
		HttpURLConnection conn = null;
		String mensaje = "";
		RemesaRestBean remesaRestBean = null;
		try {
			URL url = new URL(String.format("http://localhost:61010/Remesas.svc/Remesas/%d", numeroTransaccion));
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() == 500) {
				String contenido = IOUtils.toString(conn.getErrorStream());
				mensaje = contenido;
				if (mensaje.endsWith("no existe\"")) {
					resultado = 2;
				} else if(mensaje.endsWith("estar actualizada\"")) {
					resultado = 1;
				}
			} else {
				String contenido = IOUtils.toString(conn.getInputStream());
				remesaRestBean =  new Gson().fromJson(contenido, RemesaRestBean.class);
				remesaRestBean.setNombreAgencia(remesaRestBean.getAgencia().getNombre());
				remesaRestBean.setNombreMoneda(remesaRestBean.getMoneda().getNombre());
			}
		} catch (Exception e) {
			throw new Exception("No se pudo conectar a la URL");
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		mapResultado.put("remesa", remesaRestBean);
		mapResultado.put("resultado", resultado);
		mapResultado.put("mensaje", mensaje);
		
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(new Gson().toJson(mapResultado), httpHeaders, HttpStatus.CREATED);
	}
}

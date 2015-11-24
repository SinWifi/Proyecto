package com.example.androiddsd;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddsd.entidades.Remesa;
import com.example.prueba.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends Activity implements OnClickListener {

	private EditText eteNumero;
	private TextView tviNumero, tviFecha, tviAgencia, tviMoneda, tviMonto, tviEstado;
	private Remesa mRemesa;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        cargarViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn:
				RemesaRest rest = new RemesaRest();				
				rest.execute(eteNumero.getText().toString());
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			default:
				break;
		}
	}
	
	private void cargarViews(){
		eteNumero = (EditText)findViewById(R.id.eteNumero);
		tviNumero = (TextView)findViewById(R.id.tviNumero);
		tviFecha = (TextView)findViewById(R.id.tviFecha);
		tviAgencia = (TextView)findViewById(R.id.tviAgencia);
		tviMoneda = (TextView)findViewById(R.id.tviMoneda);
		tviMonto = (TextView)findViewById(R.id.tviMonto);
		tviEstado = (TextView)findViewById(R.id.tviEstado);
				
		Button btn = (Button)findViewById(R.id.btn);        
        btn.setOnClickListener(this);
	}
	
	private void mostrarData(){
		if(mRemesa!=null){
			tviNumero.setText(String.valueOf(mRemesa.getNumero()));
			tviFecha.setText(mRemesa.getFecha());
			tviAgencia.setText(mRemesa.getAgencia().getNombre());
			tviMoneda.setText(mRemesa.getMoneda().getSimbolo());
			tviMonto.setText(String.valueOf(mRemesa.getMonto()));
			tviEstado.setText(mRemesa.getEstado());
		}			
	}
	
	private void limpiarViews(){		
		tviNumero.setText("");
		tviFecha.setText("");
		tviAgencia.setText("");
		tviMoneda.setText("");
		tviMonto.setText("");
		tviEstado.setText("");
	}
	
	
	private class RemesaRest extends AsyncTask<String, Integer, Boolean> {		
		private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
		private String error="";
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.setTitle("Please wait...");
			progressDialog.show();			
		}	
		
		@Override
		protected Boolean doInBackground(String... params) {
			
			boolean resul = true;	    	
	    	HttpClient httpClient = new DefaultHttpClient();	        
			String numero = params[0];
			HttpGet request = new HttpGet("http://192.168.0.51/RESTServices/Remesas.svc/Remesas/"+numero);			
			request.setHeader("content-type", "application/json");
			
			try
	        {			
	        	HttpResponse response = httpClient.execute(request);
	        	String respStr = EntityUtils.toString(response.getEntity());
	        	
	        	if(response.getStatusLine().getStatusCode()==500){
	        		error=respStr;
	        	}else{
	        		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	        		JSONObject respJSON = new JSONObject(respStr);
	        		mRemesa = gson.fromJson(respJSON.toString(), Remesa.class);	        		
	        	}
	        }
	        catch(Exception ex)
	        {
	        	Log.e("ServicioRest","Error!", ex);
	        	resul = false;
	        }
	 
	        return resul;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			if (result){
				if(error.equals(""))
					mostrarData();
				else{	
					limpiarViews();
					Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
				}
			}else{
				limpiarViews();
				Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
		}
	}
}

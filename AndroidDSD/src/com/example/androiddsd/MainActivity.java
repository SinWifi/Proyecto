package com.example.androiddsd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.prueba.R;


public class MainActivity extends Activity implements OnClickListener {

	private Button btnBusqueda, btnConsulta, btnObservacion;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        cargarViews();
    }    

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnBusqueda:
				Intent intent1 = new Intent(this, BusquedaRemesaActivity.class);
				startActivity(intent1);
				break;
			case R.id.btnConsulta:
//				Intent intent2 = new Intent(this, BusquedaRemesaActivity.class);
//				startActivity(intent2);
				break;
			case R.id.btnObservacion:
//				Intent intent3 = new Intent(this, BusquedaRemesaActivity.class);
//				startActivity(intent3);
				break;
			default:
				break;
		}
	}
	
	private void cargarViews(){
		btnBusqueda = (Button)findViewById(R.id.btnBusqueda);
		btnConsulta= (Button)findViewById(R.id.btnConsulta);
		btnObservacion = (Button)findViewById(R.id.btnObservacion);
        
		btnBusqueda.setOnClickListener(this);
		btnConsulta.setOnClickListener(this);
		btnObservacion.setOnClickListener(this);
	}	
}

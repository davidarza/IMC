package com.example.indicedemasacorporal;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String LOGTAG="MainActivity";
	
	private IndiceMasaCorporal imc;
	EditText editPeso;
	EditText editAltura;
	TextView textViewResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Establecer identificadores
		editPeso = (EditText) findViewById(R.id.editPeso);
		editAltura = (EditText) findViewById(R.id.editAltura);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);

	}

	/**
	 * onButtonClickCalcularIMC
	 */
	public void onButtonClickCalcularIMC(View v) {

		String peso=null;
		String altura = null;
		String resultado;
		try {

			peso = editPeso.getText().toString();
			altura = editAltura.getText().toString();

			imc = new IndiceMasaCorporal(peso, altura);
			
			resultado = "Valor IMC = " + imc.valorIndiceMasaCorporal() + " - ";
			resultado = resultado.concat(imc.clasificacionOMS());
			textViewResultado.setText(resultado);
			Log.e(LOGTAG, imc.toString());
			editPeso.setBackgroundColor(Color.TRANSPARENT);
			editAltura.setBackgroundColor(Color.TRANSPARENT);
			
			
			
			
		} catch (IndiceMasaCorporalException e) {
			
			if(peso.isEmpty() && altura.isEmpty()){
				String texto="Faltan ambos datos por introducir";
				Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
				editPeso.setBackgroundColor(Color.LTGRAY);			
				editAltura.setBackgroundColor(Color.LTGRAY);
			}
			
			else if(peso.isEmpty()){
				String texto="Falta el peso por introducir";
				Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
				editPeso.setBackgroundColor(Color.LTGRAY);	
			}
			
			else if(altura.isEmpty()){
				String texto="Falta la altura por introducir";
				Toast.makeText(getApplicationContext(),texto, Toast.LENGTH_SHORT).show();
				editAltura.setBackgroundColor(Color.LTGRAY);	
			}
			
			else if (e.isErrorAltura() && e.isErrorPeso()){
				editAltura.setBackgroundColor(Color.LTGRAY);
				editPeso.setBackgroundColor(Color.LTGRAY);
			String texto = "Altura y peso mal introducida";
			Toast.makeText(getApplicationContext(), texto,Toast.LENGTH_SHORT).show();
			}	
			
			else if (e.isErrorPeso()) {
				editPeso.setBackgroundColor(Color.LTGRAY);
				String texto="Peso mal introducido";
				Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
			}
			
			
			else if (e.isErrorAltura()){
				editAltura.setBackgroundColor(Color.LTGRAY);
			String texto = "Altura mal introducida";
			Toast.makeText(getApplicationContext(), texto,Toast.LENGTH_SHORT).show();
		}	
			
			
		
	
		}	catch (Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
		
	}
	
	public void onClickButtonAcercaDe(View v) {
		try {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
		} catch(Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

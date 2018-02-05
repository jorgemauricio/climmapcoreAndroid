package com.example.carol.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class traedatos extends AppCompatActivity {
    Button siguiente;
    private TextView newtext;
    String date;// variable de fecha que nos ayudara a descargar los otros archivos
    public int año, mes, dia;
    String fec[] = new String[6];
    Fechas fecha = new Fechas();
    DescargaArchivosTXT d = new DescargaArchivosTXT();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traedatos);
        newtext = (TextView) findViewById(R.id.FechaView); //se busca el text view con su id para despues añadirle texto
        test();
        controlador();
    }

    public void controlador() {
        siguiente = findViewById(R.id.button);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListSong = new Intent(getApplicationContext(),txtleerarchivo.class);
                startActivity(ListSong);
            }
        });
    }

    private void test() {
        try {
            JSONObject jsonRequest = new JSONObject();

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, "http://pdiarios.alcohomeapp.com.mx/diaJSON.php", jsonRequest, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonResponse) {

                            //INICIA CÓDIGO DE CONEXIÓN AL SERVIDOR

                            try {
                                String dae = jsonResponse.getString("dia");
                                date = dae;
                                newtext.setText(date);//aparece la fecha del dia de hoy
                                año = Integer.parseInt(date.substring(0, 4)); //convertidor de año a entero
                                mes = Integer.parseInt(date.substring(5, 7));//convertidor de mes a entero
                                dia = Integer.parseInt(date.substring(8));//convertidor de dia a entero
                                fecha.creadordeFechas(año,mes,dia,fec);//llama la funcion para crear las fechas



                                d.descargar("http://pdiarios.alcohomeapp.com.mx/Docs/"+date+"/d1.txt",1);
                                d.descargar("http://pdiarios.alcohomeapp.com.mx/Docs/"+date+"/d1.txt",2);
                                d.descargar("http://pdiarios.alcohomeapp.com.mx/Docs/"+date+"/d1.txt",3);
                                d.descargar("http://pdiarios.alcohomeapp.com.mx/Docs/"+date+"/d1.txt",4);
                                d.descargar("http://pdiarios.alcohomeapp.com.mx/Docs/"+date+"/d1.txt",5);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //FINALIZA CÓDIGO DE CONEXIÓN AL SERVIDOR
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error de de conexión", Toast.LENGTH_LONG).show();
                        }
                    });
            SingletonConnection.getInstance(this).addToRequestQueue(jsObjRequest);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error de inesperado", Toast.LENGTH_LONG).show();
        }
    }
}

package mx.edu.isc.tesoem.hjcg.p2p6_7s21;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import DatosExamen.EstructuraDatos;

public class Cronometro extends Service {

    private long tiempoTotalMilisegundos = 0; // Tiempo total en milisegundos

    private Context ctx;

    Timer temporizador = new Timer();

    private static final long INTERVALO_ACTUALIZACION = 100;

    TextView textoactualiza, txtatras;
    Button btnCalificar;
    int currentIndex = 0;
    ArrayList<EstructuraDatos> listadatos = new ArrayList<>();

    public Cronometro() {
    }

    public Cronometro(Context c) {
        super();
        this.ctx = c;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setBtnCalificar(Button btnCalificar) {
        this.btnCalificar = btnCalificar;
    }

    private void IniciarCronometro() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoTotalMilisegundos += INTERVALO_ACTUALIZACION;

                long minutos = tiempoTotalMilisegundos / 60000; // 1 minuto = 60000 ms
                long segundos = (tiempoTotalMilisegundos / 1000) % 60;

                // Formatear el tiempo en minutos y segundos
                String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

                // Actualizar la vista de tiempo
                if (textoactualiza != null) {
                    textoactualiza.setText(tiempoFormateado);
                }
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }

    public void seView(TextView txv) {
        textoactualiza = txv;
        IniciarCronometro();
    }

    public void seView1(TextView txv) {
        txtatras = txv;
        ConteoAtras();
    }

    @Override
    public void onDestroy() {
        if (temporizador != null) {
            temporizador.cancel();
        }
        super.onDestroy();
    }

    private void ConteoAtras() {
        new CountDownTimer(1200000, 1000) {

            @Override
            public void onTick(long l) {
                long minutos = l / 60000; // 1 minuto = 60000 ms
                long segundos = (l / 1000) % 60;

                // Formatear el tiempo en minutos y segundos
                String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

                // Actualizar la vista de tiempo atras
                if (txtatras != null) {
                    txtatras.setText(tiempoFormateado);
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(ctx, "Se acabo el tiempo del Examen", Toast.LENGTH_SHORT).show();

                // Habilitar el botón cuando se complete el cronómetro
                if (btnCalificar != null) {
                    btnCalificar.setEnabled(true);//Habilita el boton
                    btnCalificar.callOnClick();//Lo ejecuta
                }
            }
        }.start();
    }
}

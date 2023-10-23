package mx.edu.isc.tesoem.hjcg.p2p6_7s21;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import DatosExamen.EstructuraDatos;

public class PrincipalActivity extends AppCompatActivity {

    Button btncalificar;
    ArrayList<EstructuraDatos> listadatos;
    SegundoFragment segundoFragment;
    Cronometro cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btncalificar = findViewById(R.id.btncalif);
        btncalificar.setEnabled(false);

        PrimeroFragment primeroFragment = new PrimeroFragment();
        segundoFragment = new SegundoFragment();
        listadatos = primeroFragment.listadatos;

        btncalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstructuraDatos preguntaActual = listadatos.get(primeroFragment.currentIndex);

                String respuestaSeleccionada = primeroFragment.obtenerRespuesta();
                if (respuestaSeleccionada != null) {
                    preguntaActual.setRespuestaSeleccionada(respuestaSeleccionada);
                }

                int score = calcularPuntuacion(listadatos);
                Toast.makeText(PrincipalActivity.this, "Puntuación: " + score, Toast.LENGTH_SHORT).show();

                // Llama al método de SegundoFragment para actualizar el resultado
                segundoFragment.actualizarResultado("Puntuación: " + score);

                // Reemplaza el fragmento actual con el SegundoFragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fcvcontenedor, segundoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                // Deshabilita el botón para evitar que se muestre de nuevo
                btncalificar.setEnabled(false);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fcvcontenedor, primeroFragment);
        fragmentTransaction.commit();

        // Iniciar el servicio Cronometro
        cronometro = new Cronometro(this);
        cronometro.setBtnCalificar(btncalificar);
    }

    private int calcularPuntuacion(ArrayList<EstructuraDatos> datos) {
        int score = 0;
        for (EstructuraDatos dato : datos) {
            if (dato.getRespuestaSeleccionada() != null && dato.getRespuestaSeleccionada().equals(dato.getRc())) {
                score++;
            }
        }
        return score;
    }
}

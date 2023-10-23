package mx.edu.isc.tesoem.hjcg.p2p6_7s21;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import DatosExamen.EstructuraDatos;

public class PrimeroFragment extends Fragment {

    TextView txtpregunta, lblatras;
    RadioButton r1, r2, r3;
    Button btnsig, btnatras, btna, btncalificar;

    int currentIndex = 0;
    ArrayList<EstructuraDatos> listadatos = new ArrayList<>();

    public PrimeroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primero, container, false);

        txtpregunta = view.findViewById(R.id.txtpregunta);
        lblatras = view.findViewById(R.id.lblconteoatras);
        r1 = view.findViewById(R.id.r1);
        r2 = view.findViewById(R.id.r2);
        r3 = view.findViewById(R.id.r3);
        btnsig = view.findViewById(R.id.btnsig);
        btnatras = view.findViewById(R.id.btnatras);
        btna = view.findViewById(R.id.btnatras1);
        btncalificar = getActivity().findViewById(R.id.btncalif);

        cargarPregunta(); // Cargar la primera pregunta

        // Carga las preguntas y respuestas
        listadatos.add(new EstructuraDatos("1.- ¿Quién es el descubridor de América?", "A) Hernán Cortés", "B) Pancho Villa", "C) Cristóbal Colón", "C"));
        listadatos.add(new EstructuraDatos("2.- ¿Quién pintó la última cena?", "A) Diego Rivera", "B) Leonardo Da Vinci", "C) Picasso", "B"));
        listadatos.add(new EstructuraDatos("3.- ¿Quién es el mejor corredor de Fórmula 1?", "A) Checo Pérez", "B) Hamilton", "C) Michael", "B"));
        listadatos.add(new EstructuraDatos("4.- ¿Cuál es el río más largo del mundo?", "A) El Amazonas", "B) El Nilo", "C) El Mississippi", "A"));
        listadatos.add(new EstructuraDatos("5.- ¿Qué planeta es conocido como el planeta rojo?", "A) Venus", "B) Júpiter", "C) Marte", "C"));
        listadatos.add(new EstructuraDatos("6.- ¿Quién pintó la Mona Lisa?", "A) Pablo Picasso", "B) Vincent van Gogh", "C) Leonardo da Vinci", "C"));
        listadatos.add(new EstructuraDatos("7.- ¿Cuál es la moneda oficial de Japón?", "A) Dólar japonés", "B) Yuan chino", "C) Yen japonés", "C"));
        listadatos.add(new EstructuraDatos("8.- ¿Cuál es el metal más abundante en la corteza terrestre?", "A) Oro", "B) Hierro", "C) Aluminio", "C"));
        listadatos.add(new EstructuraDatos("9.- ¿En qué continente se encuentra la Gran Muralla China?", "A) Europa", "B) Asia", "C) África", "B"));
        listadatos.add(new EstructuraDatos("10.- ¿Cuál es el país más grande del mundo en términos de superficie?", "A) Estados Unidos", "B) Canadá", "C) Rusia", "C"));


        btnsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica si se ha seleccionado una respuesta antes de avanzar
                String respuestaSeleccionada = obtenerRespuesta();
                if (respuestaSeleccionada != null) {
                    listadatos.get(currentIndex).setRespuestaSeleccionada(respuestaSeleccionada);
                }

                currentIndex++;

                if (currentIndex < listadatos.size()) {
                    cargarPregunta(); // Cargar la siguiente pregunta
                }

                // Habilita el botón de calificación si es la última pregunta
                habilitarBotonCalificacion();
            }
        });

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex--;

                if (currentIndex >= 0 && currentIndex < listadatos.size()) {
                    cargarPregunta(); // Cargar la pregunta anterior
                }

                // Habilita el botón de calificación si es la última pregunta
                habilitarBotonCalificacion();
            }
        });

        Cronometro cronometro = new Cronometro(getActivity());
        btna.setOnClickListener(View -> {
            cronometro.seView1(lblatras);
        });

        return view;
    }

    private void cargarPregunta() {
        if (currentIndex < listadatos.size()) {
            EstructuraDatos currentQuestion = listadatos.get(currentIndex);
            txtpregunta.setText(currentQuestion.getPregunta());
            r1.setText(currentQuestion.getR1());
            r2.setText(currentQuestion.getR2());
            r3.setText(currentQuestion.getR3());

            // Verifica si ya se ha seleccionado una respuesta
            String respuestaSeleccionada = currentQuestion.getRespuestaSeleccionada();
            if (respuestaSeleccionada != null) {
                if (respuestaSeleccionada.equals("A")) {
                    r1.setChecked(true);
                } else if (respuestaSeleccionada.equals("B")) {
                    r2.setChecked(true);
                } else if (respuestaSeleccionada.equals("C")) {
                    r3.setChecked(true);
                }
            } else {
                // Limpia la selección de respuestas si no hay respuesta seleccionada
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
            }
        }
    }


    public String obtenerRespuesta() {
        if (r1.isChecked()) {
            return "A";
        } else if (r2.isChecked()) {
            return "B";
        } else if (r3.isChecked()) {
            return "C";
        } else {
            return null; // Ninguna respuesta seleccionada
        }
    }

    private void habilitarBotonCalificacion() {
        btncalificar.setEnabled(currentIndex == listadatos.size() - 1);
    }
}

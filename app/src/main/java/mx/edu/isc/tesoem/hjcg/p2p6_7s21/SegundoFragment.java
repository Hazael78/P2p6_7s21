package mx.edu.isc.tesoem.hjcg.p2p6_7s21;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SegundoFragment extends Fragment {

    TextView txtresult;

    public SegundoFragment() {
        // Required empty public constructor
    }

    public static SegundoFragment newInstance(String param1, String param2) {
        SegundoFragment fragment = new SegundoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_segundo, container, false);
        txtresult = view.findViewById(R.id.txtresul); // Aquí accedes a txtresult después de inflar la vista

        return view;
    }

    // Método para actualizar el resultado en el TextView
    public void actualizarResultado(String resultado) {
        if (txtresult != null) {
            txtresult.setText(resultado);
        }
    }
}

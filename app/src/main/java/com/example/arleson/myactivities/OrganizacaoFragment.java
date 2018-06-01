package com.example.arleson.myactivities;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizacaoFragment extends Fragment {

    private TextView nome;
    private TextView endereco;
    private TextView telefone;
    private Button salvar;
    private DBHelper dbHelper;

    public OrganizacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organizacao, container, false);
        getActivity().setTitle("Organização");

        nome = view.findViewById(R.id.text_name_organizacao);
        endereco = view.findViewById(R.id.text_endereco_organizacao);
        telefone = view.findViewById(R.id.text_phone_organizacao);
        salvar = view.findViewById(R.id.btn_salvar_organizacao);

        telefone.addTextChangedListener(Mask.insert("(##)#####-####", (EditText) telefone));

        dbHelper = new DBHelper(getContext());

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nome.getText().length() == 0 || endereco.getText().length() == 0 || telefone.getText().length() == 0) {
                    Toast.makeText(getContext(), "Dados inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nomeString = nome.getText().toString();
                String enderecoString = endereco.getText().toString();
                String telefoneString = telefone.getText().toString();

                dbHelper.addOrganizacao(new Organizacao(nomeString,enderecoString,telefoneString));
                dbHelper.close();

                Toast.makeText(getContext(),"Dados salvo com sucesso!", Toast.LENGTH_SHORT).show();

                nome.setText("");
                endereco.setText("");
                telefone.setText("");

            }
        });

        return view;
    }

}

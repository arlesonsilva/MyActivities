package com.example.arleson.myactivities;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegocioFragment extends Fragment {

    private TextView titulo;
    private TextView descricao;
    private Spinner organizacaoId;
    private Spinner pessoaId;
    private TextView valor;
    private TextView dataEncerramento;
    private TextView estado;
    private Button salvar;
    private DBHelper dbHelper;
    private ArrayList<String> organizacaoList;
    private ArrayList<String> pessoaList;

    public NegocioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_negocio, container, false);
        getActivity().setTitle("Negócio");

        titulo = view.findViewById(R.id.text_title_negocio);
        descricao = view.findViewById(R.id.text_descricao_negocio);
        organizacaoId = view.findViewById(R.id.spinner_orgId_negocio);
        pessoaId = view.findViewById(R.id.spinner_pessoaId_negocio);
        valor = view.findViewById(R.id.text_valor_negocio);
        dataEncerramento = view.findViewById(R.id.text_data_negocio);
        estado = view.findViewById(R.id.text_estado_negocio);
        salvar = view.findViewById(R.id.btn_salvar_negocio);

        dataEncerramento.addTextChangedListener(Mask.insert("##/##/####", (EditText) dataEncerramento));
        valor.addTextChangedListener(Mask.monetario((EditText) valor));

        loadSpinnerOrganizacao();
        loadSpinnerPessoa();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(titulo.getText().length() == 0 || dataEncerramento.getText().length() == 0 || valor.getText().length() == 0) {
                    Toast.makeText(getContext(), "Dados inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String tituloString = titulo.getText().toString();
                String descricaoString = descricao.getText().toString();
                String organizacaoIdString = organizacaoId.getSelectedItem().toString();
                String pessoaIdString = pessoaId.getSelectedItem().toString();
                String valorString = valor.getText().toString();
                String dataEncerramentoString = dataEncerramento.getText().toString();
                String estadoString = estado.getText().toString();

                dbHelper.addNegocio(new Negocio(tituloString,descricaoString,organizacaoIdString,pessoaIdString,valorString,dataEncerramentoString,estadoString));
                dbHelper.close();

                Toast.makeText(getContext(),"Dados salvo com sucesso!", Toast.LENGTH_SHORT).show();

                titulo.setText("");
                descricao.setText("");
                organizacaoId.setSelection(0);
                pessoaId.setSelection(0);
                valor.setText("0");
                dataEncerramento.setText("");
                estado.setText("");
            }
        });

        return view;
    }

    private void loadSpinnerOrganizacao() {
        dbHelper = new DBHelper(getContext());

        List<Organizacao> organizacoes = dbHelper.getAllOrganizacoes();

        organizacaoList = new ArrayList<>();
        organizacaoList.add("Selecione uma Organização");

        for (Organizacao o : organizacoes) {
            organizacaoList.add(o.getNome());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, organizacaoList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        organizacaoId.setAdapter(dataAdapter);
    }

    private void loadSpinnerPessoa() {

        dbHelper = new DBHelper(getContext());

        List<Pessoa> pessoas = dbHelper.getAllPessoas();

        pessoaList = new ArrayList<>();
        pessoaList.add("Selecione uma Pessoa");

        for (Pessoa p : pessoas) {
            pessoaList.add(p.getNome());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, pessoaList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pessoaId.setAdapter(dataAdapter);
    }


}

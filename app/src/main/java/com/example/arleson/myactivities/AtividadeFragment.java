package com.example.arleson.myactivities;

import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
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
public class AtividadeFragment extends Fragment {

    private TextView descricao;
    private TextView tipo;
    private Spinner organizacaoId;
    private Spinner pessoaId;
    private Spinner negocioId;
    private TextView dataAtividade;
    private TextView hora;
    private Button salvar;
    private DBHelper dbHelper;
    private ArrayList<String> organizacaoList;
    private ArrayList<String> pessoaList;
    private ArrayList<String> negocioList;

    public AtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atividade, container, false);
        getActivity().setTitle("Atividade");

        descricao = view.findViewById(R.id.text_descricao_atividade);
        tipo = view.findViewById(R.id.text_tipo_atividade);
        organizacaoId = view.findViewById(R.id.spinner_orgId_atividade);
        pessoaId = view.findViewById(R.id.spinner_pessoaId_atividade);
        negocioId = view.findViewById(R.id.spinner_negocioId_atividade);
        dataAtividade = view.findViewById(R.id.text_data_atividade);
        hora = view.findViewById(R.id.text_hora_atividade);
        salvar = view.findViewById(R.id.btn_salvar_atividade);

        dataAtividade.addTextChangedListener(Mask.insert("##/##/####", (EditText) dataAtividade));
        hora.addTextChangedListener(Mask.insert("##:##", (EditText) hora));


        loadSpinnerOrganizacao();
        loadSpinnerPessoa();
        loadSpinnerNegocio();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(descricao.getText().length() == 0 || tipo.getText().length() == 0 || dataAtividade.getText().length() == 0) {
                    Toast.makeText(getContext(), "Dados inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String descricaoString = descricao.getText().toString();
                String tipoString = tipo.getText().toString();
                String organizacaoIdString = organizacaoId.getSelectedItem().toString();
                String pessoaIdString = pessoaId.getSelectedItem().toString();
                String negocioIdString = negocioId.getSelectedItem().toString();
                String dataAtividadeString = dataAtividade.getText().toString();
                String horaString = hora.getText().toString();

                dbHelper.addAtividade(new Atividade(descricaoString,tipoString,organizacaoIdString,pessoaIdString,negocioIdString,dataAtividadeString,horaString));
                dbHelper.close();

                Toast.makeText(getContext(),"Dados salvo com sucesso!", Toast.LENGTH_SHORT).show();

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

    private void loadSpinnerNegocio() {

        dbHelper = new DBHelper(getContext());

        List<Negocio> negocios = dbHelper.getAllNegocios();

        negocioList = new ArrayList<>();
        negocioList.add("Selecione um Negócio");

        for (Negocio n : negocios) {
            negocioList.add(n.getTitulo());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, negocioList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        negocioId.setAdapter(dataAdapter);
    }

}

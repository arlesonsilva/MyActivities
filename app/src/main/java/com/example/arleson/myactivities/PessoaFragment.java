package com.example.arleson.myactivities;

import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PessoaFragment extends Fragment {

    private TextView nome;
    private TextView telefone;
    private TextView email;
    private TextView textView;
    private Button salvar;
    private DBHelper dbHelper;
    private String text;

    public PessoaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pessoa, container, false);
        getActivity().setTitle("Pessoas");

        nome = view.findViewById(R.id.text_name_pessoa);
        telefone = view.findViewById(R.id.text_phone_pessoa);
        email = view.findViewById(R.id.text_email_pessoa);
        textView = view.findViewById(R.id.textView1);
        salvar = view.findViewById(R.id.btn_salvar_pessoa);

        telefone.addTextChangedListener(Mask.insert("(##)#####-####", (EditText) telefone));

        dbHelper = new DBHelper(getContext());

        List<Pessoa> pessoas = dbHelper.getAllPessoas();
        for (Pessoa p : pessoas) {
            String res = "Id: " + p.getId() + "NOME: " + p.getNome() + " TELEFONE: " + p.getTelefone() + " EMAIL: " + p.getEmail() + " \n";
            text += res;
        }
        textView.setText(text);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nome.getText().length() == 0 || telefone.getText().length() == 0 || email.getText().length() == 0) {
                    Toast.makeText(getContext(), "Dados inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email.getText().toString())) {
                    Toast.makeText(getContext(), " Email inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nomeString = nome.getText().toString();
                String telefoneString = telefone.getText().toString();
                String emailString = email.getText().toString();

                dbHelper.addPessoa(new Pessoa(nomeString,telefoneString,emailString));
                dbHelper.close();

                Toast.makeText(getContext(),"Dados salvo com sucesso!", Toast.LENGTH_SHORT).show();

                nome.setText("");
                telefone.setText("");
                email.setText("");
            }
        });

        return view;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}

package com.example.arleson.myactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arleson on 31/05/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO      = "db_atividade";

    private static final String TABELA1         = "tb_pessoa";
    private static final String TABELA2         = "tb_organizacao";
    private static final String TABELA3         = "tb_negocio";
    private static final String TABELA4         = "tb_atividade";

    private static final String ID              = "id";
    private static final String NOME            = "nome";
    private static final String TELEFONE        = "telefone";
    private static final String EMAIL           = "email";
    private static final String ENDERECO        = "endereco";
    private static final String TITULO          = "titulo";
    private static final String DESCRICAO       = "descricao";
    private static final String ID_ORGANIZACAO  = "id_organizacao";
    private static final String ID_PESSOA       = "id_pessoa";
    private static final String ID_NEGOCIO      = "id_negocio";
    private static final String VALOR           = "valor";
    private static final String DT_ENCERRAMENTO = "dt_encerramento";
    private static final String ESTADO          = "estado";
    private static final String TIPO            = "tipo";
    private static final String DT_ATIVIDADE    = "dt_atividade";
    private static final String HR_ATIVIDADE    = "hr_atividade";

    private static final String CREATE_TABLE1 = "CREATE TABLE " + TABELA1 + " ("
                                                + ID + " integer primary key autoincrement, "
                                                + NOME + " varchar, "
                                                + TELEFONE + " varchar, "
                                                + EMAIL + " varchar"
                                                +")";
    private static final String CREATE_TABLE2 = "CREATE TABLE " + TABELA2 + " ("
                                                + ID + " integer primary key autoincrement, "
                                                + NOME + " varchar, "
                                                + ENDERECO + " varchar, "
                                                + TELEFONE + " varchar"
                                                +")";
    private static final String CREATE_TABLE3 = "CREATE TABLE " + TABELA3 + " ("
                                                + ID + " integer primary key autoincrement, "
                                                + TITULO + " varchar, "
                                                + DESCRICAO + " varchar, "
                                                + ID_ORGANIZACAO + " varchar, "
                                                + ID_PESSOA + " varchar, "
                                                + VALOR + " varchar, "
                                                + DT_ENCERRAMENTO + " varchar, "
                                                + ESTADO + " varchar"
                                                +")";

    private static final String CREATE_TABLE4 = "CREATE TABLE " + TABELA4 + " ("
                                                + ID + " integer primary key autoincrement, "
                                                + DESCRICAO + " varchar, "
                                                + TIPO + " varchar, "
                                                + ID_ORGANIZACAO + " varchar, "
                                                + ID_PESSOA + " varchar, "
                                                + ID_NEGOCIO + " varchar, "
                                                + DT_ATIVIDADE + " varchar, "
                                                + HR_ATIVIDADE + " varchar"
                                                +")";

    private static final int VERSAO             = 1;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE1);
        sqLiteDatabase.execSQL(CREATE_TABLE2);
        sqLiteDatabase.execSQL(CREATE_TABLE3);
        sqLiteDatabase.execSQL(CREATE_TABLE4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA4);
        onCreate(sqLiteDatabase);
    }

    public void addPessoa(Pessoa pessoa) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, pessoa.getNome());
        values.put(TELEFONE, pessoa.getTelefone());
        values.put(EMAIL, pessoa.getEmail());

        sqLiteDatabase.insert(TABELA1,null,values);
        sqLiteDatabase.close();
    }

    public List<Pessoa> getAllPessoas() {
        List<Pessoa> pessoaList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA1;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(cursor.getString(0)));
                pessoa.setNome(String.valueOf(cursor.getString(1)));
                pessoa.setTelefone(String.valueOf(cursor.getString(2)));
                pessoa.setEmail(String.valueOf(cursor.getString(3)));
                pessoaList.add(pessoa);
            }while (cursor.moveToNext());
            cursor.close();
            sqLiteDatabase.close();
        }
        return pessoaList;
    }

    public void addOrganizacao(Organizacao organizacao) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, organizacao.getNome());
        values.put(ENDERECO, organizacao.getEndereco());
        values.put(TELEFONE, organizacao.getTelefone());

        sqLiteDatabase.insert(TABELA2,null,values);
        sqLiteDatabase.close();
    }

    public List<Organizacao> getAllOrganizacoes() {
        List<Organizacao> organizacaoList = new ArrayList<>();

        String sql2 = "SELECT * FROM " + TABELA2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql2,null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Organizacao organizacao = new Organizacao();
                organizacao.setId(Integer.parseInt(cursor.getString(0)));
                organizacao.setNome(String.valueOf(cursor.getString(1)));
                organizacaoList.add(organizacao);
            }while (cursor.moveToNext());
            cursor.close();
            sqLiteDatabase.close();
        }
        return organizacaoList;
    }

    public void addNegocio(Negocio negocio) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITULO, negocio.getTitulo());
        values.put(DESCRICAO, negocio.getDescricao());
        values.put(ID_ORGANIZACAO, negocio.getOrganizacaoId());
        values.put(ID_PESSOA, negocio.getPessoaId());
        values.put(VALOR, negocio.getValor());
        values.put(DT_ENCERRAMENTO, negocio.getDtEncerramento());
        values.put(ESTADO, negocio.getEstado());

        sqLiteDatabase.insert(TABELA3,null,values);
        sqLiteDatabase.close();
    }

    public List<Negocio> getAllNegocios() {
        List<Negocio> negocioList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA3;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Negocio negocio = new Negocio();
                negocio.setId(Integer.parseInt(cursor.getString(0)));
                negocio.setTitulo(String.valueOf(cursor.getString(1)));
                negocioList.add(negocio);
            }while (cursor.moveToNext());
            cursor.close();
            sqLiteDatabase.close();
        }
        return negocioList;
    }

    public void addAtividade(Atividade atividade) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DESCRICAO, atividade.getDescricao());
        values.put(TIPO, atividade.getTipo());
        values.put(ID_ORGANIZACAO, atividade.getOrganizacaoId());
        values.put(ID_PESSOA, atividade.getPessoaId());
        values.put(ID_NEGOCIO, atividade.getNegocioId());
        values.put(DT_ATIVIDADE, atividade.getDataAtividade());
        values.put(HR_ATIVIDADE, atividade.getHora());

        sqLiteDatabase.insert(TABELA4,null,values);
        sqLiteDatabase.close();
    }

}

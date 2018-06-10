package com.grupo04.cleancity.data;

import com.grupo04.cleancity.model.dispositivos.Caminhao;
import com.grupo04.cleancity.model.dispositivos.Lixeira;
import com.grupo04.cleancity.model.dispositivos.ReguladorPh;
import com.grupo04.cleancity.model.equipe.Coleta;
import com.grupo04.cleancity.model.equipe.Equipe;
import com.grupo04.cleancity.model.equipe.Funcionario;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Hagen
 *
 * Database class for a simulated database and simulated persistence
 */
public class Database {

    /**
     * Singleton
     */
    private static Database database;
    public static Database getInstance() {
        if(database == null)
            database = new Database();

        return database;
    }

    private List<Lixeira> lixeiras = new ArrayList<>();
    private List<Lixeira> lixeirasCheias = new ArrayList<>();
    private List<ReguladorPh> reguladoresPH = new ArrayList<>();
    private List<Caminhao> caminhoes = new ArrayList<>();
    private List<Coleta> coletas = new ArrayList<>();
    private List<Equipe> equipes = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();

    private Database() {

    }

    public List<Lixeira> getLixeiras() {
        return lixeiras;
    }

    public List<Lixeira> getLixeirasCheias() {
        return lixeirasCheias;
    }

    public List<ReguladorPh> getReguladoresPH() {
        return reguladoresPH;
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public List<Coleta> getColetas() {
        return coletas;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void addLixeira(Lixeira lixeira) {
        this.lixeiras.add(lixeira);
    }

    public void addCaminhao(Caminhao caminhao) {
        this.caminhoes.add(caminhao);
    }

    public void addColeta(Coleta coleta) {
        this.coletas.add(coleta);
    }

    public void removerLixeira(Lixeira lixeira) {
        this.lixeiras.remove(lixeira);
    }

    public Lixeira getLixeiraById(int id) {
        for (Lixeira lixeira : Database.getInstance().getLixeiras()) {
            if (lixeira.getId() == id)
                return lixeira;
        }

        return null;
    }

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void addEquipe(Equipe equipe) {
        equipes.add(equipe);
    }
}

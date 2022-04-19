package br.senai.sp.cotia.todolistapp.model;

public class SubTarefa {

    private Long idSubtarefa;
    private String descricao;
    private String foto;
    private boolean concluida;
    private Long idTareda;

    public Long getIdSubtarefa() {
        return idSubtarefa;
    }

    public void setIdSubtarefa(Long idSubtarefa) {
        this.idSubtarefa = idSubtarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Long getIdTareda() {
        return idTareda;
    }

    public void setIdTareda(Long idTareda) {
        this.idTareda = idTareda;
    }
}

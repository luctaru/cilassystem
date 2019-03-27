/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vande
 */
@Entity
@Table(name = "grupo_produtos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoProdutos.findAll", query = "SELECT g FROM GrupoProdutos g")
    , @NamedQuery(name = "GrupoProdutos.findByCodigo", query = "SELECT g FROM GrupoProdutos g WHERE g.codigo = :codigo")
    , @NamedQuery(name = "GrupoProdutos.findByNome", query = "SELECT g FROM GrupoProdutos g WHERE g.nome = :nome")
    , @NamedQuery(name = "GrupoProdutos.findByComissaoVista", query = "SELECT g FROM GrupoProdutos g WHERE g.comissaoVista = :comissaoVista")
    , @NamedQuery(name = "GrupoProdutos.findByComissaoPrazo", query = "SELECT g FROM GrupoProdutos g WHERE g.comissaoPrazo = :comissaoPrazo")})
public class GrupoProdutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comissao_vista")
    private BigDecimal comissaoVista;
    @Column(name = "comissao_prazo")
    private BigDecimal comissaoPrazo;

    public GrupoProdutos() {
    }

    public GrupoProdutos(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getComissaoVista() {
        return comissaoVista;
    }

    public void setComissaoVista(BigDecimal comissaoVista) {
        this.comissaoVista = comissaoVista;
    }

    public BigDecimal getComissaoPrazo() {
        return comissaoPrazo;
    }

    public void setComissaoPrazo(BigDecimal comissaoPrazo) {
        this.comissaoPrazo = comissaoPrazo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoProdutos)) {
            return false;
        }
        GrupoProdutos other = (GrupoProdutos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.bean.GrupoProdutos[ codigo=" + codigo + " ]";
    }
    
}

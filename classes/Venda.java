/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;

/**
 *
 * @author brend
 */
public class Venda implements Comparable<Venda>{
    private String prod;
    private int id;
    private String data;
    private double total;
    private double lucro;
    public String Coment;

    public Venda(String prod, int id, String data, double total, double lucro, String Coment) {
        this.prod = prod;
        this.id = id;
        this.data = data;
        this.total = total;
        this.lucro = lucro;
        this.Coment = Coment;
    }

    public Venda(String prod,String data, double total, double lucro) {
        this.prod = prod;
        this.data = data;
        this.total = total;
        this.lucro = lucro;
    }

    public Venda(String prod, double lucro) {
        this.prod = prod;
        this.lucro = lucro;
    }

    public Venda(int id, String prod,String data, double total, double lucro,String Coment) {
        this.prod = prod;
        this.id = id;
        this.data = data;
        this.total = total;
        this.lucro = lucro;
        this.Coment = Coment;
    }

    public String getProd() {
        return prod;
    }
    
     public String getComent() {
        return Coment;
    }
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public double getTotal() {
        return total;
    }

    public double getLucro() {
        return lucro;
    }

    public int compareTo(Venda o) {
        if(this.lucro<o.getLucro()){
            return -1;
        }else if(this.getLucro() > o.getLucro()){
            return 1;
        }
        return 0;
    }
}

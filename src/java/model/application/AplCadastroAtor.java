/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.application;

import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.domain.Ator;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.Conection;

/**
 *
 * @author User
 */
public class AplCadastroAtor {
    public static int NOMEINVALIDO = 1;
    public static int PERSISTENCIA = 2;
    public static int ERRO_GERAL = 3;
    public static int SUCESSO = 4;
    
    
     public List<Ator> listarTodos() {
        // consulta no banco e retorna lista de atores
        
        
        return null;
    }
    public static int inserir(String nome) throws IOException {
        // insere ator no banco
        if((nome.equals("")) || (nome == null)){
            return NOMEINVALIDO;
        }
        
        Ator actor = new Ator(nome);
        
        Session s = Conection.getSession();
	Transaction t = null;
        
        try {
            t = s.beginTransaction();
            s.save(actor);
            
            t.commit();
            
            return SUCESSO;
        }catch(HibernateException he){
            t.rollback();
            return PERSISTENCIA;
        }catch(Exception e){
            t.rollback();
            return ERRO_GERAL;
        }finally{
            s.close();
        }
    }
    public void excluir(int id) {
        // exclui ator do banco
    }
    public void atualizar(Ator ator) {
        // atualiza ator no banco
    }
}

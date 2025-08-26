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

    public static List<Ator> listarTodos() {
        Session s = Conection.getSession();
        List<Ator> atores = null;

        try {
            atores = s.createQuery("FROM Ator", Ator.class).list();
        } catch (HibernateException he) {
            System.out.println("Erro ao listar atores: " + he.getMessage());
        } finally {
            s.close();
        }

        return atores;
    }

    public static int inserir(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
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
        } catch (HibernateException he) {
            if (t != null) {
                t.rollback();
            }
            return PERSISTENCIA;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return ERRO_GERAL;
        } finally {
            if (s != null && s.isOpen()) {
                s.close();
            }
        }
    }

    public static int excluir(int id) {
        Session s = Conection.getSession();
        Transaction t = null;

        try {
            t = s.beginTransaction();

            // Busca o ator pelo id
            Ator ator = s.get(Ator.class, id);
            if (ator == null) {
                return NOMEINVALIDO;
            }

            s.delete(ator);
            t.commit();
            return SUCESSO;
        } catch (HibernateException he) {
            if (t != null) {
                t.rollback();
            }
            return PERSISTENCIA;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return ERRO_GERAL;
        } finally {
            s.close();
        }
    }

    public static int atualizar(String nome) {
        Session s = Conection.getSession();
        Transaction t = null;

        try {
            t = s.beginTransaction();
            s.update(nome);
            t.commit();
            return SUCESSO;
        } catch (HibernateException he) {
            if (t != null) {
                t.rollback();
            }
            return PERSISTENCIA;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            return ERRO_GERAL;
        } finally {
            s.close();
        }
    }
}

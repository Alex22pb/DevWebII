/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.application.AplCadastroAtor;

/**
 *
 * @author User
 */
@WebServlet(name = "servletLocadora", urlPatterns = {"/cadastroAtorLocadora"})
public class CrtCadastroAtor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("operacao");

        switch (op) {

            case "inserir":
                String nome = request.getParameter("txt_name");

                if (AplCadastroAtor.inserir(nome) == AplCadastroAtor.SUCESSO) {
                    response.sendRedirect("MensagemSucesso.html");
                } else {
                    response.sendRedirect("MensagemErro.html");
                }
                break;
            case "atualizar":
                break;
            case "excluir":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

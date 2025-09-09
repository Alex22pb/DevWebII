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
import java.util.List;
import model.application.AplCadastroAtor;
import model.domain.Ator;

/**
 *
 * @author User
 */
@WebServlet(name = "servletLocadora", urlPatterns = {"/cadastroAtorLocadora"})
public class CrtCadastroAtor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("operacao");

        if (op == null) {
            response.sendRedirect(".");
            return;
        }

        switch (op) {
            case "inserir":
                String nome = request.getParameter("txt_name");
                if (AplCadastroAtor.inserir(nome) == AplCadastroAtor.SUCESSO) {
                    response.sendRedirect(".");
                } else {
                    response.sendRedirect(".");
                }
                break;

            case "atualizar":
                String idStr = request.getParameter("id");
                String novoNome = request.getParameter("txt_name");
                if (idStr != null && !idStr.isEmpty() &&
                    AplCadastroAtor.atualizar(Integer.parseInt(idStr), novoNome) == AplCadastroAtor.SUCESSO) {
                    response.sendRedirect(".");
                } else {
                    response.sendRedirect(".");
                }
                break;

            case "excluir":
                String idExcluir = request.getParameter("id");
                if (idExcluir != null && AplCadastroAtor.excluir(Integer.parseInt(idExcluir)) == AplCadastroAtor.SUCESSO) {
                    response.sendRedirect(".");
                } else {
                    response.sendRedirect(".");
                }
                break;

            case "listar":
                List<Ator> atores = AplCadastroAtor.listarTodos();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                StringBuilder json = new StringBuilder("[");
                for (int i = 0; i < atores.size(); i++) {
                    Ator a = atores.get(i);
                    json.append("{")
                        .append("\"id\":").append(a.getId()).append(",")
                        .append("\"nome\":\"").append(a.getName().replace("\"", "\\\"")).append("\"")
                        .append("}");
                    if (i < atores.size() - 1) json.append(",");
                }
                json.append("]");

                response.getWriter().write(json.toString());
                break;


            default:
                response.sendRedirect(".");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}


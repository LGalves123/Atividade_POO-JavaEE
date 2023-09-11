/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;
import java.util.Random;

@WebServlet(urlPatterns = {"/loteria.json"})
public class ApiLoteriaServlet extends HttpServlet {

    private ArrayList<Integer> numerosPossiveis = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Integer> loteria = numeros(6);

            // Criar um objeto JSON com a data/hora e os números da loteria
            JSONObject obj = new JSONObject();
            obj.put("dateTime", new Date());
            JSONArray arr = new JSONArray(loteria); // Converter a lista em um JSONArray
            obj.put("numerosLoteria", arr);

            out.print(obj.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private List<Integer> numeros(int quantidade) {
        List<Integer> aleatorios = new ArrayList<>();
        Random random = new Random();

        if (numerosPossiveis.isEmpty()) {
            // Inicialize a lista de números possíveis com os números dentro do intervalo desejado
            for (int i = 1; i <= 60; i++) {
                numerosPossiveis.add(i);
            }
        }

        for (int i = 0; i < quantidade; i++) {
            int indice = random.nextInt(numerosPossiveis.size());
            int numero = numerosPossiveis.remove(indice);
            aleatorios.add(numero);
        }

        return aleatorios;
    }
}

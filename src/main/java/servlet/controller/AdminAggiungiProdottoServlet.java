package servlet.controller;

import dao.ProdottoDAO;
import model.Prodotto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAggiungiProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Mostra il form se viene fatta una richiesta GET
        request.getRequestDispatcher("/pages/admin/admin-aggiungi-prodotto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // Evita problemi con accenti e caratteri speciali

        try {
            Prodotto p = new Prodotto();

            // 1. Categoria (gestisce stringa vuota o null)
            String idCatParam = request.getParameter("id_categoria");
            if (idCatParam != null && !idCatParam.trim().isEmpty()) {
                p.setIdCategoria(Integer.parseInt(idCatParam));
            } else {
                p.setIdCategoria(null);
            }

            // 2. Campi Testuali
            p.setNome(request.getParameter("nome"));
            p.setUrlNomeProdotto(request.getParameter("url_nome_prodotto"));
            p.setDescrizione(request.getParameter("descrizione"));

            // 3. Prezzo (converte eventuale virgola in punto)
            String prezzoParam = request.getParameter("prezzo");
            if (prezzoParam != null) {
                p.setPrezzo(Double.parseDouble(prezzoParam.replace(",", ".")));
            }

            // 4. Quantità (stringa, es. "10pz" oppure "1L")
            p.setQuantitaDaTenere(request.getParameter("quantita_da_tenere"));

            // 5. Immagine (fondamentale per ImmagineProdottoDAO)
            p.setImmagine(request.getParameter("url_immagine"));

            // 6. Checkbox Pubblico (sicuro contro NullPointerException)
            String pubblicoParam = request.getParameter("pubblico");
            p.setPubblico("1".equals(pubblicoParam) || "true".equalsIgnoreCase(pubblicoParam) || "on".equalsIgnoreCase(pubblicoParam));

            // Salvataggio nel DB via DAO
            ProdottoDAO.save(p);

            // Redirezione alla tabella dei prodotti
            response.sendRedirect(request.getContextPath() + "/pages/admin/prodotti");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore nel salvataggio: " + e.getMessage());
            doGet(request, response);
        }
    }
}
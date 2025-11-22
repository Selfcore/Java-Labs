package org.example.lab6;

import Models.Product;
import Persistence.Repositories.ProductRepository;
import Persistence.Repositories.RepositoryImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(value = "/products")
public class ProductServlet extends HttpServlet {
    private RepositoryImp<Product> productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("products", productRepository.getAll());
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}

package Servlets;

import Models.Product;
import Persistence.Repositories.ProductRepository;
import Persistence.Repositories.RepositoryImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(value = "/add-product")
public class AddProductServlet extends HttpServlet {
    private RepositoryImp<Product> productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addProductForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);

        productRepository.save(product);

        response.sendRedirect("products");
    }
}

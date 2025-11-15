package org.example.lab4;

import Interfaces.RepositoryImp;
import Models.Laptop;
import Repositories.LaptopRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "laptopsServlet", value = "/laptops")
public class LaptopsServlet extends HttpServlet {
    private static List<Laptop> laptops = new ArrayList<>();
    private RepositoryImp<Laptop> laptopRepository = new LaptopRepository();

    public void init() {
        laptops = laptopRepository.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("laptops", laptops);
        req.getRequestDispatcher("/laptops.jsp").forward(req, resp);
    }
}

package org.example.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GuessServlet", value = "/guess")
public class GuessServlet extends HttpServlet {
    private static final int MIN_THRESHOLD = 0;
    private static final int MAX_THRESHOLD = 100;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        int secret = Integer.parseInt(req.getParameter("secret"));
        session.setAttribute("secret", secret);
        session.setAttribute("low", MIN_THRESHOLD);
        session.setAttribute("high", MAX_THRESHOLD);
        session.setAttribute("attempts", 1);

        int guess = (MIN_THRESHOLD + MAX_THRESHOLD) / 2;

        showGuessPage(resp, guess);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("guess.jsp");
            return;
        }

        int low = (Integer) session.getAttribute("low");
        int high = (Integer) session.getAttribute("high");
        int secret = (Integer) session.getAttribute("secret");
        int attempts = (Integer) session.getAttribute("attempts");

        int guess = Integer.parseInt(req.getParameter("guess"));
        String answer = req.getParameter("answer");

        if ("Більше".equals(answer)) {
            low = guess + 1;
        } else if ("Менше".equals(answer)) {
            high = guess - 1;
        } else if ("Дорівнює".equals(answer)) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h2>Комп’ютер вгадав число: <b>" + guess + "</b></h2>");
            out.println("<p>Кількість спроб: " + attempts + "</p>");
            out.println("<form action='guess.jsp' method='get'>");
            out.println("<input type='submit' value='Почати нову гру'>");
            out.println("</form>");
            out.println("</body></html>");
            session.invalidate();
            return;
        }

        session.setAttribute("low", low);
        session.setAttribute("high", high);
        session.setAttribute("attempts", attempts + 1);

        int nextGuess = (low + high) / 2;
        showGuessPage(resp, nextGuess);
    }

    private void showGuessPage(HttpServletResponse resp, int guess) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2>Комп’ютер думає, що твоє число: <b>" + guess + "</b></h2>");
        out.println("<form action='guess' method='get'>");
        out.println("<input type='hidden' name='guess' value='" + guess + "'>");
        out.println("<input type='submit' name='answer' value='Більше'>");
        out.println("<input type='submit' name='answer' value='Менше'>");
        out.println("<input type='submit' name='answer' value='Дорівнює'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}

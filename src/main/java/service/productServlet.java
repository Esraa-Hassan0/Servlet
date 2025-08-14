package service;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import DAO.productDAO;
import com.google.gson.Gson;

@WebServlet("/products")
public class productServlet extends HttpServlet{
    private productDAO productDAO = new productDAO();
    private Gson gson=new Gson();

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
//        req.setAttribute("products",productDAO.listAll());
//        req.getRequestDispatcher("/WEB-INF/products.jsp").forward(req,res);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        String json=gson.toJson(productDAO.listAll());
        PrintWriter out=res.getWriter();
        out.print(json);
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
       String action=req.getParameter("action");
       if(action.equals("add")){
        productDAO.add(req.getParameter("name"),Double.parseDouble((req.getParameter("price"))));
       }else if ("update".equals(action)) {
           productDAO.update(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), Double.parseDouble(req.getParameter("price")));
       }
            res.sendRedirect("products");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productDAO.delete(id);
        res.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}



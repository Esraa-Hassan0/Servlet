package service;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import DAO.productDAO;

@WebServlet("/products")
public class productServlet extends HttpServlet{
    private productDAO productDAO = new productDAO();
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        req.setAttribute("products",productDAO.listAll());
        req.getRequestDispatcher("/WEB-INF/products.jsp").forward(req,res);
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



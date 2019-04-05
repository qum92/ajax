package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MovieService;
import service.impl.MovieServiceImpl;
import util.Command;

public class AJAXMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=urf-8");
			if ("list".equals(cmd)) {
				PrintWriter pw = response.getWriter();
				pw.println(gson.toJson(ms.selectMovieList()));
			} else {
				try {
					int miNum = Integer.parseInt(cmd);
					PrintWriter pw = response.getWriter();
					pw.println(gson.toJson(ms.selectMovieByNum(miNum)));
				} catch (Exception e) {
					throw new ServletException("올바른 상세조회 값이 아닙니다.");
				}
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = Command.getCmd(request);
			if ("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				if (hs.getAttribute("user") == null) {
					Map<String,String> rMap = new HashMap<>();
					rMap.put("msg", "로그인하세요.");
					rMap.put("url", "/");
					Command.printJSON(response,rMap);
					return;
				}
				Map<String, String> movie = Command.getSingMap(request);
				Map<String,String> rMap = new HashMap<>();
				rMap.put("msg", "등록에 실패하였습니다.");
				rMap.put("url", "/views/movie/ajax_list");
				if(ms.insertMovie(movie)==1) {
					rMap.put("msg", "등록에 성공하셨습니다.");					
				}
				Command.printJSON(response,rMap);
			} else if ("update".equals(cmd)) {
				HttpSession hs = request.getSession();
				if (hs.getAttribute("user") == null) {
				Map<String,String> rMap = new HashMap<>();
				rMap.put("msg", "로그인하세요.");
				rMap.put("url", "/");
				Command.printJSON(response,rMap);
				}
				Map<String, String> movie = new HashMap<>();
				movie.put("mi_name", request.getParameter("mi_name"));
				movie.put("mi_year", request.getParameter("mi_year"));
				movie.put("mi_national", request.getParameter("mi_national"));
				movie.put("mi_vendor", request.getParameter("mi_vendor"));
				movie.put("mi_director", request.getParameter("mi_director"));
				String msg = "영화업데이트실패!";
				String url = "/movie/list";
				if (ms.updateMovie(movie) == 1) {
					msg = "영화업데이트성공";
				}
				Command.goResultPage(request, response, msg, url);
			} else if ("delete".equals(cmd)) {
				HttpSession hs = request.getSession();
				if (hs.getAttribute("user") == null) {
					Map<String,String> rMap = new HashMap<>();
					rMap.put("msg", "로그인하세요.");
					rMap.put("url", "/");
					Command.printJSON(response,rMap);
					return;
				}
				int miNum = Integer.parseInt(request.getParameter("mi_num"));
				Map<String,String> rMap = new HashMap<>();
				rMap.put("msg", "삭제에 실패하였습니다.");
				rMap.put("url", "/views/movie/ajax_list");
				if(ms.deleteMovie(miNum)==1) {
					rMap.put("msg", "삭제에 성공하셨습니다.");					
				}
				Command.printJSON(response,rMap);
			}
		}
	}

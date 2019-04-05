package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MovieService;
import service.impl.MovieServiceImpl;
import util.Command;

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html);charset=utf-8");
		String cmd = Command.getCmd(request);
			if ("list".equals(cmd)) {
				request.setAttribute("list", ms.selectMovieList());
				RequestDispatcher rd = request.getRequestDispatcher("/views/movie/movie_list");
				rd.forward(request, response);
			} else {
				try {
					int miNum = Integer.parseInt(cmd);
					request.setAttribute("movie", ms.selectMovieByNum(miNum));
					RequestDispatcher rd = request.getRequestDispatcher("/views/movie/view");
					rd.forward(request, response);
				} catch (Exception e) {
					throw new ServletException("올바른 상세조회 값이 아닙니다.");
				}
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html);charset=utf-8");
		String cmd = Command.getCmd(request);
			if ("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				if (hs.getAttribute("user") == null) {
					Command.goResultPage(request, response, "/", "로그인하세요");
					return;
				}
				Map<String, String> movie = Command.getSingMap(request);
				String msg = "영화등록실패!";
				String url = "/movie/list";
				if (ms.insertMovie(movie) == 1) {
					msg = "영화등록성공!";
				}
				Command.goResultPage(request, response, msg, url);
			} else if ("update".equals(cmd)) {
				HttpSession hs = request.getSession();
				if (hs.getAttribute("user") == null) {
					Command.goResultPage(request, response, "/", "로그인하세요");
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
					Command.goResultPage(request, response, "/", "로그인하세요");
					return;
				}
				int miNum = Integer.parseInt(request.getParameter("mi_num"));
				String msg = "삭제에 실패하였습니다.";
				String url = "/movie/" + miNum;
				if(ms.deleteMovie(miNum)==1) {
					msg = "삭제에 성공하셨습니다";
				}
				Command.goResultPage(request, response, msg, url);
			}
		}
	}

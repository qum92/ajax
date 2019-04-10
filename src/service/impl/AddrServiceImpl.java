package service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;
import service.AddrService;
import util.Command;

public class AddrServiceImpl implements AddrService {
	private AddrDAO adao = new AddrDAOImpl();

	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if (paramMap.get("page") != null && !"".equals(paramMap.get("page"))) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if (paramMap.get("pageCount") != null && !"".equals(paramMap.get("pageCount"))) {
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		if (paramMap.get("blockCount") != null && !"".equals(paramMap.get("blockCount"))) {
			blockCount = Integer.parseInt(paramMap.get("blockCount"));
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		request.setAttribute("page", page);
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount - 1);
		paramMap.put("lNum", lNum + "");
		paramMap.put("sNum", sNum + "");
		List<Map<String, String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		int totalCnt = adao.selectTotalAddrCnt(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt / pageCount;
		if (totalCnt % pageCount > 0) {
			totalPageCnt++;
		}
		int sBlock = ((page - 1) / blockCount) * blockCount + 1;
		int lBlock = sBlock + (blockCount - 1);
		if (sBlock > totalPageCnt) {
			sBlock = totalPageCnt;
		}
		request.setAttribute("sBlock", sBlock);
		request.setAttribute("lBlock", lBlock);
		request.setAttribute("totalPageCnt", totalPageCnt);
		return addrList;
	}

	public int selectTotalAddrCnt() {
		return 0;
	}

	public void selectAddr(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingMap(request);
		int page = 1;
		int pageCount = 10;
		if (paramMap.get("page") != null && !"".equals(paramMap.get("page"))) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if (paramMap.get("pageCount") != null && !"".equals(paramMap.get("pageCount"))) {
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("addr", adao.selectAddr(paramMap));
	}

	public Map<String,String> updateAddr(HttpServletRequest request) throws IOException {
		Map<String,String> param = Command.fromJson(request);
		Map<String,String> rMap = new HashMap<>();
		rMap.put("result","false");
		rMap.put("msg","수정에 실패했습니다.");
		if(adao.updateAddr(param)==1) {
			rMap.put("update","true");
			rMap.put("msg","수정에 성공했습니다.");
		}
		return rMap;
	}


	public Map<String,String> deleteAddr(HttpServletRequest request) throws IOException {
		Map<String,String> param = Command.fromJson(request);
		Map<String,String> rMap = new HashMap<>();
		rMap.put("result","false");
		rMap.put("msg","삭제에 실패했습니다.");
		if(adao.deleteAddr(param)==1) {
			rMap.put("delete","true");
			rMap.put("msg","삭제에 성공했습니다.");
		}
		return rMap;
	}
}

package service.impl;

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
		Map<String,String> paramMap = Command.getSingMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if(paramMap.get("page")!=null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null) {
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		if(paramMap.get("blockCount")!=null) {
			blockCount = Integer.parseInt(paramMap.get("blockCount"));
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		request.setAttribute("page", page);
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount-1);
		paramMap.put("lNum", lNum+"");
		paramMap.put("sNum", sNum+"");
		List<Map<String,String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		int totalCnt = adao.selectTotalAddrCnt(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt/pageCount;
		if(totalCnt%pageCount>0) {
			totalPageCnt ++;
		}
		int sBlock = ((page-1)/blockCount) * blockCount+1;
		int lBlock = sBlock + (blockCount -1);
		if(sBlock>totalPageCnt) {
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

}
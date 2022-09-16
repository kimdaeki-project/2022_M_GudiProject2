package com.iu.home.licenseQna;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	//글목록
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception {
		ModelAndView mv = new ModelAndView();
		
		List<QnaDTO> ar = qnaService.getList(pager);
		mv.addObject("pager",pager);
		mv.addObject("list",ar);
		//mv.addObject("Qna");
		mv.setViewName("qna/list");
		return mv;
	}
	//글상세
	@GetMapping("detail")
	public ModelAndView getDetail(QnaDTO qnaDTO)throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("qna/detail");
		return mv;
	}
	
	//글쓰기
	@GetMapping("add")
	public String setAdd()throws Exception {
		return "qna/add";
	}
	//
	@PostMapping("add")
	public ModelAndView setAdd(QnaDTO qnaDTO,MultipartFile[]files, HttpSession session)throws Exception {
		ModelAndView mv = new ModelAndView();
		int result =qnaService.setAdd(qnaDTO,files,session.getServletContext());
		mv.setViewName("redirect:./list");
		return mv;
	}
	//글수정
	@GetMapping("update")
	public ModelAndView setUpdate(QnaDTO qnaDTO,ModelAndView mv)throws Exception {
		qnaDTO = qnaService.getDetail(qnaDTO);
		mv.addObject("qnaDTO",qnaDTO);
		mv.setViewName("qna/update");
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(QnaDTO qnaDTO)throws Exception {
		int result = qnaService.setUpdate(qnaDTO);
		return "redirect:./detail?num="+qnaDTO.getQnaNum();
	}
	//글삭제
	public String setDelete(QnaDTO qnaDTO)throws Exception {
		int result = qnaService.setDelete(qnaDTO);
		return "redirect./list";
	}
	
	@GetMapping("reply")
	public ModelAndView setReply(QnaDTO qnaDTO, ModelAndView mv)throws Exception {
		mv.addObject("qnaDTO", qnaDTO);
		mv.setViewName("qna/reply");
		return mv;
	}
	
	@PostMapping("reply")
	public String setReply(QnaDTO qnaDTO)throws Exception {
		int result = qnaService.setReply(qnaDTO);
		return "redirect:./list";
	}

}